package com.hust.shiro.authentication;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Test {

	public static void main(String[] args) {
		
		String source = "123456";
		String salt = "sadsgg";
		int hashIterations = 1;
		
		//构造方法中，第一个参数是明文，原始密码
		//第二个参数就是盐，通过使用随机数
		//第三个参数是散列的次数
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		
		String password_md5 = md5Hash.toString();
		
		System.out.println(password_md5);
		
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		
		System.out.println(simpleHash.toString());
	}
	
}
