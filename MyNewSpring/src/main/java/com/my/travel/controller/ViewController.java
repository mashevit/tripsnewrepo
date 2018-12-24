package com.my.travel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.my.travel.components.ToAndApp;
import com.my.travel.components.toCSview;
import com.my.travel.dao.CityRepository;
import com.my.travel.dao.PicRepository;
import com.my.travel.dao.TripRepository;
import com.my.travel.dao.TripSightseeingRepository;
import com.my.travel.model2.Pic;
import com.my.travel.model2.Traveler;
import com.my.travel.model2.Trip;
import com.my.travel.model2.TripSightseeing;


@Controller
@RequestMapping(value = "/web/tripcity")
@SessionAttributes({ "imgcontent,idpage"} )

public class ViewController {
	
	
	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private TripSightseeingRepository tripSightseeingRepository;
	
	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private PicRepository picRepository;

	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private CityRepository cityRepository;
	
 	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private TripRepository tripRepository;
	private static boolean isweb=true;
	
	
	
	@RequestMapping(value = "/{id}")
	public String notesList(Model model,@PathVariable int id) {
		List<Trip> trlist=tripRepository.findByCityIdcitiesIn(id);
		if(trlist.size()>1) return "redirect:/web/tripcity/lg/"+id;
		System.out.println("dvdfd3333u");
		List<ToAndApp> tmpp=getpics(id);
		model.addAttribute("idpage", id);  
		model.addAttribute("imgcontent", tmpp);  
		model.addAttribute("listints", IntStream.rangeClosed(0, tmpp.size()-1)
			    .boxed().collect(Collectors.toList()));  
		if(isweb)
		return "cityxplorer2";//"tsts";
		return "Carousels";
	}
	
	
	@RequestMapping(value = "/one/{id}")
	public String onetrip(Model model,@PathVariable int id) {

		List<ToAndApp> tmpp=getpicslg(id);
		model.addAttribute("idpage", id);  
		model.addAttribute("imgcontent", tmpp);  
		model.addAttribute("listints", IntStream.rangeClosed(0, tmpp.size()-1)
			    .boxed().collect(Collectors.toList()));  
		if(isweb)
		return "cityxplorer2";//"tsts";
		return "Carousels";
	}
	
	
	@RequestMapping(value = "/all")
	public String alltr(Model model) {
		List<Trip> asa=tripRepository.findAll();
		model.addAttribute("trips",asa );  
		model.addAttribute("indsize",asa.size());
		return "triplinks";
	}
	
	
	
	public List<ToAndApp> getpics(int id){
		//todo: recycle code
		System.out.println("  trippic in view	");
		List<TripSightseeing> tripSightseeings=tripSightseeingRepository.findAllByTripCityIdcities(id);
		String globalc=cityRepository.findById(id).get().getCityName();
		List<ToAndApp> tmp=new ArrayList<ToAndApp>();
		int i=0;
		tripSightseeings.forEach(a->{
			List<Pic> pictmp= picRepository.findAllBytripSightseeingIdtripSightseeingIn(a.getIdtripSightseeing());
			ToAndApp andApp=new ToAndApp();
			andApp.setSightname(a.getSightseeing().getSightseeingsname());
			andApp.setAddr(pictmp.stream().map(bp->bp.getPicsAddr()).collect(Collectors.toList()));
		//	pictmp.stream().forEach(ab->{andApp.setImgaddr(ab.getPicsAddr());});
			andApp.setImgaddr("www://");
			andApp.setTrip(globalc);
			andApp.setSight(a.getSightseeing().getSightseeingsname());
			tmp.add(andApp);
	
			
		});
		
		return tmp;

	}

	
	public List<ToAndApp> getpicstrip(int id){
		//todo: recycle code
		System.out.println("  trippic in view	");
		List<TripSightseeing> tripSightseeings=tripSightseeingRepository.findAllByTripIdtrip(id);
		//String globalc=cityRepository.findById(id).get().getCityName();
		Trip trip=tripRepository.getOne(id);
		List<ToAndApp> tmp=new ArrayList<ToAndApp>();
		int i=0;
		tripSightseeings.forEach(a->{
			List<Pic> pictmp= picRepository.findAllBytripSightseeingIdtripSightseeingIn(a.getIdtripSightseeing());
			ToAndApp andApp=new ToAndApp();
			andApp.setSightname(a.getSightseeing().getSightseeingsname());
			andApp.setAddr(pictmp.stream().map(bp->bp.getPicsAddr()).collect(Collectors.toList()));
		//	pictmp.stream().forEach(ab->{andApp.setImgaddr(ab.getPicsAddr());});
			andApp.setImgaddr("");
			andApp.setTrip(trip.nameCompat());
			andApp.setSight(a.getSightseeing().getSightseeingsname());
			if(pictmp.size()>0)
			tmp.add(andApp);
			
			
		});
		
		return tmp;

	}
	
	
	
	
	public List<ToAndApp> getpicslg(int id){
		//todo: recycle code
		System.out.println("  trippic in view	");
		List<TripSightseeing> tripSightseeings=tripSightseeingRepository.findAllByTripIdtrip(id);
		//String globalc=cityRepository.findById(id).get().getCityName();
		Trip trip=tripRepository.getOne(id);
		List<ToAndApp> tmp=new ArrayList<ToAndApp>();
		int i=0;
		tripSightseeings.forEach(a->{
			List<Pic> pictmp= picRepository.findAllBytripSightseeingIdtripSightseeingIn(a.getIdtripSightseeing());
			ToAndApp andApp=new ToAndApp();
			andApp.setSightname(a.getSightseeing().getSightseeingsname());
			andApp.setAddr(pictmp.stream().map(bp->bp.getPicsAddr()).collect(Collectors.toList()));
		//	pictmp.stream().forEach(ab->{andApp.setImgaddr(ab.getPicsAddr());});
			andApp.setImgaddr("");
			andApp.setTrip(trip.nameCompat());
			andApp.setSight(a.getSightseeing().getSightseeingsname());
			if(pictmp.size()>0)
			tmp.add(andApp);
			
			
		});
		
		return tmp;

	}
	
	
	

	@RequestMapping(value = "/chng")
	public String changeview(Model model, HttpSession session) {
		isweb=!isweb;
		return "redirect:/web/main";
		
	}
	
	
	@RequestMapping(value = "/lg/{id}")
	public String notesList2(Model model,@PathVariable int id) {
		model.addAttribute("idpage", id);  
		List<toCSview> ls =new ArrayList<toCSview>();
		List<Trip> trlist=tripRepository.findByCityIdcitiesIn(id);
		//if(trlist.size()>1) return "redirect:/web/tripcity/lg/"+id;
		System.out.println("dvdfd3333u");
		int count=0;
		for(int i=0;i<trlist.size();i++) {
			
		toCSview cSview=new toCSview();
		List<ToAndApp> tmpp = getpicslg(trlist.get(i).getIdtrip());
		cSview.setContentlst(tmpp);
		cSview.setIlst(IntStream.rangeClosed(count, tmpp.size()-1+count)
			    .boxed().collect(Collectors.toList()));
		cSview.setTrip(tripRepository.getOne(id));
		cSview.setNum(count*30);
		ls.add(cSview);
		count=tmpp.size()+count;
		}
		model.addAttribute("csimgcontent", ls);  
		if(!isweb)
		return "Carousels";//"cityexplorer3";		//"tsts";
		return "cityexplorer3";
	}
	
	
	
}
