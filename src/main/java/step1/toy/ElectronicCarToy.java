package step1.toy;

import step1.battery.Battery;
import step1.battery.NormalBattery;

public class ElectronicCarToy {

	private Battery battery;

	public ElectronicCarToy() {
		this.battery = new NormalBattery();
	}
}
