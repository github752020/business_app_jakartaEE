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
public class Customer implements Serializable {
    
    private String cusid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String memo;

    public Customer(String cusid, String name, String password, String email, 
            String phone, String address, String memo) {
        this.cusid = cusid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memo = memo;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
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

    

    public String getCusid() {
        return cusid;
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
    
    public String getMemo() {
        return memo;
    }

}
