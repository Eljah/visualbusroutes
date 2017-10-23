package com.github.eljah.visualbusroutes.config;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

public class GoogleAppEngineKeyPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(StringUtils.isNotBlank(text)
				? KeyFactory.stringToKey(text.trim())
				: null);
	}

	@Override
	public String getAsText() {
		return getValue() != null
				? KeyFactory.keyToString((Key)getValue())
				: null;
	}
}