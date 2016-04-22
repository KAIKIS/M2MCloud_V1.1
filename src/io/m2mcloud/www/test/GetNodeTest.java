package io.m2mcloud.www.test;

import io.m2mcloud.www.devices.GetDataNode;
import io.m2mcloud.www.devices.GetNodeAlarm;

public class GetNodeTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		GetDataNode dataNode = new GetDataNode();
		dataNode.get("A4C24CDFE7D33396FD62B39425BDC13A", "6B1379A430621F8A1673CC42E4FEF1BE", "123");
		GetNodeAlarm alarm = new GetNodeAlarm();
		alarm.get("A4C24CDFE7D33396FD62B39425BDC13A", "6B1379A430621F8A1673CC42E4FEF1BE", "123");
		System.out.println(dataNode.getNode().size());
		System.out.println(alarm.getAlarms().size());
	}

}
