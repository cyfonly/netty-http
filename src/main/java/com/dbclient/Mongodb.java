package com.dbclient;

import java.util.ArrayList;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;

import com.config.Address;
import com.config.ServerConf;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * mongodb client demo
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class Mongodb {
	
	private static MongoClient client = null;
	private static final String TEST_MONGODB_NAME = "testmongo";
	
	public static MongoClient getClient() throws Exception{
		if(null == client){
			ServerConf cfg = ConfigFactory.create(ServerConf.class);
			Address[] addresses = cfg.addresses();
			List<ServerAddress> mongoAddr = new ArrayList<ServerAddress>();
			for(int i=0; i<addresses.length; i++){
				Address item = addresses[i];
				ServerAddress address = new ServerAddress(item.getIp(), item.getPort());
				mongoAddr.add(address);
			}
			
			boolean auth = cfg.auth();  //根据配置判断连接 Mongodb 时是否需要权限认证
			if(auth){
				List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
				String username = cfg.username();
				String password = cfg.password();
				MongoCredential credential = MongoCredential.createCredential(username, TEST_MONGODB_NAME, password.toCharArray());
				credentialsList.add(credential);
				client = new MongoClient(mongoAddr, credentialsList);  //备注：此处不会抛出Exception，即使创建MongoClient失败，这是驱动的锅……
			}else{
				client = new MongoClient(mongoAddr);
			}
			
			client.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
		}
		return client;
	}

}
