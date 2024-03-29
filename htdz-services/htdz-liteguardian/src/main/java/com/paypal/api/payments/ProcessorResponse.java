package com.paypal.api.payments;

import com.paypal.base.rest.PayPalModel;








public class ProcessorResponse extends PayPalModel {

	/**
	 * Paypal normalized response code, generated from the processor's specific response code
	 */
	private String responseCode;

	/**
	 * Address Verification System response code. https://developer.paypal.com/webapps/developer/docs/classic/api/AVSResponseCodes/
	 */
	private String avsCode;

	/**
	 * CVV System response code. https://developer.paypal.com/webapps/developer/docs/classic/api/AVSResponseCodes/
	 */
	private String cvvCode;

	/**
	 * Provides merchant advice on how to handle declines related to recurring payments
	 */
	private String adviceCode;

	/**
	 * Response back from the authorization. Provided by the processor
	 */
	private String eciSubmitted;

	/**
	 * Visa Payer Authentication Service status. Will be return from processor
	 */
	private String vpas;

	/**
	 * Default Constructor
	 */
	public ProcessorResponse() {
	}

	/**
	 * Parameterized Constructor
	 */
	public ProcessorResponse(String responseCode) {
		this.responseCode = responseCode;
	}
}
