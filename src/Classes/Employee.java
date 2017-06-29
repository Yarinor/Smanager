package Classes;

import java.io.Serializable;


public class Employee implements Serializable {
    
    //job options
    public enum jobsList {
    Admin{public String toString() {
          return "מנהל ראשי";
    }},
    Manager{public String toString() {
          return "מנהל";
      }},
    Worker{public String toString() {
          return "עובד";
      }}
    }
    
    private String firstName;
    private String lastName;
    private int id;
    private String phone;
    private String address;
    private boolean active;
    private jobsList job;
    private String passWord;
    
    
    public Employee(){
        this("ריק","ריק",0,"ריק","ריק",true,jobsList.Worker);
    }
    public Employee(String firstName,String lastName, int id,String phone, String address,boolean active,jobsList jobVal){
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
        setPhone(phone);
        setAddress(address);
        setActive(active);
        setJob(jobVal);
        //default password, can be changed later
        setPassWord("1234");
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public jobsList getJob() {
        return job;
    }
    public void setJob(jobsList job) {
        this.job = job;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    
    @Override
    public String toString(){
        String status;
        if(this.active=true)
        {
            status = "yes";
        }
        else
        {
            status = "no";
        }
        return "Employee Name: "+ firstName + " Employee Id: " + id + " Employee phone: " + id + " active: " + status + " role: " + job;
    }
}
