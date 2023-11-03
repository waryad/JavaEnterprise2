package ca.sheridancollege.waryad.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.waryad.beans.Calendar;

@SpringBootTest
@AutoConfigureTestDatabase
public class TestDatabaseAccess {
    
    @Autowired
    private DatabaseAccess da;
    
    @Test
    public void whenInsertCalendar_getCalendarListIsOneGreater(){
       //setup
        int size = da.getCalendarList().size();
        Calendar calendar = new Calendar();
        calendar.setTitle("Deepinder");
        
        //when
        da.insertCalendar(calendar);
        
        
        //then(the actual test!)
        assertEquals(da.getCalendarList().size(),++size);
    }
}