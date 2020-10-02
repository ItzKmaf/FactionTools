package com.kmaf.TrenchTools.Config;

public class InvalidConfigurationValue extends InvalidConfiguration {
	
	private final String valuePath;
	private final String reason;
	
	public InvalidConfigurationValue(Config config, String valuePath, String reason) {
		super(config);
		this.valuePath = valuePath;
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		String previous = super.toString();
		previous += "\n&cThe Configuration value with the key: &a" + valuePath + " &cis Malformed.";
		previous += "\n&8Reason: &c" + reason;
		return previous;
	}
}
