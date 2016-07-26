package com;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.ServerConf;
import com.handlers.HttpServerhandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 基于 netty5 的 http1.1 服务端
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class HttpServer {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	
	private final int port;
	public HttpServer(int port){
		this.port = port;
	}
	
	public void run() throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                public void initChannel(SocketChannel ch) throws Exception {
	                   ch.pipeline().addLast("codec",new HttpServerCodec())  //或者使用HttpRequestDecoder & HttpResponseEncoder
	                   		.addLast("aggregator",new HttpObjectAggregator(1024*1024))  //在处理 POST消息体时需要加上
	                   		.addLast("handler",new HttpServerhandler());  //业务handler
	                }
	            })
	            .option(ChannelOption.SO_BACKLOG, 1024)
	            .childOption(ChannelOption.SO_KEEPALIVE, true)
	            .childOption(ChannelOption.TCP_NODELAY, true);
			ChannelFuture future = bootstrap.bind(port).sync();
			
			logger.info("Netty-http server listening on port " + port);
			
			future.channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception{
		ServerConf cfg = ConfigFactory.create(ServerConf.class);
		int port;
		if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }else{
            port = cfg.port();
        }
		new HttpServer(port).run();
	}

}
