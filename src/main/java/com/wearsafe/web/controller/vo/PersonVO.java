package com.wearsafe.web.controller.vo;

import java.sql.Timestamp;
import java.util.List;


/**
 * 
 * @author nagendra
 *
 */
public class PersonVO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Timestamp doe;
	private Timestamp dom;
	
	public PersonVO() {
		
	}
	
	public PersonVO(Long id, String firstName, String lastName, String email, Timestamp doe, Timestamp dom) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.doe = doe;
		this.dom = dom;
	}

	private List<AddressVO> addresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	public Timestamp getDom() {
		return dom;
	}

	public void setDom(Timestamp dom) {
		this.dom = dom;
	}

	public List<AddressVO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressVO> addresses) {
		this.addresses = addresses;
	}

}
