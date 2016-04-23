package io.m2mcloud.www.test;

import io.m2mcloud.www.products.GetAllProducts;
import io.m2mcloud.www.products.GetOneProduct;

public class GetAllPTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		GetAllProducts products = new GetAllProducts();
//		System.out.println(products.getAP("A4C24CDFE7D33396FD62B39425BDC13A"));
//		System.out.println(products.getProducts().get(0).getProductName());
		
		GetOneProduct product = new GetOneProduct();
		System.out.println(product.getOP("04985241A8CE4FC2A80389668B6F4D4C", "8C04DA5D13D116EEE24D4966E452E811"));
	}

}
