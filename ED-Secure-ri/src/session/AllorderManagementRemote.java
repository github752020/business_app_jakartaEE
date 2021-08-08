/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.AllorderDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author user
 */
@Remote
public interface AllorderManagementRemote {

    public boolean hasOrder(String orid);

    public boolean addOrder(AllorderDTO allorderDTO);

    public boolean updateOrderDetails(AllorderDTO allorderDTO);

    public AllorderDTO getOrderDetails(String orid);

    public ArrayList<AllorderDTO> getPendingOrders();

    public ArrayList<AllorderDTO> getMyOrderDetails();

    public boolean fulfillOrder(String orid);

    public boolean payOrder(String orid);

    public boolean removeOrder(String orid);
}
