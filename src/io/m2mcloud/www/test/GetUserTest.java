package io.m2mcloud.www.test;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.result.ProductResult;
import io.m2mcloud.www.result.UsersTokenResult;
import io.m2mcloud.www.users.GetUser;

/**
 * ��ȡ�û���Ϣ���ԣ�
 * 1����ȷ��tokenId
 * 2�������tokenId
 * 3������
 * @author Kai
 *
 */

public class GetUserTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		GetUser getUser = new GetUser();
		String result = getUser.getUserInfo("04985241A8CE4FC2A80389668B6F4D4C");
		//String result = getUser.getUserInfo("123");
		if(result.equals("�������ݿ�ʧ��")){
			
			System.out.println(result);
		}
		else if(result.equals("�û���δע��")){
			System.out.println(result);
			
		}
		else if(result.equals("��ȡ�û���Ϣ�ɹ�")){
			UsersTokenResult tokenResult = new UsersTokenResult();
			List<ProductResult> pr = new ArrayList<ProductResult>();
			int size = getUser.getUsers().getProducts().size();
			for (int i = 0; i < size; i++) {
				ProductResult temp = new ProductResult();
				temp.setProductId(getUser.getUsers().getProducts().get(i).getProductId());
				temp.setRole(getUser.getUsers().getProducts().get(i).getRole());
				temp.setProductCreateTime(getUser.getUsers().getProducts().get(i).getProductCreateTime());
				pr.add(temp);
			}
			tokenResult.setUserName(getUser.getUsers().getUserName());
			tokenResult.setUserCreateTime(getUser.getUsers().getCreateTime());
			tokenResult.setProductResult(pr);
			tokenResult.setResult(result);
			System.out.println(tokenResult.getUserName());
			System.out.println(tokenResult.getUserCreateTime());
			System.out.println(tokenResult.getProductResult());
			System.out.println(tokenResult.getResult());
		}
	}

}
