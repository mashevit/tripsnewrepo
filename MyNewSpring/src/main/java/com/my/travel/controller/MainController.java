package com.my.travel.controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.my.travel.dao.CityRepository;
import com.my.travel.dao.PicRepository;
import com.my.travel.dao.StorePersisRepository;
import com.my.travel.dao.TravelerRepository;
import com.my.travel.dao.TripRepository;
import com.my.travel.model2.City;
import com.my.travel.model2.Pic;
import com.my.travel.model2.StorePersis;
import com.my.travel.model2.Traveler;
import com.my.travel.model2.Trip;

// This means that this class is a Controller
@Controller // This means URL's start with /demo (after Application path)
@SessionAttributes({ "users", "iliaUser", "requestedUser" })
//@RequestMapping(value = "/web")
public class MainController {
	// This means to get the bean called userRepository
	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private TravelerRepository travelerRepository;
	
	
	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private TripRepository tripRepository;
	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private PicRepository picRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StorePersisRepository storePersisRepository;

	@RequestMapping(value = "/")
	public String notesList(Model model) {
		System.out.println("dvdfd");
		if (!(model.containsAttribute("requestedUser")))
			return "redirect:/process-init";
		else
			return "fileone";
	}

	@PostMapping("/selectuser")
	public String select(@RequestParam Traveler nameoftraveler, Model model) {

		model.addAttribute("requestedUser", nameoftraveler);
		return "fileone";
	}

	@RequestMapping("/removeuser")
	public String removeuser(@ModelAttribute("requestedUser") Traveler requestedUser,
			@ModelAttribute("iliaUser") Traveler iliaUser, Model model) {
		if (requestedUser.getIdtraveler() != iliaUser.getIdtraveler())
			;
		travelerRepository.delete(requestedUser);
		model.addAttribute("requestedUser", iliaUser);
		return "fileone";
	}

	public List<Traveler> listAll() {
		List<Traveler> counts = new ArrayList<>();
		travelerRepository.findAll().forEach(counts::add);
		return counts;
	}

	@RequestMapping(value = "/process-init")
	public ModelAndView processPerson(/* @ModelAttribute Person person */) {

		System.out.println("asasdsad21212");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fileone");

		modelAndView.addObject("requestedUser", getiliaUser());
		modelAndView.addObject("selectedOption", new Traveler());
		return modelAndView;

	}


	@RequestMapping(value = "/savenewtrip", method = RequestMethod.POST)
	public String savenewtrip(@ModelAttribute("newtrip") Trip tr, Model model, HttpSession session) {
	System.out.println(tr);
	tr.setTraveler((Traveler)session.getAttribute("requestedUser"));
	tripRepository.save(tr);
		return "fileone";
		
	}
	
	
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String developersAdd(@RequestParam String name, @RequestParam Date birthdate, Model model) {
		Traveler newDeveloper = new Traveler();
		newDeveloper.setTravelerBirthdate(birthdate);
		newDeveloper.setTravelerName(name);
			
		travelerRepository.save(newDeveloper);
		List<Traveler> users = new ArrayList<>();
		users = listAll();
		model.addAttribute("users", users);
		System.out.println("asasdsad");

		return "fileone";
	}

	@RequestMapping(value = "/addcity", method = RequestMethod.POST)
	public String addcity(@RequestParam String newcity, Model model) {
		City city = new City();
		city.setCityName(newcity);
		cityRepository.save(city);
		/*
		 * List<Traveler> users = new ArrayList<>(); users = listAll();
		 * model.addAttribute("users", users); System.out.println("asasdsad");
		 */
		model.addAttribute("cities", getcities());
		return "fileone";
	}

	@ModelAttribute("iliaUser")
	public Traveler getiliaUser() {
		return travelerRepository.findBytravelerNameContainingIgnoreCase("Ilia M").get(0);
	}

	/*
	 * @ModelAttribute("requestedUser") public List<Traveler> getusers() { return
	 * travelerRepository.findAll(); }
	 */

	@ModelAttribute("frontp")
	public List<Object> get3pics() {
		List<Object> ans = new ArrayList<Object>();
		List<StorePersis> touse = storePersisRepository.findBycatname("frontp");
		if (touse.size() >= 3) {
			for (int i = 0; i < 3; i++) {
				StorePersis sp = touse.get(i);
				ans.add(new Object[] { picRepository.getOne(sp.getData1ind()),
						cityRepository.getOne(sp.getData2ind()) });
			}

		} else {
			ans = chkpics(); // picRepository.findFrontPics(PageRequest.of(0, 3/*, Sort.by(...)));
			for (int i = 0; i < 3; i++) {
				Object a = ans.get(i);
				Pic p = (Pic) ((Object[]) a)[0];
				City c = (City) ((Object[]) a)[1];

				System.out.println(p);
				StorePersis sp = new StorePersis("frontp", p.getIdpics(), c.getIdcities());
				storePersisRepository.save(sp);
				// ans.add(new Object[] { picRepository.getOne(sp.getData1ind()),
				// cityRepository.getOne(sp.getData2ind()) });
			}

		}
		return ans;
	}

