public enum Setting {
	OFF ("---"), LOW ("--+"), MEDIUM ("-++"), HIGH ("+++"); // Each setting has its own constructor
	private String value;                                   // Made possible through the value string
	
	Setting(String value){ 
		this.value = value;                                 // Constructor that uses the string value
	}
	
	public String toString() {
		return "[" + value + "]";                           // This is basically the toString function of this enum, it will be used to conveniently print the value of the setting
	}
}
