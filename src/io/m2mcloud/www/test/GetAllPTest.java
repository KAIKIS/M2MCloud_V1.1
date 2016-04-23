package io.m2mcloud.www.test;

import io.m2mcloud.www.products.GetAllProducts;

public class GetAllPTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		GetAllProducts products = new GetAllProducts();
		System.out.println(products.getAP("A4C24CDFE7D33396FD62B39425BDC13A"));
		System.out.println(products.getProducts().get(0).getProductName());
	}

}
