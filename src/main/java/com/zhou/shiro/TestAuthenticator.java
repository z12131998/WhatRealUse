package com.zhou.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author 周俊宇
 */
public class TestAuthenticator {
	public static void main(String[] args) {
		//1.创建安全管理器 SecurityManager
		DefaultSecurityManager securityManager = new DefaultSecurityManager();

		//2.给安全管理器设置realm realm数据从哪里来,这里我选择测试 在类路径下进行
		securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
		//Ini可以操作进行自定义

		//3.securityUtils 全局安全工具类 ,给他设置安全管理器
		SecurityUtils.setSecurityManager(securityManager);

		//4.关键对象 subject主体
		Subject subject = SecurityUtils.getSubject();

		//5.用户认证,需要传达Token
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("xiaochen","123");
		try{
			System.out.println("认证状态"+subject.isAuthenticated());
			subject.login(usernamePasswordToken);
			System.out.println("认证状态"+subject.isAuthenticated());
		}catch (UnknownAccountException e){
			e.printStackTrace();
			System.out.println("用户不存在");
		}catch (IncorrectCredentialsException e){
			e.printStackTrace();
			System.out.println("密码错误");
		}

	}
}
