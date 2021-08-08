/**
 * ED - Secure - jdbc
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - EmsEmployee
 *
 * This is the class that holds the information for the database record
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package secure;

import java.io.Serializable;
import util.MyHash;

/**
 *
 * @author elau
 */
public class EmsEmployee implements Serializable {
    
    private String empid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String secqn;
    private String secans;
    private String bsbid;
    private String accid;
    private double salary;
    private String appgroup;
    private boolean active;
    private String memo;

    public EmsEmployee(String empid, String name, String password, String email, 
            String phone, String address, String secqn, String secans, 
            String bsbid, String accid, double salary, 
            String appgroup, boolean active, String memo) {
        this.empid = empid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.secqn = secqn;
        this.secans = secans;
        this.bsbid = bsbid;
        this.accid = accid;
        this.salary = salary;
        this.appgroup = appgroup;
        this.active = active;
        this.memo = memo;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String sha256Password) {
        this.password = sha256Password;
    }
    
    public void setPasswordFromPlainPassword(String plainPassword) {
        this.password = MyHash.getSHA256HashedString(plainPassword);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSecqn(String secqn) {
        this.secqn = secqn;
    }

    public void setSecAns(String secans) {
        this.secans = secans;
    }

    public void setBsbid(String bsbid) {
        this.bsbid = bsbid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public void setAppGroup(String appgroup) {
        this.appgroup = appgroup;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmpid() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getSecqn() {
        return secqn;
    }

    public String getSecans() {
        return secans;
    }

    public String getBsbid() {
        return bsbid;
    }

    public String getAccid() {
        return accid;
    }

    public double getSalary() {
        return salary;
    }
    
    public String getAppgroup() {
        return appgroup;
    }

    public boolean isActive() {
        return active;
    }
    
    public String getMemo() {
        return memo;
    }

}
