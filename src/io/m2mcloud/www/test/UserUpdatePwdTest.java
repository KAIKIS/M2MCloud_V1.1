package io.m2mcloud.www.test;

import io.m2mcloud.www.result.UserModifyPasswordResult;
import io.m2mcloud.www.users.UserUpdatePwd;

/**
 * �û���¼������¸���������ԣ�
 * 1������ȷ�������
 * 2��tokenId����
 * 3���û�������
 * 4�����������
 * 
 * @author Kai
 *
 */

public class UserUpdatePwdTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		UserUpdatePwd updatePwd = new UserUpdatePwd();
		
		String result = updatePwd.UpdatePwd("A4C24CDFE7D33396FD62B39425BDC13A", "18380421129", 
				"3573551", "123456");
//		String result = updatePwd.UpdatePwd("123456", "18380421129", 
//				"3573551", "123456");//tokenId����
//		String result = updatePwd.UpdatePwd("A4C24CDFE7D33396FD62B39425BDC13A", "123456", 
//				"3573551", "123456");//�û�������
//		String result = updatePwd.UpdatePwd("A4C24CDFE7D33396FD62B39425BDC13A", "18380421129", 
//				"qweqwe", "123456");
		if(result.equals("�������ݿ�ʧ��")){
			System.out.println(result);
		}
		else if(result.equals("�û�������û������������")){
			System.out.println(result);
		}
		else if(result.equals("�û���δע��")){
			System.out.println(result);
		}
		else if(result.equals("�û��޸�����ɹ�")){
			UserModifyPasswordResult modifyPasswordResult = new UserModifyPasswordResult();
			modifyPasswordResult.setTokenId(updatePwd.getUsers().getTokenId());
			modifyPasswordResult.setResult(result);
			System.out.println(modifyPasswordResult.getTokenId());
			System.out.println(modifyPasswordResult.getResult());
		}
		
	}

}
