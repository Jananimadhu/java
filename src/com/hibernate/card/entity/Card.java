package com.hibernate.card.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name = "cards")
public class Card {
	
	@Column(name="CARD_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="CARD_NO")
	private String cardNo;
	
	@Column(name="EXP_DATE")
	private String expDate;
	
	@Column(name="SERVICE_CODE")
	private int serviceCode;

	@Column(name="CVV")
	private int cvv;

	public Card(java.lang.String cardNo, java.lang.String expDate, int serviceCode, int cvv) {
		this.cardNo = cardNo;
		this.expDate = expDate;
		this.serviceCode = serviceCode;
		this.cvv = cvv;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public java.lang.String getCardNo() {
		return cardNo;
	}

	public void setCardNo(java.lang.String cardNo) {
		this.cardNo = cardNo;
	}

	public java.lang.String getExpDate() {
		return expDate;
	}

	public void setExpDate(java.lang.String expDate) {
		this.expDate = expDate;
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Card{" +
				"Id=" + Id +
				", cardNo=" + cardNo +
				", expDate=" + expDate +
				", serviceCode=" + serviceCode +
				", cvv=" + cvv +
				'}';
	}
}
