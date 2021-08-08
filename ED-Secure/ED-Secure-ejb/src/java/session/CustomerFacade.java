/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class CustomerFacade implements CustomerFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Customer customer) {
        em.persist(customer);
    }

    private void edit(Customer customer) {
        em.merge(customer);
    }

    private void remove(Customer customer) {
        em.remove(em.merge(customer));
    }

    @Override
    public Customer find(String cusid) {
        return em.find(Customer.class, cusid);
    }
}
