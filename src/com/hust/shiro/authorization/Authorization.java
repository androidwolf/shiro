package com.hust.shiro.authorization;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 授权测试
 * 
 * @author Administrator
 */
public class Authorization {

	// 角色授权、资源授权测试
	@Test
	public void testAuthorization() {

		// 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将SecurityManager设置到系统中
		SecurityUtils.setSecurityManager(securityManager);

		// 创建subject
		Subject subject = SecurityUtils.getSubject();

		// 创建token令牌
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

		// 执行认证
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名未注册");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		System.out.println("认证状态：" + subject.getPrincipal());

		// 认证通过后执行授权

		// 基于角色的授权
		boolean isHasRole = subject.hasRole("role1");
		System.out.println("isHasRole" + isHasRole);

		boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1","role2"));
		System.out.println("hasAllRoles" + hasAllRoles);
		
		//使用check方法进行授权，如果授权不通过会排出异常
		try {
			subject.checkRole("role123");
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
		
		// 基于资源的授权
		boolean isPermitted = subject.isPermitted("user:create");
		System.out.println("isPermitted" + isPermitted);
		
		//使用check方法进行授权，如果授权不通过抛出异常
		try {
			subject.checkPermission("items:create");
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
	}
	
	
	// 自定义realm资源授权测试
	@Test
	public void testAuthorizationRealm() {
		
		// 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		
		// 将SecurityManager设置到系统中
		SecurityUtils.setSecurityManager(securityManager);
		
		// 创建subject
		Subject subject = SecurityUtils.getSubject();
		
		// 创建token令牌
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsansan", "111111");
		
		// 执行认证
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名未注册");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		System.out.println("认证状态：" + subject.isAuthenticated());
		
		// 认证通过后执行授权
		
		/*// 基于角色的授权
		boolean isHasRole = subject.hasRole("role1");
		System.out.println("isHasRole" + isHasRole);
		
		boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1","role2"));
		System.out.println("hasAllRoles" + hasAllRoles);
		
		//使用check方法进行授权，如果授权不通过会排出异常
		try {
			subject.checkRole("role123");
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}*/
		
		// 基于资源的授权
		boolean isPermitted = subject.isPermitted("user:create");
		System.out.println("isPermitted" + isPermitted);
		
		//使用check方法进行授权，如果授权不通过抛出异常
		try {
			subject.checkPermission("items:update");
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
	}
}
