package com.paypal.api.payments;

import java.util.ArrayList;
import java.util.List;


public class WebProfileList  extends ArrayList<WebProfile> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<WebProfile> webProfiles = null;

	/**
	 * Default Constructor
	 */
	public WebProfileList() {
		webProfiles = new ArrayList<WebProfile>();
	}

}