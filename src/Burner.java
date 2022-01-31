public class Burner {
	public final static int TIME_DURATION = 2;
	int timer;
	private Setting mySetting;
	
	public enum Temperature{
		BLAZING, HOT, WARM, COLD;
	}
	private Temperature myTemperature;
	public Temperature getMyTemperature() {
		return myTemperature;
	}
	
	public Burner() {
		super();
		timer = 0;
		myTemperature = Temperature.COLD;
		mySetting = Setting.OFF;
	}
	public void plusButton() {
		switch (mySetting) {
		case OFF:
			mySetting = Setting.LOW;
			timer = TIME_DURATION;
			break;
		case LOW:
			mySetting = Setting.MEDIUM;
			timer = TIME_DURATION;
			break;
		case MEDIUM:
			mySetting = Setting.HIGH;
			timer = TIME_DURATION;
			break;
		default:
			break;
		}
	}
	public void minusButton() {
		switch (mySetting) {
		case LOW:
			mySetting = Setting.OFF;
			timer = TIME_DURATION;
			break;
		case MEDIUM:
			mySetting = Setting.LOW;
			timer = TIME_DURATION;
			break;
		case HIGH:
			mySetting = Setting.MEDIUM;
			timer = TIME_DURATION;
			break;
		default:
			break;
		}
	}
	
	public void tempUp() {
		switch (myTemperature) {
		case COLD:
			myTemperature = Temperature.WARM;
			break;
		case WARM:
			myTemperature = Temperature.HOT;
			break;
		case HOT:
			myTemperature = Temperature.BLAZING;
			break;
		default:
			break;
		}
	}
	public void tempDown() {
		switch (myTemperature) {
		case WARM:
			myTemperature = Temperature.COLD;
			break;
		case HOT:
			myTemperature = Temperature.WARM;
			break;
		case BLAZING:
			myTemperature = Temperature.HOT;
			break;
		default:
			break;
		}
	}
	public void tempChange() {
		switch (mySetting) {
		case OFF:
			tempDown();
			break;
		case LOW:
			if(myTemperature == Temperature.COLD) {
				tempUp();
			} else if (myTemperature != Temperature.WARM){
				tempDown();
			}
			break;
		case MEDIUM:
			if(myTemperature == Temperature.BLAZING) {
				tempDown();
			} else if (myTemperature != Temperature.HOT){
				tempUp();
			}
			break;
		case HIGH:
			tempUp();
			break;
		default:
			break;
		}
	}
	
	public void updateTemperature() {
		timer--;
		if(timer == 0) {
			tempChange();
			timer = TIME_DURATION;
		}
	}

	public void display() {
		String toShow = "";
		switch (myTemperature) {
		case COLD:
			toShow = "cooool";
			break;
		case WARM:
			toShow = "warm";
			break;
		case HOT:
			toShow = "CAREFUL";
			break;
		case BLAZING:
			toShow = "VERY HOT! DON'T TOUCH";
		default:
			break;
		}
		System.out.println(mySetting + "....." + toShow);
	}
}
