package ca.sheridancollege.waryad.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.waryad.beans.Calendar;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public List<Calendar> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM calendar";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Calendar>(Calendar.class));
	}

	public Calendar findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM calendar WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Calendar>(Calendar.class)).get(0);
	}

	public Long save(Calendar calendar) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

		String query = " INSERT INTO calendar(title, noworkDay, dolistHoliday, hdayDate, hdayTime, message) VALUES(:title, :noworkDay, :dolistHoliday, :hdayDate, :hdayTime, :message)";
		namedParameters.addValue("title", calendar.getTitle());
		namedParameters.addValue("noworkDay", calendar.getNoworkDay());
		namedParameters.addValue("dolistHoliday", calendar.getDolistHoliday());
		namedParameters.addValue("hdayDate", calendar.getHdayDate());
		namedParameters.addValue("hdayTime", calendar.getHdayTime());
		namedParameters.addValue("message", calendar.getMessage());

		jdbc.update(query, namedParameters, generatedKeyHolder);

		return (Long) generatedKeyHolder.getKey();
	}

	public void deleteAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "DELETE FROM calendar";
		jdbc.update(query, namedParameters);
	}

	public Long count() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT count(*) FROM calendar";

		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}

	public void saveAll(List<Calendar> calendarList) {
		for (Calendar s : calendarList) {
			save(s);
		}
	}

	public void insertCalendar(Calendar calendar) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = " INSERT INTO calendar(title, noworkDay, dolistHoliday, hdayDate, hdayTime, message) VALUES(:title, :noworkDay, :dolistHoliday, :hdayDate, :hdayTime,:message)";

		namedParameters.addValue("title", calendar.getTitle());
		namedParameters.addValue("noworkDay", calendar.getNoworkDay());
		namedParameters.addValue("dolistHoliday", calendar.getDolistHoliday());
		namedParameters.addValue("hdayDate", calendar.getHdayDate());
		namedParameters.addValue("hdayTime", calendar.getHdayTime());
		namedParameters.addValue("message", calendar.getMessage());

		jdbc.update(query, namedParameters);

	}

	public List<Calendar> getCalendarList() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM calendar ";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Calendar>(Calendar.class));
	}

	public List<Calendar> getCalendarById(Long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM calendar WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Calendar>(Calendar.class));

	}
}
