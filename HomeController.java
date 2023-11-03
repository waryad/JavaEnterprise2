package ca.sheridancollege.waryad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.waryad.beans.Calendar;
import ca.sheridancollege.waryad.database.DatabaseAccess;

@Controller
public class HomeController {

	@Autowired
	private DatabaseAccess da;

	final String REST_URL = "http://localhost:8080/calendars/";

	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<Calendar[]> responseEntity = restTemplate.getForEntity(REST_URL, Calendar[].class);
		model.addAttribute("calendarList", responseEntity.getBody());
		model.addAttribute("calendar", new Calendar());
		model.addAttribute("calendarList", da.getCalendarList());
		return "index";
	}

	@GetMapping(value = "/getCalendar/{id}", produces = "application/json")
	@ResponseBody
	public Calendar getCalendar(@PathVariable int id, RestTemplate restTemplate) {
		ResponseEntity<Calendar> responseEntity = restTemplate.getForEntity(REST_URL + id, Calendar.class);
		return responseEntity.getBody();
	}

	@PostMapping("/insertCalendar")
	public String insertCalendar(Model model, @ModelAttribute Calendar calendar) {
		da.insertCalendar(calendar);
		model.addAttribute("calendar", new Calendar());
		model.addAttribute("calendarList", da.getCalendarList());
		return "index";
	}

}
