package com.decoder.protobuf;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;

/**
 * HTTP protobuf 解码器，将 HTTP message body 反序列化为 protobuf 生成的 pojo
 * 
 * 使用方法（以当前目录下的UserProtobuf为例）：
 * HttpServer 中：
 * 		.addLast("protobufDecoder", new HttpProtobufDecoder(UserProbuf.User.getDefaultInstance()))
 * HttpServerhandler channelRead 方法中：
 * 		if(msg instanceof UserProbuf.User){
 * 			UserProbuf.User user = (UserProbuf.User) msg;
 * 			......
 * 		}
 * 
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class HttpProtobufDecoder extends MessageToMessageDecoder<HttpRequest>{
	
	private final MessageLite prototype;
	
	public HttpProtobufDecoder(MessageLite prototype){
		if (prototype == null) {
            throw new NullPointerException("prototype");
        }
        this.prototype = prototype.getDefaultInstanceForType();
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) {
		FullHttpRequest fullRequest = (FullHttpRequest) msg;
		ByteBuf content = fullRequest.content();
		int length = content.readableBytes();
		byte[] bytes = new byte[length];
		for(int i=0; i<length; i++){
			bytes[i] = content.getByte(i);
		}
		try {
			out.add(prototype.getParserForType().parseFrom(bytes, 0, length));
		} catch (InvalidProtocolBufferException e) {
			throw new CodecException("HTTP message body is not " + prototype + "type");
		}
	}

}
