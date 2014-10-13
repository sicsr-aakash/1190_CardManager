package com.cardmanager;

public class Card {
	private int card_id;
	private String card_no;
	private String card_name;
	private String card_holder_name;
	private String card_validity;
	private String card_pin;
	private String card_cvv;
	private String card_bank;
	private String card_type;
	private String card_user_email;
	private String card_password;
	
	public Card(String card_no, String card_name, String card_holder_name,
			String card_validity, String card_password, String card_pin, String card_cvv,
			String card_bank, String card_type, String card_user_email) {
		super();
		this.card_no = card_no;
		this.card_name = card_name;
		this.card_holder_name = card_holder_name;
		this.card_validity = card_validity;
		this.card_pin = card_pin;
		this.card_cvv = card_cvv;
		this.card_bank = card_bank;
		this.card_type = card_type;
		this.card_password = card_password;
		this.card_user_email = card_user_email;
	}
	
	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getCard_holder_name() {
		return card_holder_name;
	}
	public void setCard_holder_name(String card_holder_name) {
		this.card_holder_name = card_holder_name;
	}
	public String getCard_validity() {
		return card_validity;
	}
	public void setCard_validity(String card_validity) {
		this.card_validity = card_validity;
	}
	public String getCard_pin() {
		return card_pin;
	}
	public void setCard_pin(String card_pin) {
		this.card_pin = card_pin;
	}
	public String getCard_cvv() {
		return card_cvv;
	}
	public void setCard_cvv(String card_cvv) {
		this.card_cvv = card_cvv;
	}
	public String getCard_bank() {
		return card_bank;
	}
	public void setCard_bank(String card_bank) {
		this.card_bank = card_bank;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getCard_user_email() {
		return card_user_email;
	}
	public void setCard_user_email(String card_user_email) {
		this.card_user_email = card_user_email;
	}
	public String getCard_password() {
		return card_password;
	}
	public void setCard_password(String card_password) {
		this.card_password = card_password;
	}
	
	
	
}
