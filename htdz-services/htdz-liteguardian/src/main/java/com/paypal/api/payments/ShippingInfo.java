package com.paypal.api.payments;

import com.paypal.base.rest.PayPalModel;








public class ShippingInfo extends PayPalModel {

	/**
	 * The invoice recipient first name. Maximum length is 30 characters.
	 */
	private String firstName;

	/**
	 * The invoice recipient last name. Maximum length is 30 characters.
	 */
	private String lastName;

	/**
	 * The invoice recipient company business name. Maximum length is 100 characters.
	 */
	private String businessName;

	/**
	 * The invoice recipient address.
	 */
	private InvoiceAddress address;

	/**
	 * The invoice recipient phone number.
	 */
	private Phone phone;

	/**
	 * Default Constructor
	 */
	public ShippingInfo() {
	}
}
