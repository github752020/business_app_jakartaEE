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

/**
 *
 * @author elau
 */
public class CustomerDTO implements Serializable {

    private final String cusid;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;

    public CustomerDTO(String cusid, String name, String email, String phone, String address) {
        this.cusid = cusid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getCusid() {
        return cusid;
    }

    public String getName() {
        return name;
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
}
