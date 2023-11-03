package ca.sheridancollege.waryad.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.waryad.beans.Calendar;
import ca.sheridancollege.waryad.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/calendars") // http://localhost:8080/calendars
public class CalendarController {

	private DatabaseAccess da;

	@GetMapping
	public List<Calendar> getCalendarCollection() {
		return da.findAll();
	}
	@GetMapping("/{id}")
	public Calendar getIndividualCalendar(@PathVariable Long id) {
	    return da.findById(id);
	}

//	@GetMapping("/id")
//	public Calendar getIndividualCalendar(@PathVariable Long id) {
//		return da.findById(id);
//	}

	@PostMapping(consumes = "application/json")
	public String postCalendar(@RequestBody Calendar calendar) {
		return "http://localhost:8080/calendars/" + da.save(calendar);
	}

	@PutMapping(consumes = "application/json")
	public String putCalendarCollection(@RequestBody List<Calendar> calendarList) {
		da.deleteAll();
		da.saveAll(calendarList);
		return "Total Records: " + da.count();
	}
}
