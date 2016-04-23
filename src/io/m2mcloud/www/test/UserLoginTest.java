package io.m2mcloud.www.test;

import io.m2mcloud.www.result.UserLoginResult;
import io.m2mcloud.www.users.UserLogin;

/**
 * 用户登录测试：
 * 1、测试未注册用户
 * 2、测试已注册用户，密码错误
 * 3、测试已注册用户，用户名错误，密码正确
 * @author Kai
 *
 */

public class UserLoginTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		UserLogin login = new UserLogin();
		
		//String result = login.findUser("18380421129", "3573551");
		//String result = login.findUser("zhkai0427", "3573551");//判断未注册用户
		//String result = login.findUser("zhkai0427@gmail.com", "qweqwe");//判断用户名相同、密码不同
		String result = login.findUser("zhkai04271@gmail.com", "123456");//判断用户名不同同、密码相同
		if(result.equals("连接数据库失败")){
			System.out.println(result);
		}
		else if(result.equals("用户名或密码错误")){
			System.out.println(result);
		}
		else{
			UserLoginResult userDoc = new UserLoginResult();
			userDoc.setTokenId(login.getUsers().getTokenId());
			userDoc.setResult(result);
			System.out.println(userDoc.getTokenId());
			System.out.println(userDoc.getResult());
		}
		
	}
}
