package io.m2mcloud.www.test;

import io.m2mcloud.www.result.UserRegisterResult;
import io.m2mcloud.www.users.UserRegister;

/**
 * �û�ע�����
 * ��Ҫ���ԣ�
 * 1������û�ע��
 * 2���û�����ͬ��������ͬ
 * 3���û�����ͬ�����벻ͬ
 * 4���û�����ͬ��������ͬ
 * @author Kai
 *
 */

public class UserRegisterTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		UserRegister register = new UserRegister();
		
		//String result = register.insertUser("18380421129", "3573551");//ִ�������ж��ظ�ע����û�в��
		//String result = register.insertUser("18380421129", "123456");//�ж��û�����ͬ�����벻ͬ
		String result = register.insertUser("zhkai0427@gmail.com", "123456");
		if(result.equals("�������ݿ�ʧ��")){
			System.out.println(result);
		}
		else if(result.equals("�û�ע��ɹ�")){
			UserRegisterResult userDoc = new UserRegisterResult();
			userDoc.setTokenId(register.getUsers().getTokenId());
			userDoc.setResult(result);
			System.out.println(userDoc.getTokenId());
			System.out.println(userDoc.getResult());
		}
		else if(result.equals("�û��Ѿ�����")){
			System.out.println(result);
		}
	}

}
