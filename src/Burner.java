public class Burner {
	public final static int TIME_DURATION = 2;        // static constant declared by the assignment requirements
	int timer;                                        // Used to track if the temperature should change after time updates
	private Setting mySetting;                        // mySetting is an enum from the Setting.java file which contains the setting of the burner
	
	public enum Temperature{
		BLAZING, HOT, WARM, COLD;                     // Class enum to show the current temperature of the burner.
	}
	private Temperature myTemperature;                // myTemperature is like mySetting but for the temperature of the burner
	public Temperature getMyTemperature() {
		return myTemperature;                         // This return value is used in the displayStove function to see if any are of BLAZING temperature
	}
	
	public Burner() {
		super();                                  
		timer = 0;                                    // By default, the timer is 0, burner is cold and off, so this constructor initializes burners to that state.
		myTemperature = Temperature.COLD;
		mySetting = Setting.OFF;
	}
	public void plusButton() {
		switch (mySetting) {                          // A switch statement evaluates the current setting of the burner and increments it upwards by 1 setting
		case OFF:
			mySetting = Setting.LOW;                  // off goes to low, low to medium, medium to high.
			timer = TIME_DURATION;                    // each refreshes the timer to the constant
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
			break;                                    // This default leaves everything alone, so HIGH won't change if the plusButton is pressed 
		}
	}
	public void minusButton() {
		switch (mySetting) {
		case LOW:                                     // minusButton functions almost identically to the plusButton, but incrementing downwards
			mySetting = Setting.OFF;                  // so low -> off, med -> low, etc . . .
			timer = TIME_DURATION;                    // This method also resets the timer
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
			break;                                   // We have a default do nothing, which should make the OFF setting not change if this button is pressed with the Setting is OFF
		}
	}
	
	public void tempUp() {
		switch (myTemperature) {                  // tempUp and tempDown are helper functions for tempChange, which is a helper to updateTemperature
		case COLD:                                // it simply increments the temperature of the burner upwards
			myTemperature = Temperature.WARM;     // meaning it does not touch timer or anything else
			break;
		case WARM:
			myTemperature = Temperature.HOT;
			break;
		case HOT:
			myTemperature = Temperature.BLAZING;
			break;
		default:                                 // default means it cannot go past blazing, and will leave it alone
			break;
		}
	}
	public void tempDown() {
		switch (myTemperature) {
		case WARM:
			myTemperature = Temperature.COLD;         // increments myTemperature downwards for the tempChange function and has a default for COLD where nothing is touched
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
		switch (mySetting) {                               // This is a helper function for the update temperature method
		case OFF: 
			tempDown();                                    // if this function is called and the burner is off, the temperature of the burner should decrease
			break;                                         // this will not decrease past cold due to the tempDown function
		case LOW:
			if(myTemperature == Temperature.COLD) {         // if the temperature is cold, and the setting is low this should increase temperature
				tempUp();
			} else if (myTemperature != Temperature.WARM){  // otherwise, as long as it is not warm (the setting for low heat) it should decrease
				tempDown();
			}
			break;
		case MEDIUM:
			if(myTemperature == Temperature.BLAZING) {      // if the temperature is BLAZING, it should be brought down to a level appropriate to the medium setting
				tempDown();
			} else if (myTemperature != Temperature.HOT){   // which would be hot, if it is too low, it is brought up
				tempUp();
			}
			break;
		case HIGH:
			tempUp();                                        // finally, if the setting is high, the temperature should always increase
			break;                                           // will not exceed blazing because of the tempUp function
		default:
			break;
		}
	}
	
	public void updateTemperature() {                  // called by the stove class to simulate the passing of time, uses the tempUp, tempDown, and tempChange functions to aid it
		timer--;                                       // timer will always decrease because time has passed
		if(timer == 0) {                               // once the timer reaches 0
			tempChange();                              // we call the tempChange function which raises or lowers the temperature as appropriate
			timer = TIME_DURATION;                     // then it resets the timer. 
		}
	}

	public void display() {                            // called by displayStove to show the temperature and setting of the current burner
		String toShow = "";
		switch (myTemperature) {
		case COLD:
			toShow = "cooool";                         // This switch and toString functions allow the function to display the corresponding message for each burner temperature available
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
			break;                                    // default will never happen
		}
		System.out.println(mySetting + "....." + toShow); // this is a void function so we can print here all of the important information in the appropriate way as shown by the rubric
	}
}
