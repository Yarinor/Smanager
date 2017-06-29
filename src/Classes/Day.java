package Classes;


import java.io.Serializable;
import java.util.*;


public class Day implements Serializable  {

    private Calendar date;
    private List<Shift> shiftList;
    
    public Day(){
        this(new GregorianCalendar());
    }
    public Day(Calendar date){
        setDate(date);
        shiftList = new ArrayList();
    }

    
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }
    public List<Shift> getShiftList() {
        return shiftList;
    }
    public void setShiftList(List<Shift> ShiftList) {
        this.shiftList = ShiftList;
    }

}
