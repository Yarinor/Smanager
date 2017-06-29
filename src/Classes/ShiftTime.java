/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.*;


public abstract class ShiftTime implements Serializable {

    private Calendar StartTime;
    private Calendar endTime;
    private int shiftHours;
    private int shiftMin;
    
    public ShiftTime(){
        this(0,0,0,0,0,0,0);
    }
    
    public ShiftTime(int startHour,int startMin, int endHour,int endMin, int year,int month, int day){
        setStartTime(new GregorianCalendar(year,month-1,day,startHour,startMin));
        setEndTime(new GregorianCalendar(0,0,0,endHour,endMin));
        
        //calculate how many minutes and hours shift has
        int hours = endHour - startHour;
        if(hours<0)
            hours = 24+hours;
        int min = endMin - startMin;
        if(min<0){
            min = 60+min;
            hours=hours-1;
        }
        setShiftMin(min);
        setShiftHours(hours);
    }
    
    public Calendar getStartTime() {
        return StartTime;
    }
    public void setStartTime(Calendar StartTime) {
        this.StartTime = StartTime;
    }
    public Calendar getEndTime() {
        return endTime;
    }
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
    public int getShiftHours() {
        return shiftHours;
    }
    public void setShiftHours(int shiftHours) {
        this.shiftHours = shiftHours;
    }
    public int getShiftMin() {
        return shiftMin;
    }
    public void setShiftMin(int shiftMin) {
        this.shiftMin = shiftMin;
    }
}
