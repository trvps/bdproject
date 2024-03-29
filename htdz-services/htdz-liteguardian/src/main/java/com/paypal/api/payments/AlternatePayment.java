package com.paypal.api.payments;

import com.paypal.base.rest.PayPalModel;



public class AlternatePayment extends PayPalModel {

	/**
	 * The unique identifier of the alternate payment account.
	 */
	private String alternatePaymentAccountId;

	/**
	 * The unique identifier of the payer
	 */
	private String externalCustomerId;

	/**
	 * Alternate Payment provider id. This is an optional attribute needed only for certain alternate providers e.g Ideal
	 */
	private String alternatePaymentProviderId;

	/**
	 * Default Constructor
	 */
	public AlternatePayment() {
	}

	/**
	 * Parameterized Constructor
	 */
	public AlternatePayment(String alternatePaymentAccountId) {
		this.alternatePaymentAccountId = alternatePaymentAccountId;
	}
}