	@ModelAttribute("users")
	public List<Traveler> getusers() {
		return travelerRepository.findAll();
	}

	@ModelAttribute("newtrip")
	public Trip newtrip() {
		return new Trip();
	}
	
	
	@ModelAttribute("cities")
	public List<City> getcities() {
		return cityRepository.findAll();
	}

	
	  @ModelAttribute("frontcities") 
	  public List<City> getfrontcities() {
		  return comcity();
	  }
			  	
	  @ModelAttribute("utrips")
	  public List<Trip> getutrips() {
		  return utrips();
	  }
	  
	  @ModelAttribute("frtr")
	  public List<List<Trip>> getfrtr() {
		  return frontTrips();
	  }
	  
/*	  @ModelAttribute("sampleTrip")
	  public Trip getsmtr() {
		  return tripRepository.getOne(1);
	  }*/
/*	  @InitBinder
	  public void initDateBinder(final WebDataBinder binder) {
	         binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true));
	  }
	  */
	 
	final double neededratio = 1.777777777;

	public List<Object> chkpics() {
		List<Object> ans = new ArrayList<Object>();
		List<Object[]> tmp = picRepository.findFrontPics(PageRequest.of(0, 6));
		City curc = (City) tmp.get(0)[1];
		Pic q = (Pic) tmp.get(0)[0];
		int ind = 0;
		List<Pic> tmplst = null;
		int b = 177;
	

		int countans = 0;
		String str = null;
		while (ind < 5 && countans < 3) {
			boolean catched = false;
			curc = (City) tmp.get(ind)[1];
			q = (Pic) tmp.get(ind)[0];
			System.out.println(ind + "   iind");
			str = (q).getPicsAddr();
			System.out.println(str);
			if (str.length() >= 9 && str.substring(str.length() - 3).equalsIgnoreCase("jpg")) {
				try {
					URL url1 = new URL(str);

					// TODO Auto-generated catch block

					BufferedImage image1 = ImageIO.read(url1.openStream());
					boolean tmpd = false;

					double ratio = 1.0 * image1.getWidth() / image1.getHeight();
					int a = (int) (ratio * 100);
					System.out.println("f");
					double diff = Math.abs(a - b);

					System.out.println(a + " sf sf f s    " + b + " ");
					if (diff <= 3) {
						System.out.println(a + " " + b + " ");
						System.out.println("trueeeeeeeeee");
						countans++;
						tmpd = true;// Numbers are close enough

					}
					if (str.length() >= 9 && str.substring(str.length() - 3).equalsIgnoreCase("jpg") && tmpd) {

						ans.add(new Object[] { q, curc });
	
						System.out.println("3");
						tmplst = null;

					} else {
						catched = true;
					}

				} catch (Exception e) {
					System.out.println("e");

			}

			} else {
				catched = true;
			}
			if (catched) {
				tmplst = picRepository.findTop5ByTripSightseeingTripCityIdcities(curc.getIdcities());
				for (int i = 0; i < 5; i++) {
					q = tmplst.get(i);
					String str1 = q.getPicsAddr();

					URL url2;

					System.out.println(str1 + "   iadri");
					System.out.println(i + "   ii");
					if (str1.length() >= 9 && str1.substring(str1.length() - 3).equalsIgnoreCase("jpg")) {
						try {
							url2 = new URL(q.getPicsAddr());

							// TODO Auto-generated catch block

							BufferedImage image2 = ImageIO.read(url2.openStream());

							boolean tmpd = false;

							double ratio = 1.0 * image2.getWidth() / image2.getHeight();
							int a = (int) (ratio * 100);
							System.out.println("f1");
							double diff = Math.abs(a - b);

							System.out.println(a + " sf sf f s    d " + b + " ");
							if (diff <= 3) {
								System.out.println(a + " " + b + " ");
								System.out.println("trueeeeeeeeee");
								countans++;
								tmpd = true;// Numbers are close enough

							}
							if (str1.length() >= 9 && str1.substring(str1.length() - 3).equalsIgnoreCase("jpg")
									&& tmpd) {
								ans.add(new Object[] { q, curc });
								i = 6;

							}

						} catch (Exception e) {
							i++;
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			}
			ind++;
		}
		// City curc=(City) tmp.get(ind)[1];

		// Object[] a1 = tmp.get(ind);

		return ans;
	}

	
	public List<Trip> utrips(){
		return tripRepository.uniqueTrips(PageRequest.of(0, 3));	
	}
	
	public List<City> comcity(){
		return cityRepository.commoncities(PageRequest.of(0, 3));	
	}	
	
	public List<List<Trip>> frontTrips(){
		List<List<Trip>> ans=new ArrayList<List<Trip>>();
		List<City> a= comcity();
		ans.add( tripRepository.findByCityIn(a.get(0)));
		ans.add( tripRepository.findByCityIn(a.get(1)));
		ans.add( tripRepository.findByCityIn(a.get(2)));

		return ans;
	}	
}