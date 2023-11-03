package ca.sheridancollege.waryad.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Calendar {

	private Long id;
	@NonNull
	private String title;
	private int noworkDay;
	private String dolistHoliday;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate hdayDate;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hdayTime;
	private String message;

	private String assignMyFestival(Integer noworkDay) {
		if (noworkDay >= 6)
			return "Diwali holidays";
		else if (noworkDay >= 15)
			return "Christmas holidays";
		else if (noworkDay >= 20)
			return "College holidays";
		else if (noworkDay >= 2)
			return "ThanksGivingDay";

		else
			return "Working Days";
	}

	public Calendar(String title, int noworkDay, String dolistHoliday, LocalDate hdayDate, LocalTime hdayTime,
			String message) {
		this.title = title;
		this.noworkDay = noworkDay;
		dolistHoliday = assignMyFestival(noworkDay);
		this.hdayDate = hdayDate;
		this.hdayTime = hdayTime;
		this.message = message;
	}

	public void setDay(Integer noworkDay) {
		this.noworkDay = noworkDay;
		dolistHoliday = assignMyFestival(noworkDay);
	}
}
