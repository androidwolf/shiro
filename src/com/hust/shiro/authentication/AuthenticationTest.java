package com.hust.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthenticationTest {

	// 用户的登录和退出
	@Test
	public void testLoginLogout() {

		// 创建securityManager工厂，通过ini文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");

		// 创建securityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// SecurityUtils里面创建一个subject
		Subject subject = SecurityUtils.getSubject();

		// 在认证提交前要准备token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken("zhaangsan", "111111");

		// 执行认证提交
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名未注册");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过: " + isAuthenticated);

		// 退出操作
		subject.logout();

		// 是否认证通过
		isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过: " + isAuthenticated);
	}

	// 自定义realm
	@Test
	public void testCustomRealm() {

		// 创建securityManager工厂，通过ini文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

		// 创建securityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// SecurityUtils里面创建一个subject
		Subject subject = SecurityUtils.getSubject();

		// 在认证提交前要准备token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken("zhaangsan", "111111");

		// 执行认证提交
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名未注册");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过: " + isAuthenticated);

		// 退出操作
		subject.logout();

		// 是否认证通过
		isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过: " + isAuthenticated);
	}
	
	// 自定义realm实现散列匹配
	@Test
	public void testCustomRealmMD5() {
		
		// 创建securityManager工厂，通过ini文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
		
		// 创建securityManager
		SecurityManager securityManager = factory.getInstance();
		
		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		
		// SecurityUtils里面创建一个subject
		Subject subject = SecurityUtils.getSubject();
		
		// 在认证提交前要准备token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsansan", "123456");
		
		// 执行认证提交
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名未注册");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();
		
		System.out.println("是否认证通过: " + isAuthenticated);
		
		// 退出操作
		subject.logout();
		
		// 是否认证通过
		isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过: " + isAuthenticated);
	}

}
