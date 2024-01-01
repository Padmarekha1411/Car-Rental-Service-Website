package com.example.CarServiceProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.CarServiceProject.bean.Booking_Details;
import com.example.CarServiceProject.bean.ContactUs;
import com.example.CarServiceProject.bean.Get_Email;
import com.example.CarServiceProject.bean.Registration;
import com.example.CarServiceProject.bean.Rent_Details;
import com.example.CarServiceProject.bean.Reviews;
import com.example.CarServiceProject.bean.car_rent;
import com.example.CarServiceProject.dao.BookingRepo;
import com.example.CarServiceProject.dao.CarRepo;
import com.example.CarServiceProject.dao.CarrentRepo;
import com.example.CarServiceProject.dao.RegRepo;
import com.example.CarServiceProject.dao.RentdetailsRepo;
import com.example.CarServiceProject.dao.ReviewRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class carcontroller {

	@Autowired
	CarRepo cr;
	
	@RequestMapping("/index")
	public List<car_rent> car(HttpSession session) {
		List<car_rent> cars = new ArrayList<>();
		
		for(car_rent car:crr.findAll()) {
			
			cars.add(car);
		}
		
		session.setAttribute("carlist", cars);
		
		List<Reviews> review = new ArrayList<>();
		
		for(Reviews reviews:pm.findAll()) {
			
			review.add(reviews);
		}
		
		session.setAttribute("reviews", review);
		
		return cars;
	}
	
	
	
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@Autowired
	BookingRepo br;
	
	
	@Autowired
	RentdetailsRepo rrr;
	@RequestMapping("/booking")
	public List<Rent_Details> bookcar(Rent_Details r,HttpSession session,Registration r3) {
		
		rrr.save(r);
		List<Rent_Details> rent=new ArrayList<>();

		for(Rent_Details r1:rrr.findAll()) {
			
			rent.add(r1);
		}
		
		session.setAttribute("rent", rent);
		
		
		
		ModelAndView mv=new ModelAndView("booking");
		
		return rent;
	}
	@Autowired
	
private JavaMailSender javaMailSender;
	
	@RequestMapping("/bookingdetails")
	public String bookingdetails(Booking_Details bd,Get_Email em) throws MessagingException {
	
		br.save(bd);
		ModelAndView mv=new ModelAndView();
		mv.addObject("rent", br);
		System.out.println(bd.getEmail());
		System.out.println(bd.getFirst_name());
		 try {
			   
			  MimeMessage messege=javaMailSender.createMimeMessage();
			  MimeMessageHelper helper=new MimeMessageHelper(messege);
			  helper.setFrom("padmarekhaj18@gmail.com");
			  helper.setTo(bd.getEmail());
			  helper.setSubject("Your Car Booking Confirmed !");
               helper.setText("Dear"+ bd.getFirst_name()+",\n"
			  		+ "Exciting news! Your car booking has been successfully confirmed. We're thrilled to have you on board. Get ready for a fantastic journey ahead! If you have any queries, feel free to reach out.\r\n"
			  		+ "Best regards,\n"
			  		+ "Royal Cars");
			 
			  System.out.println("email");
			  javaMailSender.send(messege);
			  
		  }catch(Exception e) {
			  System.out.println("catch");
			  e.printStackTrace();
		  }
		 
		return "redirect:/index";
	
	}
	
	@Autowired
	CarrentRepo crr;
	
	
	
	@RequestMapping("/car")
	public List<car_rent> getcars(HttpServletRequest request) {
		List<car_rent> cars = new ArrayList<>();
		
		for(car_rent car:crr.findAll()) {
			
			cars.add(car);
		}
		
		request.setAttribute("carlist", cars);
		return cars;
	}
	
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/contactus")
	public String contactus(ContactUs cu) {
	
		cr.save(cu);
		ModelAndView mv = new ModelAndView();
		mv.addObject("Contact",cu);
		return "index";	
	}
	
	@RequestMapping("/detail")
	public List<car_rent> getcar(HttpSession session) {
		List<car_rent> cars = new ArrayList<>();
		
		for(car_rent car:crr.findAll()) {
			
			cars.add(car);
		}
		
		session.setAttribute("carlist", cars);
		return cars;
	}
	
	@RequestMapping("/service")
	public String service() {
		return "service";
	}
	
	@RequestMapping("/team")
	public String team() {
		return "team";
	}
	

	
	@Autowired
	RegRepo rr;
	
	@RequestMapping("/register")
	public String register() {
		return "login";
	}
	
	@PostMapping("/registerform")
	public String register(Registration r) {
	
		rr.save(r);
		ModelAndView mv = new ModelAndView();
		mv.addObject("Registration",r);
		return "index";	
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/loginform")
	public String loginuser(Registration r,HttpSession session) {
		
		System.out.println(r.getEmail());
		Iterable<Registration> itr = rr.findAll();
		ModelAndView mv = new ModelAndView();
		
		int count=0;
		for(Registration r1:itr ) {
		 
		if(r1.getEmail().equals(r.getEmail()) && r1.getPassword().equals(r.getPassword()))	{
		Optional<Registration> optional = rr.findById(r.getEmail());
		
		Registration r2 = optional.get();
		
		session.setAttribute("user", r1);
		return "redirect:/index";
		}else {
			count++;
		}
		
		
		
		}
		if(count>0) {

			System.out.print("failed");

			session.setAttribute("failedlogin", "Invalid email or password");
			return "login";
		}		
	
		
		return "login";
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		
		return "index";
	}

	@Autowired
	ReviewRepo pm;
	
	
	
	@RequestMapping("/testimonial")
	public List<Reviews> getreviews(HttpServletRequest request) {
		List<Reviews> review = new ArrayList<>();
		
		for(Reviews reviews:pm.findAll()) {
			
			review.add(reviews);
		}
		
		request.setAttribute("reviews", review);
		return review;
	}
	
	@RequestMapping("/showbooking")
	public String showbooking(Registration r,HttpSession session) {
		
		int sum=0,count=0;
		Iterable<Rent_Details> itr=rrr.findAll();
		List<Rent_Details> list=new ArrayList<>();
		for(Rent_Details rd:itr) {
			list.add(rd);
		}
		
		session.setAttribute("rent", list);
		
		
		
		return "booking";	
	}
	
	@RequestMapping("/delete")
	public String delete(Rent_Details r,HttpSession session) {
		
		System.out.println(r.getId());
		rrr.deleteById(r.getId());
		Iterable<Rent_Details> itr=rrr.findAll();
		List<Rent_Details> list=new ArrayList<>();
		for(Rent_Details rd:itr) {
			list.add(rd);
		}
		
		session.setAttribute("rent", list);
		
		return "booking";
	}
	
	
	
	
}
