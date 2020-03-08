package com.skychen;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class MyBean implements Serializable {
	private static final String GOOGLE = "https://www.google.co.in/";
	private static final String YAHOO = "https://in.yahoo.com/";
	private WebOptions selectedOption;
	private String webOptionUrl;

	public void prepareUrl() {
		if (WebOptions.GOOGLE.equals(selectedOption)) {
			webOptionUrl = GOOGLE;
		} else if (WebOptions.YAHOO.equals(selectedOption)) {
			webOptionUrl = YAHOO;
		} else {
			webOptionUrl = "";
		}
	}

	public WebOptions getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(WebOptions selectedOption) {
		this.selectedOption = selectedOption;
	}

	public String getWebOptionUrl() {
		return webOptionUrl;
	}

	public void setWebOptionUrl(String webOptionUrl) {
		this.webOptionUrl = webOptionUrl;
	}

	public List<WebOptions> getAllWebOptions() {
		return Arrays.asList(WebOptions.values());
	}

}
