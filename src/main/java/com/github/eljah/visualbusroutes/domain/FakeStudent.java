package com.github.eljah.visualbusroutes.domain;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 10:48
 */
public class FakeStudent extends Student {
	public boolean isValid() {
		return "fake".equals(getPassword());
	}
}
