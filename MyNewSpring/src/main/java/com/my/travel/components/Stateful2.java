package com.my.travel.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.travel.dao.CityRepository;
import com.my.travel.dao.PicRepository;
import com.my.travel.dao.SightseeingRepository;
import com.my.travel.dao.TripRepository;
import com.my.travel.dao.TripSightseeingRepository;
import com.my.travel.model2.Pic;
import com.my.travel.model2.TripSightseeing;

@Component
public class Stateful2 {
	

	

	
	
	
	public static boolean a = false;
	public static List<Tosave> imgdetails=new ArrayList<Tosave>();
public Stateful2() {
	// TODO Auto-generated constructor stub
}
	public Stateful2(int id) {
		// TODO Auto-generated constructor stub{

		// TODO Auto-generated constructor stub
		//this.init(id);
	}

	public void addItem(String imgaddr, TripSightseeing tripsight) {
		Tosave tosave = new Tosave();
		tosave.setAddr(imgaddr);
		tosave.setTrs(tripsight.getIdtripSightseeing());
		imgdetails.add(tosave);
	}

	public void clearItems() {
		imgdetails.clear();
	}

	public void removeItem(String ind) {
		int indi = Integer.parseInt(ind);
		imgdetails.remove(indi);
	}

	public List<Tosave> listItems() {
		return imgdetails;
	}

	public void init(List<Pic> listp,int tripsightid) {
	//	List<Pic> listp=picRepository.findAllBytripSightseeingIdtripSightseeingIn(tripsightid);
		

		imgdetails.clear();
		
		
	    listp.forEach(
	           p-> {
	                if (p.getPicsAddr().length()>40 ) {
	                	imgdetails.add(new Tosave(tripsightid,p.getPicsAddr().substring(p.getPicsAddr().length()-40)));
	                } else {
	                	imgdetails.add(new Tosave(tripsightid,p.getPicsAddr()));
	                }
	            }
	    );
	/*	listp.stream().map(v ->  {
		      if (v.getPicsAddr().length()>40) {
		          return new Tosave(tripsightid,v.getPicsAddr().substring(v.getPicsAddr().length()-40));
		        } else {
		          return new Tosave(tripsightid,v.getPicsAddr());
		        }).collect(Collectors.toList());*/
		
	}
}
