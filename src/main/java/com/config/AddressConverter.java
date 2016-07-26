package com.config;

import java.lang.reflect.Method;
import org.aeonbits.owner.Converter;

/**
 * mongo address converter demo
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class AddressConverter implements Converter<Address>{
	
	@Override
	public Address convert(Method method, String input) {
		String[] split = input.split(":");
		String ip = split[0];
		int port = Integer.valueOf(split[1]);
		return new Address(ip, port);
	}

}
