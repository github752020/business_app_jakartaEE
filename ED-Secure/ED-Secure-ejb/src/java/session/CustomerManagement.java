/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.CustomerDTO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author user
 */
@DeclareRoles({"EMS_EMPLOYEE", "CUSTOMER"})
@Stateless
public class CustomerManagement implements CustomerManagementRemote {

    @EJB
    private CustomerFacadeLocal customerFacade;

    private Customer convertCustomerDTO2Entity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            // just in case
            return null;
        }

        String cusid = customerDTO.getCusid();
        String name = customerDTO.getName();
        String email = customerDTO.getEmail();
        String phone = customerDTO.getPhone();
        String address = customerDTO.getAddress();
        

        Customer customer = new Customer();
        customer.setCusid(cusid);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        
        return customer;
    }

    private CustomerDTO convertCustomerEntity2DTO(Customer customer) {
        if (customer == null) {
            // just in case
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO(
                customer.getCusid(), customer.getName(),
                customer.getEmail(),
                customer.getPhone(), customer.getAddress()
        );

        return customerDTO;
    }
    
    @Override
    @RolesAllowed({"EMS_EMPLOYEE"})
    public CustomerDTO getCustomerDetails(String cusid) {
        // get the employee
        Customer customer = customerFacade.find(cusid);

        if (customer == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            CustomerDTO customerDTO = this.convertCustomerEntity2DTO(customer);

            return customerDTO;
        }
    }
}
