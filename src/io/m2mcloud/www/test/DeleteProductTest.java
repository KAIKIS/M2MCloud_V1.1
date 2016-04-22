package io.m2mcloud.www.test;

import io.m2mcloud.www.devices.CreateDevices;
import io.m2mcloud.www.products.DeleteProduct;

public class DeleteProductTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		CreateDevices devices = new CreateDevices();
//		System.out.println(devices.insertDevice("2BFE01657FAA6425F3E4C1D974026250", "F735E34F2AC0675D97D5FA3523432D4E",
//				"123456789", "测试设备1", "asdasgf", "12.45", "18.45"));
		
		DeleteProduct deleteProduct = new DeleteProduct();
		System.out.println(deleteProduct.delete("A4C24CDFE7D33396FD62B39425BDC13A", "9EB39765F58CD1A409A75B628DA61531"));
		
	}

}
