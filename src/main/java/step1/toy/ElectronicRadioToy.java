package step1.toy;

import step1.battery.Battery;

public class ElectronicRadioToy {

	private Battery battery;

	public ElectronicRadioToy(Battery battery) {
		this.battery = battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}
}
