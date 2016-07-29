package com.handlers;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.config.ServerConf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.util.ReferenceCountUtil;

/**
 * 业务 handler demo
 * @author yunfeng.cheng
 * @create 2016-07-25
 */
public class HttpServerhandler extends ChannelHandlerAdapter{
	
	private static ServerConf cfg;
	
	private HttpHeaders headers;
	private HttpRequest request;
	private FullHttpResponse response;
	private FullHttpRequest fullRequest;
	private HttpPostRequestDecoder decoder;
	private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MAXSIZE);
    
    private static final String FAVICON_ICO = "/favicon.ico";
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String CONNECTION_KEEP_ALIVE = "keep-alive";
    private static final String CONNECTION_CLOSE = "close";
    
    
    static{
    	cfg = ConfigFactory.create(ServerConf.class);
    	
    	//上传文件存放根路径
        DiskFileUpload.baseDirectory = cfg.filedir();
    }
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
		if(msg instanceof HttpRequest){
			try{
				request = (HttpRequest) msg;
				headers = request.headers();
				
				String uri = request.uri();
				System.out.println("http uri: " + uri);
				//去除浏览器"/favicon.ico"的干扰
				if(uri.equals(FAVICON_ICO)){
					return;
				}
				
				HttpMethod method = request.method();
				if(method.equals(HttpMethod.GET)){
					QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
					Map<String, List<String>> uriAttributes = queryDecoder.parameters();
					//此处仅打印请求参数（你可以根据业务需求自定义处理）
		            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
		                for (String attrVal : attr.getValue()) {
		                    System.out.println(attr.getKey() + "=" + attrVal);
		                }
		            }
				}else if(method.equals(HttpMethod.POST)){
					
					//POST请求，由于你需要从消息体中获取数据，因此有必要把msg转换成FullHttpRequest
					fullRequest = (FullHttpRequest) msg;
					
					//根据不同的 Content_Type 处理 body 数据
					dealWithContentType();
				}else{
					//其他类型在此不做处理，需要的话可自己扩展
				}
				
				writeResponse(ctx.channel(), HttpResponseStatus.OK, SUCCESS, false);
				
			}catch(Exception e){
				writeResponse(ctx.channel(), HttpResponseStatus.INTERNAL_SERVER_ERROR, ERROR, true);
				
			}finally{
				ReferenceCountUtil.release(msg);
			}
			
		}else{
			//discard request...
			ReferenceCountUtil.release(msg);
		}
	}
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
	
	/**
	 * 简单处理常用几种 Content-Type 的 POST 内容（可自行扩展）
	 * @param headers
	 * @param content
	 * @throws Exception
	 */
	private void dealWithContentType() throws Exception{
		String contentType = getContentType();
		if(contentType.equals("application/json")){  //可以使用HttpJsonDecoder
			String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
			JSONObject obj = JSON.parseObject(jsonStr);
			for(Entry<String, Object> item : obj.entrySet()){
				System.out.println(item.getKey()+"="+item.getValue().toString());
			}
			
		}else if(contentType.equals("application/x-www-form-urlencoded")){
			//方式一：使用 QueryStringDecoder
//			String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
//			QueryStringDecoder queryDecoder = new QueryStringDecoder(jsonStr, false);
//			Map<String, List<String>> uriAttributes = queryDecoder.parameters();
//            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
//                for (String attrVal : attr.getValue()) {
//                    System.out.println(attr.getKey() + "=" + attrVal);
//                }
//            }
            //方式二：使用 HttpPostRequestDecoder
			initPostRequestDecoder();
			List<InterfaceHttpData> datas = decoder.getBodyHttpDatas();
            for (InterfaceHttpData data : datas) {
            	if(data.getHttpDataType() == HttpDataType.Attribute) {
            		Attribute attribute = (Attribute) data;
            		System.out.println(attribute.getName() + "=" + attribute.getValue());
            	}
            }
            
		}else if(contentType.equals("multipart/form-data")){  //用于文件上传
			readHttpDataAllReceive();
			
		}else{
			//do nothing...
		}
	}
	
	private void readHttpDataAllReceive() throws Exception{
		initPostRequestDecoder();
		try {
			List<InterfaceHttpData> datas = decoder.getBodyHttpDatas();
            for (InterfaceHttpData data : datas) {
                writeHttpData(data);
            }
        } catch (Exception e) {
        	//此处仅简单抛出异常至上一层捕获处理，可自定义处理
            throw new Exception(e);
        }
	}
	
	private void writeHttpData(InterfaceHttpData data) throws Exception{
		//后续会加上块传输（HttpChunk），目前仅简单处理
    	if(data.getHttpDataType() == HttpDataType.FileUpload) {
    		FileUpload fileUpload = (FileUpload) data;
    		String fileName = fileUpload.getFilename();
    		if(fileUpload.isCompleted()) {
    			//保存到磁盘
    			StringBuffer fileNameBuf = new StringBuffer(); 
    			fileNameBuf.append(DiskFileUpload.baseDirectory).append(fileName);
    			fileUpload.renameTo(new File(fileNameBuf.toString()));
    		}
    	}
	}
	
	private void writeResponse(Channel channel, HttpResponseStatus status, String msg, boolean forceClose){
		ByteBuf byteBuf = Unpooled.wrappedBuffer(msg.getBytes());
		response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, byteBuf);
		boolean close = isClose();
		if(!close && !forceClose){
			response.headers().add(org.apache.http.HttpHeaders.CONTENT_LENGTH, String.valueOf(byteBuf.readableBytes()));
		}
		ChannelFuture future = channel.write(response);
		if(close || forceClose){
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
	
	private String getContentType(){
		String typeStr = headers.get("Content-Type").toString();
		String[] list = typeStr.split(";");
		return list[0];
	}
	
	private void initPostRequestDecoder(){
		if (decoder != null) {  
            decoder.cleanFiles();  
            decoder = null;  
        }
		decoder = new HttpPostRequestDecoder(factory, request, Charsets.toCharset(CharEncoding.UTF_8));
	}
	
	private boolean isClose(){
		if(request.headers().contains(org.apache.http.HttpHeaders.CONNECTION, CONNECTION_CLOSE, true) ||
				(request.protocolVersion().equals(HttpVersion.HTTP_1_0) && 
				!request.headers().contains(org.apache.http.HttpHeaders.CONNECTION, CONNECTION_KEEP_ALIVE, true)))
			return true;
		return false;
	}

}
