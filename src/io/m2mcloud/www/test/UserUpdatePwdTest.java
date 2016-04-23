package io.m2mcloud.www.test;

import io.m2mcloud.www.result.UserModifyPasswordResult;
import io.m2mcloud.www.users.UserUpdatePwd;

/**
 * 用户登录的情况下更改密码测试：
 * 1、在正确的情况下
 * 2、tokenId错误
 * 3、用户名错误
 * 4、旧密码错误
 * 
 * @author Kai
 *
 */

public class UserUpdatePwdTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		UserUpdatePwd updatePwd = new UserUpdatePwd();
		
		String result = updatePwd.UpdatePwd("A4C24CDFE7D33396FD62B39425BDC13A", "18380421129", 
				"3573551", "123456");
//		String result = updatePwd.UpdatePwd("123456", "18380421129", 
//				"3573551", "123456");//tokenId错误
//		String result = updatePwd.UpdatePwd("A4C24CDFE7D33396FD62B39425BDC13A", "123456", 
//				"3573551", "123456");//用户名错误
//		String result = updatePwd.UpdatePwd("A4C24CDFE7D33396FD62B39425BDC13A", "18380421129", 
//				"qweqwe", "123456");
		if(result.equals("连接数据库失败")){
			System.out.println(result);
		}
		else if(result.equals("用户输入的用户名或密码错误")){
			System.out.println(result);
		}
		else if(result.equals("用户还未注册")){
			System.out.println(result);
		}
		else if(result.equals("用户修改密码成功")){
			UserModifyPasswordResult modifyPasswordResult = new UserModifyPasswordResult();
			modifyPasswordResult.setTokenId(updatePwd.getUsers().getTokenId());
			modifyPasswordResult.setResult(result);
			System.out.println(modifyPasswordResult.getTokenId());
			System.out.println(modifyPasswordResult.getResult());
		}
		
	}

}
