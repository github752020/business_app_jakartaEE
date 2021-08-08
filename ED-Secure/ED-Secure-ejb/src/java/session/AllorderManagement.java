/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Allorder;
import entity.AllorderDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@DeclareRoles({"EMS_EMPLOYEE", "CUSTOMER"})
@Stateless
public class AllorderManagement implements AllorderManagementRemote {

    @Resource
    SessionContext context;

    @EJB
    private AllorderFacadeLocal allorderFacade;

    private Allorder convertAllorderDTO2Entity(AllorderDTO allorderDTO) {
        if (allorderDTO == null) {
            // just in case
            return null;
        }

        String orid = allorderDTO.getOrid();
        String cusid = allorderDTO.getCusid();
        String ordate = allorderDTO.getOrdate();
        String dedate = allorderDTO.getDedate();
        double quantity = allorderDTO.getQuantity();
        double total = allorderDTO.getTotal();
        boolean paid = allorderDTO.getPaid();
        boolean delivered = allorderDTO.getDelivered();
        

        Allorder allorder = new Allorder();
        allorder.setOrid(orid);
        allorder.setCusid(cusid);
        allorder.setOrdate(ordate);
        allorder.setDedate(dedate);
        allorder.setQuantity(quantity);
        allorder.setTotal(total);
        allorder.setPaid(paid);
        allorder.setDelivered(delivered);

        return allorder;
    }

    private AllorderDTO convertAllorderEntity2DTO(Allorder allorder) {
        if (allorder == null) {
            // just in case
            return null;
        }

        AllorderDTO allorderDTO = new AllorderDTO(
                allorder.getOrid(), allorder.getCusid(),
                allorder.getOrdate(), allorder.getDedate(),
                allorder.getQuantity(), allorder.getTotal(),
                allorder.getPaid(), allorder.getDelivered()
        );

        return allorderDTO;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
    @Override
    @RolesAllowed({"EMS_EMPLOYEE", "CUSTOMER"})
    public boolean hasOrder(String orid) {
        return allorderFacade.hasOrder(orid);
    }

    
    @Override
    @RolesAllowed({"CUSTOMER"})
    public boolean addOrder(AllorderDTO allorderDTO) {

        if (allorderDTO == null) {
            // just in case
            return false;
        }

        // check employee exist?
        if (hasOrder(allorderDTO.getOrid())) {
            // employee exists, cannot add one
            return false;
        }

        // employee not exist
        // convert to entity
        Allorder allorder = this.convertAllorderDTO2Entity(allorderDTO);
        // add one
        return allorderFacade.addOrder(allorder);
    }

    
    @Override
    @RolesAllowed({"EMS_EMPLOYEE"})
    public boolean updateOrderDetails(AllorderDTO allorderDTO) {
        // check employee exist?
        if (!hasOrder(allorderDTO.getOrid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        Allorder allorder = this.convertAllorderDTO2Entity(allorderDTO);
        
        // update details
        return allorderFacade.updateOrderDetails(allorder);
    }
    
    
    @Override
    @RolesAllowed({"EMS_EMPLOYEE"})
    public AllorderDTO getOrderDetails(String orid) {
        // get the employee
        Allorder allorder = allorderFacade.find(orid);

        if (allorder == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            AllorderDTO allorderDTO = this.convertAllorderEntity2DTO(allorder);

            return allorderDTO;
        }
    }
    
    @Override
    @RolesAllowed({"EMS_EMPLOYEE"})
    public ArrayList<AllorderDTO> getPendingOrders() {
        List <Allorder> pendingList = new ArrayList <>();
        ArrayList <AllorderDTO> pendingDTOList = new ArrayList <>();
        pendingList = allorderFacade.getPendingOrders();
        for (Allorder order: pendingList){
                AllorderDTO orderDTO = this.convertAllorderEntity2DTO(order);
                pendingDTOList.add(orderDTO);
            }
        return pendingDTOList;
    }
    
    
    @Override
    @RolesAllowed({"CUSTOMER"})
    public ArrayList<AllorderDTO> getMyOrderDetails() {

        String cusid = context.getCallerPrincipal().getName();
        List <Allorder> myOrderList = new ArrayList <>();
        ArrayList <AllorderDTO> myOrderDTOList = new ArrayList <>();

        // get the employee
        myOrderList = allorderFacade.getMyOrderDetails(cusid);

        if (myOrderList == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            for (Allorder order: myOrderList){
                AllorderDTO orderDTO = this.convertAllorderEntity2DTO(order);
                myOrderDTOList.add(orderDTO);
            }
            

            return myOrderDTOList;
        }
    }

    @Override
    @RolesAllowed({"EMS_EMPLOYEE"})
    public boolean fulfillOrder(String orid) {

        //String orid = context.getCallerPrincipal().getName();

        // get the employee
        Allorder allorder = allorderFacade.find(orid);

        if (allorder == null) {
            // not found - no such employee
            return false;
        } else {
            // found - prepare details
            boolean isfulfilled = allorderFacade.fulfillOrder(orid);
            return isfulfilled;
        }
    }
    
    
    @Override
    @RolesAllowed({"CUSTOMER"})
    public boolean payOrder(String orid) {
        return allorderFacade.payOrder(orid);
    }

    
    @Override
    @RolesAllowed({"EMS_EMPLOYEE"})
    public boolean removeOrder(String orid) {
        return allorderFacade.removeOrder(orid);
    }
}
