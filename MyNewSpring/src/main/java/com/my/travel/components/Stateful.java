package com.my.travel.components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.my.travel.model2.TripSightseeing;


public  class Stateful {
	public static boolean a = false;
	public static List<Tosave> imgdetails;

	public Stateful() {
		// TODO Auto-generated constructor stub{
		if (!a)
			imgdetails = new Vector<>();
		a = true;
		// TODO Auto-generated constructor stub
	}

	public void addItem(String imgaddr, TripSightseeing tripsight) {
		Tosave tosave = new Tosave();
		tosave.setAddr(imgaddr);
		tosave.setTrs(tripsight.getIdtripSightseeing());
		imgdetails.add(tosave);
	}

	public static void clearItems() {
		imgdetails.clear();
	}

	public void removeItem(String ind) {
		int indi = Integer.parseInt(ind);
		imgdetails.remove(indi);
	}

	public List<Tosave> listItems() {
		return imgdetails;
	}

}
