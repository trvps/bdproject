package com.paypal.api.payments;

import com.paypal.base.rest.PayPalModel;


public class ErrorDetails extends PayPalModel {

	/**
	 * Name of the field that caused the error.
	 */
	private String field;

	/**
	 * Reason for the error.
	 */
	private String issue;

	/**
	 * @deprecated This property is not publicly available
	 * Reference ID of the purchase_unit associated with this error
	 */
	@Deprecated
	private String purchaseUnitReferenceId;

	/**
	 * @deprecated This property is not publicly available
	 * PayPal internal error code.
	 */
	@Deprecated
	private String code;


	/**
	 * Default Constructor
	 */
	public ErrorDetails() {
	}

	/**
	 * Parameterized Constructor
	 */
	public ErrorDetails(String field, String issue) {
		this.field = field;
		this.issue = issue;
	}
}
