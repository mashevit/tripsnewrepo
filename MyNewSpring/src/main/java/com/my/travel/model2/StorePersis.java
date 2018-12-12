package com.my.travel.model2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "storepersis")
public class StorePersis {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	public StorePersis() {
		super();
	}

	@Column(name = "catname")
	private String catname;
	
	public StorePersis(String catname, int data1ind, int data2ind) {
		super();
		this.catname = catname;
		this.data1ind = data1ind;
		this.data2ind = data2ind;
	}

	@Column(name = "data1ind")
	private int data1ind;
	
	@Column(name = "data2ind")
	private int data2ind;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public int getData1ind() {
		return data1ind;
	}

	public void setData1ind(int data1ind) {
		this.data1ind = data1ind;
	}


	public int getData2ind() {
		return data2ind;
	}

	public void setData2ind(int data2ind) {
		this.data2ind = data2ind;
	}
	
	
	

}
