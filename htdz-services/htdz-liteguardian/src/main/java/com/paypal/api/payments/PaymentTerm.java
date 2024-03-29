package com.paypal.api.payments;

import com.paypal.base.rest.PayPalModel;








public class PaymentTerm extends PayPalModel {

	/**
	 * The terms by which the invoice payment is due.
	 */
	private String termType;

	/**
	 * The date when the invoice payment is due. This date must be a future date. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String dueDate;

	/**
	 * Default Constructor
	 */
	public PaymentTerm() {
	}
}
