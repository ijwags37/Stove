public enum Setting {
	OFF ("---"), LOW ("--+"), MEDIUM ("-++"), HIGH ("+++");
	private String value;
	
	Setting(String value){
		this.value = value;
	}
	
	public String toString() {
		return "[" + value + "]";
	}
}
