package io.m2mcloud.www.test;

import io.m2mcloud.www.result.UserRegisterResult;
import io.m2mcloud.www.users.UserRegister;

/**
 * 用户注册测试
 * 需要测试：
 * 1、多个用户注册
 * 2、用户名相同、密码相同
 * 3、用户名相同、密码不同
 * 4、用户名不同、密码相同
 * @author Kai
 *
 */

public class UserRegisterTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		UserRegister register = new UserRegister();
		
		//String result = register.insertUser("18380421129", "3573551");//执行两次判断重复注册有没有差别
		//String result = register.insertUser("18380421129", "123456");//判断用户名相同、密码不同
		String result = register.insertUser("zhkai0427@gmail.com", "123456");
		if(result.equals("连接数据库失败")){
			System.out.println(result);
		}
		else if(result.equals("用户注册成功")){
			UserRegisterResult userDoc = new UserRegisterResult();
			userDoc.setTokenId(register.getUsers().getTokenId());
			userDoc.setResult(result);
			System.out.println(userDoc.getTokenId());
			System.out.println(userDoc.getResult());
		}
		else if(result.equals("用户已经存在")){
			System.out.println(result);
		}
	}

}
