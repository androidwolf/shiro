package com.hust.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("customRealm");
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
		String password = "111111";
		
		
		//如果查询到返回AuthenticationInfo
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = 
				new SimpleAuthenticationInfo(userCode, password, this.getName());
		
		
		
		return simpleAuthenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//principals获取主身份信息
		//将getPrimaryPrincipal方法返回值强转为真实身份类型
		String userCode = (String) principals.getPrimaryPrincipal();
		
		//根据身份信息获取权限信息
		//模拟数据
		List<String> permissions = new ArrayList<String>();
		permissions.add("user:create");
		permissions.add("items:update");
		
		//查到权限数据，返回
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		return simpleAuthorizationInfo;
	}

}
