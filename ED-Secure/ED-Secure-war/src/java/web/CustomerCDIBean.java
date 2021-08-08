/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.CustomerDTO;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import session.CustomerManagementRemote;

/**
 *
 * @author user
 */
@Named(value = "customerCDIBean")
@ConversationScoped
public class CustomerCDIBean implements Serializable {

    @EJB
    private CustomerManagementRemote customerManagement;

    @Inject
    private Conversation conversation;

    private String cusid;
    private String name;
    private String email;
    private String phone;
    private String address;

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
    public CustomerCDIBean() {
    }
    
    private boolean isNull(String s) {
        return (s == null);
    }
    
    public String displayCustomer() {
        // check cusid is null
        System.out.println(cusid);
        if (isNull(cusid) || conversation == null) {
            return "debug";
        }

        return setCustomerDetails();
    }
    
    private String setCustomerDetails() {

        if (isNull(cusid) || conversation == null) {
            return "debug";
        }

        CustomerDTO e = customerManagement.getCustomerDetails(cusid);

        if (e == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.cusid = e.getCusid();
            this.name = e.getName();
            this.email = e.getEmail();
            this.phone = e.getPhone();
            this.address = e.getAddress();
            return "success";
        }
    }
    
    
}
