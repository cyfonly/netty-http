package com.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

/**
 * 系统配置文件接口类，使用 OWNER api
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
@Sources("classpath:ServerConf.properties")
public interface ServerConf extends Config{
	
	@DefaultValue("dev")
	String env();
	
	@Key("server.${env}.port")
	@DefaultValue("8080")
	Integer port();
	
	@Key("server.${env}.mongo")
	@DefaultValue("127.0.0.1:27017, 127.0.0.1:27018, 127.0.0.1:27019")
	@ConverterClass(AddressConverter.class)
	Address[] addresses();
	
	@Key("server.${env}.mongo.auth")
	@DefaultValue("false")
	Boolean auth();
	
	@Key("server.${env}.mongo.username")
	@DefaultValue("admin")
	String username();
	
	@Key("server.${env}.mongo.password")
	@DefaultValue("123456")
	String password();
	
	@Key("server.${env}.filedir")
	@DefaultValue("D:\\")
	String filedir();
	
}
