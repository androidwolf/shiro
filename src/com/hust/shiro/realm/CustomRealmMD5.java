package com.hust.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealmMD5 extends AuthorizingRealm {
	
	@Override
	public void setName(String name) {
		super.setName("customRealmMD5");
	}

	// 用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//第一步就是从token中取用户身份信息
		String userCode = (String) token.getPrincipal();
		
		//如果查询不到返回null
		//模拟在数据库中找不到用户
		if (!userCode.equals("zhangsansan")) {
			return null;
		}
		
		//第二步根据用户输入的userCode从数据库查询
		String password = "98cd6f146bdec65a48dedc505aaf2b80";
		String salt = "sadsgg";
		
		//如果查询到返回AuthenticationInfo
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = 
				new SimpleAuthenticationInfo(userCode, password, ByteSource.Util.bytes(salt),this.getName());
		
		return simpleAuthenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

}
