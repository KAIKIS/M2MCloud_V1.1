package io.m2mcloud.www.test;

import io.m2mcloud.www.result.UserLoginResult;
import io.m2mcloud.www.users.UserLogin;

/**
 * �û���¼���ԣ�
 * 1������δע���û�
 * 2��������ע���û����������
 * 3��������ע���û����û�������������ȷ
 * @author Kai
 *
 */

public class UserLoginTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		UserLogin login = new UserLogin();
		
		//String result = login.findUser("18380421129", "3573551");
		//String result = login.findUser("zhkai0427", "3573551");//�ж�δע���û�
		//String result = login.findUser("zhkai0427@gmail.com", "qweqwe");//�ж��û�����ͬ�����벻ͬ
		String result = login.findUser("zhkai04271@gmail.com", "123456");//�ж��û�����ͬͬ��������ͬ
		if(result.equals("�������ݿ�ʧ��")){
			System.out.println(result);
		}
		else if(result.equals("�û������������")){
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
