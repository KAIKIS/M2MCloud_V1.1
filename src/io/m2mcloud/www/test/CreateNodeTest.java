package io.m2mcloud.www.test;

import io.m2mcloud.www.devices.CreateDataAlarm;
import io.m2mcloud.www.devices.CreateDataNode;

public class CreateNodeTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		CreateDataNode node = new CreateDataNode();
//		node.insertNode("2BFE01657FAA6425F3E4C1D974026250", "177B2C09FBB073807598AD9B4238864C", "321", 
//				"abc", "asd", "asd", "asd");
		CreateDataAlarm alarm = new CreateDataAlarm();
		System.out.println(alarm.insertNode("2BFE01657FAA6425F3E4C1D974026250", "177B2C09FBB073807598AD9B4238864C", "321",
				"900150983CD24FB0D6963F7D28E17F72", "qwe", "qwe", 12, 21));
	}

}
