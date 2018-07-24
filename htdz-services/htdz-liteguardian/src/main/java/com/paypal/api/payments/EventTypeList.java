package com.paypal.api.payments;

import java.util.ArrayList;
import java.util.List;

import com.paypal.base.rest.PayPalModel;


public class EventTypeList  extends PayPalModel {

	/**
	 * A list of Webhooks event-types
	 */
	private List<EventType> eventTypes;

	/**
	 * Default Constructor
	 */
	public EventTypeList() {
		eventTypes = new ArrayList<EventType>();
	}
}
