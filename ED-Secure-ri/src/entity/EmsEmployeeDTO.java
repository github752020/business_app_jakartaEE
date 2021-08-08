/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Data Transfer Object - EmsEmployeeDTO
 *
 * This is the DTO for EmsEmployee entity class
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author elau
 */
public class EmsEmployeeDTO implements Serializable {
    
    private final String empid;
    private final String name;
    private final String password;
    private final String email;
    private final String phone;
    private final String address;
    private final String secqn;
    private final String secans;
    private final String bsbid;
    private final String accid;
    private final BigDecimal salary;
    private final String appgroup;
    private final Boolean active;
    private final String memo;

    public EmsEmployeeDTO(String empid, String name, String password, 
            String email, String phone, String address, String secqn, String secans, 
            String bsbid, String accid, BigDecimal salary, 
            String appgroup, Boolean active, String memo) {
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

    public BigDecimal getSalary() {
        return salary;
    }

    public String getAppgroup() {
        return appgroup;
    }

    public Boolean getActive() {
        return active;
    }

    public String getMemo() {
        return memo;
    }
    
}
