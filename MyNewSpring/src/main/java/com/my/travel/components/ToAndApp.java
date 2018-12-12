package com.my.travel.components;

import java.util.List;

public class ToAndApp {
String sight;
String imgaddr;


String trip;
//@JsonIgnore
List<String> addr;


public String getSight() {
	return sight;
}

public void setSight(String sight) {
	this.sight = sight;
}

public String getImgaddr() {
	return imgaddr;
}

public void setImgaddr(String imgaddr) {
	this.imgaddr = imgaddr;
}

public String getTrip() {
	return trip;
}

public void setTrip(String trip) {
	this.trip = trip;
}

public ToAndApp() {
	// TODO Auto-generated constructor stub
}

public String getSightname() {
	return sight;
}

public void setSightname(String sightname) {
	this.sight = sightname;
}

public List<String> getAddr() {
	return addr;
}

public void setAddr(List<String> addr) {
	this.addr = addr;
}

}
