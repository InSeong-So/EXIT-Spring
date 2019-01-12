package day1.toy.toy;

import day1.toy.battery.Battery;
import day1.toy.battery.NormalBattery;

public class ElectronicCarToy {

	private Battery battery;

	public ElectronicCarToy() {
		this.battery = new NormalBattery();
	}
}
