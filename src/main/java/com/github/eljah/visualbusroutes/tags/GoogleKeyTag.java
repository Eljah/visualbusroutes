package com.github.eljah.visualbusroutes.tags;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: pawel
 * Date: 24.07.13
 * Time: 22:27
 */
public class GoogleKeyTag extends TagSupport {
	private Key key;
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().append(KeyFactory.keyToString(key));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
}
