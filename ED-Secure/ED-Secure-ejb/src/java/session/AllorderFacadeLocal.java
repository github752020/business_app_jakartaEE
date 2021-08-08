/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Allorder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface AllorderFacadeLocal {

    public Allorder find(String id);

    public boolean hasOrder(String orid);

    public boolean addOrder(Allorder order);

    public boolean updateOrderDetails(Allorder order);

    public List <Allorder> getPendingOrders();

    public List <Allorder> getMyOrderDetails(String cusid);

    public boolean payOrder(String orid);

    public boolean fulfillOrder(String orid);

    public boolean removeOrder(String orid);
}
