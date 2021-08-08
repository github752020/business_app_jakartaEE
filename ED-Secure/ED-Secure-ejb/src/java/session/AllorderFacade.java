/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Allorder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author user
 */
@Stateless
public class AllorderFacade implements AllorderFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public AllorderFacade() {
    }

    private void create(Allorder entity) {
        Logger logger = Logger.getAnonymousLogger();
        try {em.persist(entity);}
        catch (ConstraintViolationException e) {
       logger.log(Level.SEVERE,"Exception: ");
       e.getConstraintViolations().forEach(err->logger.log(Level.SEVERE,err.toString()));
    }
    }

    private void edit(Allorder entity) {
        em.merge(entity);
    }

    private void remove(Allorder entity) {
        em.remove(em.merge(entity));
    }

   
    @Override
    public Allorder find(String id) {
        return em.find(Allorder.class, id);
    }

    
    @Override
    public boolean hasOrder(String orid) {
        return (find(orid) != null);
    }

    
    @Override
    public boolean addOrder(Allorder order) {
        // check again - just to play it safe
        Allorder e = find(order.getOrid());

        if (e != null) {
            // could not add one
            return false;
        }

        create(order);

        return true;
    }

    
    @Override
    public boolean updateOrderDetails(Allorder order) {
        // find the order, just in case
        Allorder e = find(order.getOrid());

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // no need to update the primary key - orid
        edit(order);
        return true;
    }

   
   
    @Override
    public boolean fulfillOrder(String orid) {
        // find the order
        Allorder e = find(orid);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        if (e.getDelivered() == null) {
            return false;
        }

        if (e.getDelivered()) {
            // order delivered already
            return false;
        }

        e.setDelivered(true);
        return true;
    }
    
    @Override
    public boolean payOrder(String orid) {
        // find the order
        Allorder e = find(orid);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        if (e.getPaid() == null) {
            return false;
        }

        if (e.getPaid()) {
            // order delivered already
            return false;
        }

        e.setPaid(true);
        return true;
    }

   
    @Override
    public boolean removeOrder(String orid) {
        // find the order
        Allorder e = find(orid);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        em.remove(e);
        return true;
    }
    
    @Override
    public List<Allorder> getPendingOrders(){
        List <Allorder> pendingList = em.createNamedQuery("Allorder.findByDelivered")
                .setParameter("delivered", false).getResultList();
        return pendingList;
    };

    @Override
    public List<Allorder> getMyOrderDetails(String cusid){
        List <Allorder> myOrderList = em.createNamedQuery("Allorder.findByCusid")
                .setParameter("cusid", cusid).getResultList();
        return myOrderList;
    };
}
