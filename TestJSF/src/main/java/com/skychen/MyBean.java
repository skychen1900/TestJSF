package com.skychen;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class MyBean implements Serializable {
    private static final String GOOGLE = "https://www.google.co.jp/";
    private static final String YAHOO = "https://jp.yahoo.com/";
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

    public String prepareUrl2() {

        if (WebOptions.GOOGLE.equals(selectedOption)) {
            webOptionUrl = GOOGLE;
        } else if (WebOptions.YAHOO.equals(selectedOption)) {
            webOptionUrl = YAHOO;
        } else {
            webOptionUrl = "";
        }

        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("nextUrl", webOptionUrl);

        return "/pages/redirect/dummy.xhtml?faces-redirect=true";
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
