
package Classes;

import java.io.Serializable;
import java.util.*;


public class Limits extends ShiftTime implements Serializable {
 
    private List<String> shiftsNames;
    private String comment;
    
    public Limits(){
        this("---",0,0,0);
    }
    public Limits(String comment,int year,int month, int day){
        super(0,0,0,0,year,month,day);
        setComment(comment);
        shiftsNames = new ArrayList<String>();
    }
    

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<String> getShiftsNames() {
        return shiftsNames;
    }
    public void setShiftsNames(List<String> shiftsNames) {
        this.shiftsNames = shiftsNames;
    }
    
}
