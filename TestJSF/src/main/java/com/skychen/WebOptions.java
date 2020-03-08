package com.skychen;

public enum WebOptions {
	GOOGLE("google"), YAHOO("yahoo");
	private String webOption;

	WebOptions(String webOption) {
		this.webOption = webOption;
	}

	public static WebOptions getOptionByOptoin(String value) {
		if (WebOptions.GOOGLE.webOption.equals(value)) {
			return WebOptions.GOOGLE;
		}
		return WebOptions.YAHOO;
	}

	public String getWebOption() {
		return webOption;
	}
}
