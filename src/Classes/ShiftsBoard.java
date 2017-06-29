package Classes;


import java.io.Serializable;
import java.util.*;


public class ShiftsBoard implements Serializable {
    private Calendar date;
    private Map<String,Day> daysList;  
    
    
    public ShiftsBoard(int year,int month,int day) {
        
        //get all week dates
        setDate(new GregorianCalendar(year,month,day));
        Calendar dateMonday = new GregorianCalendar(year,month,day);
        Calendar dateTuesday = new GregorianCalendar(year,month,day);
        Calendar dateWednesday = new GregorianCalendar(year,month,day);
        Calendar dateThursday = new GregorianCalendar(year,month,day);
        Calendar dateFriday = new GregorianCalendar(year,month,day);
        Calendar dateSaturday = new GregorianCalendar(year,month,day);
        
        //save all dates
        dateMonday.add(Calendar.DATE,1);
        dateTuesday.add(Calendar.DATE,2);
        dateWednesday.add(Calendar.DATE,3);
        dateThursday.add(Calendar.DATE,4);
        dateFriday.add(Calendar.DATE,5);
        dateSaturday.add(Calendar.DATE,6);
        
        //make all week days list with dates
        daysList = new HashMap();
        daysList.put("Sunday",new Day(new GregorianCalendar(year,month,day)));
        daysList.put("Monday", new Day(dateMonday));
        daysList.put("Tuesday", new Day(dateTuesday));
        daysList.put("Wednesday", new Day(dateWednesday));
        daysList.put("Thursday", new Day(dateThursday));
        daysList.put("Friday", new Day(dateFriday));
        daysList.put("Saturday", new Day(dateSaturday));
    }
      
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }
    public Map<String, Day> getDaysList() {
        return daysList;
    }
    public void setDaysList(Map<String, Day> daysList) {
        this.daysList = daysList;
    }
    

}
