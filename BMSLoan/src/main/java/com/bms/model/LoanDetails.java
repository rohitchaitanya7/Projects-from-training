package com.bms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table
public class LoanDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@JsonIgnore
	@OneToOne(mappedBy = "loanDetails")
	private Loan loan_no;
	@Column
	private double courseFee;
	@Column
	private String course;
	@Column
	private String fatherName;
	@Column
	private String fatherOccupation;
	@Column
	private int fatherTotalExp;
	@Column
	private int fatherCurrentCompanyExp;
	@Column
	private String rationCard;
	@Column
	private double FatherAnnualIncome;
	@Column
	private double AnnualIncome;
	@Column
	private String companyName;
	@Column
	private String designation;
	@Column
	private int TotalExp;
	@Column
	private int ExpWithCurrentCompany;
}
