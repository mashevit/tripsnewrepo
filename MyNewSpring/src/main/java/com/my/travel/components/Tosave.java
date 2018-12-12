package com.my.travel.components;

public class Tosave{
	String addr;
	int trs;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getTrs() {
		return trs;
	}
	public void setTrs(int trs) {
		this.trs = trs;
	}
	public String toString() {
		return addr+"     "+trs;
		
		
	}
	public Tosave(int a, String b) {
		this.setAddr(b);
		this.setTrs(a);
		
	}
	
	public Tosave() {
		// TODO Auto-generated constructor stub
	}
	
}