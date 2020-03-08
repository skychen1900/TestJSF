package com.skychen;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "com.skychen.WebOptionConverter", forClass = WebOptionConverter.class)
//@ViewScoped
public class WebOptionConverter implements Converter, Serializable {
	/** the serialVersionUID      **/
	private static final long serialVersionUID = -218581226063576481L;

	public WebOptionConverter() {
		super();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return WebOptions.getOptionByOptoin(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof WebOptions) {
			final WebOptions objectStatus = (WebOptions) value;
			return objectStatus.getWebOption();
		}
		return "";
	}
}