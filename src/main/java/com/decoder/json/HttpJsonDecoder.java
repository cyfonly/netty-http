package com.decoder.json;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;

/**
 * HTTP JSON 解码器，将 HTTP message body 反序列化为 JSONObject
 * 
 * 使用方法：
 * HttpServer 中：
 * 		.addLast("jsonDecoder", new HttpJsonDecoder())
 * HttpServerhandler channelRead 方法中：
 * 		if(msg instanceof JSONObject){
 * 			JSONObject obj = (JSONObject) msg;
 * 			......
 * 		}
 * 
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class HttpJsonDecoder extends MessageToMessageDecoder<HttpRequest>{
	
	@Override
	protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) throws Exception {
		FullHttpRequest fullRequest = (FullHttpRequest) msg;
		ByteBuf content = fullRequest.content();
		int length = content.readableBytes();
		byte[] bytes = new byte[length];
		for(int i=0; i<length; i++){
			bytes[i] = content.getByte(i);
		}
		try{
			JSONObject obj = JSON.parseObject(new String(bytes));
			out.add(obj);
		}catch(ClassCastException e){
			throw new CodecException("HTTP message body is not a JSONObject");
		}
	}

}
