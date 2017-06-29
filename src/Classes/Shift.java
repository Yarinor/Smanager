package Classes;


import java.io.Serializable;
import java.util.*;


public class Shift extends ShiftTime implements Serializable {
    private String name;
    private String position;
    private Map<Integer,Employee> employeeList;

    public Shift(){
        this("ריק","ריק",0,0,0,0);
    }
    public Shift(String name,String position,int startHour,int startMin, int endHour,int endMin){
        //use ShiftTime to choose times by date type
        super(startHour,startMin,endHour,endMin,0,0,0);
        
        setName(name);
        setPosition(position);
        employeeList = new HashMap();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<Integer, Employee> getEmployeeList() {
        return employeeList;
    }
    public void setEmployeeList(Map<Integer, Employee> employeeList) {
        this.employeeList = employeeList;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
