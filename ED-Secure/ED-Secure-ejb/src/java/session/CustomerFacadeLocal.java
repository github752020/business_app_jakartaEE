/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface CustomerFacadeLocal {
    public Customer find(String id);
}
