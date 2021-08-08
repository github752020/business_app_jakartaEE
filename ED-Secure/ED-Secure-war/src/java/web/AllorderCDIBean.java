/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.AllorderDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import session.AllorderManagementRemote;

/**
 *
 * @author user
 */
@Named(value = "allorderCDIBean")
@ConversationScoped
public class AllorderCDIBean implements Serializable {

    @EJB
    private AllorderManagementRemote allorderManagement;

    @Inject
    private Conversation conversation;

    private String orid;
    private String cusid;
    private String ordate;
    private String dedate;
    private double quantity;
    private double total;
    private double uprice = 2;
    private boolean paid;
    private boolean delivered;
    
    private String ccn;
    private String cvv;
    private String exp;
    
    private AllorderDTO tmpOrder;

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public String getCcn() {
        return ccn;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExp() {
        return exp;
    }

    private ArrayList<AllorderDTO> pendingList = new ArrayList<>();
    private ArrayList<AllorderDTO> myOrderList = new ArrayList<>();

    public AllorderCDIBean() {
    }

    public void setAllorderManagement(AllorderManagementRemote allorderManagement) {
        this.allorderManagement = allorderManagement;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public void setOrdate(String ordate) {
        this.ordate = ordate;
    }

    public void setDedate(String dedate) {
        this.dedate = dedate;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public AllorderManagementRemote getAllorderManagement() {
        return allorderManagement;
    }

    public String getOrid() {
        return orid;
    }

    public String getCusid() {
        return cusid;
    }

    public String getOrdate() {
        return ordate;
    }

    public String getDedate() {
        return dedate;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public boolean getPaid() {
        return paid;
    }

    public boolean getDelivered() {
        return delivered;
    }

    /*public boolean validSalary() {
        if (salary == null) {
            return false;
        }

        double d_salary = salary.doubleValue();
        boolean b = (d_salary > 0.0);
        return b;
    }*/
    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    /*public boolean isEmployeeActive(){
        boolean result = allorderManagement.isEmployeeActive();
        return result;
    }*/
    public String payOrder() {
        boolean result = false;
        // check orid is null
        if (isNull(orid)) {
            return "debug";
        }
        if (!isNull(ccn) && !isNull(cvv) && !isNull(exp)) {
            result = allorderManagement.payOrder(orid);
        }
        endConversation();
        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }
    
    public String addOrder() {
        cusid = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String formattedDate = dateObj.format(myFormatObj);
        ordate = LocalDate.now().toString();
        dedate = LocalDate.now().plusDays(1).toString();
        orid = cusid + formattedDate;
        total = uprice*quantity;
        paid = false;
        delivered = false;
        System.out.println(cusid);
        System.out.println(orid);
        System.out.println(ordate);
        System.out.println(dedate);
        System.out.println(quantity);
        System.out.println(total);
        System.out.println(paid);
        System.out.println(delivered);
        
        // all information seems to be valid
        // try add the employee
        AllorderDTO allorderDTO = new AllorderDTO(orid, cusid, ordate,
                dedate, quantity, total, paid, delivered);
        boolean result = allorderManagement.addOrder(allorderDTO);
        startConversation();
        if (result) {
            System.out.println("success");
            return "success";
            
        } else {
            System.out.println("failure");
            return "failure";
            
        }
    }

    public String setOrderDetailsForChange() {
        // check orid is null
        if (tmpOrder == null || conversation == null) {
            //System.out.println(orid);
            return "debug";
        }

        if (!allorderManagement.hasOrder(tmpOrder.getOrid())) {
            return "failure";
        }

        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setOrderDetails();
    }

    /*public String setMyDetailsForChange() {
        
        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setMyDetails();
    }*/
    public String changeOrder() {
        // check orid is null
        if (isNull(orid)) {
            return "debug";
        }

        //String hashedPassword = MyHash.getSHA256HashedString(password);
        AllorderDTO allorderDTO = new AllorderDTO(orid, cusid, ordate,
                dedate, quantity, total, paid, delivered);
        boolean result = allorderManagement.updateOrderDetails(allorderDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    /*public String changeMyEmployee() {
        
        String hashedPassword = MyHash.getSHA256HashedString(password);
        AllorderDTO allorderDTO = new AllorderDTO(orid, name, hashedPassword,
                email, phone, address, secqn, secans, bsbid, accid, salary, 
                appgroup, active, memo);
        boolean result = allorderManagement.updateMyEmpolyeeDetails(allorderDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }*/

 /*public void validateNewPassword(FacesContext context,
            UIComponent componentToValidate, Object value)
            throws ValidatorException {

        // get new password
        String oldPwd = (String) value;

        // get old password
        UIInput newPwdComponent = (UIInput) componentToValidate.getAttributes().get("newpwd");
        String newPwd = (String) newPwdComponent.getSubmittedValue();

        if (oldPwd.equals(newPwd)) {
            FacesMessage message = new FacesMessage(
                    "Old Password and New Password are the same! They must be different.");
            throw new ValidatorException(message);
        }

    }

    public void validatePasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object pwdValue) throws ValidatorException {

        // get password
        String pwd = (String) pwdValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("password : " + pwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!pwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "Password and Confirm Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public void validateNewPasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object newValue) throws ValidatorException {

        // get new password
        String newPwd = (String) newValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("new password : " + newPwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!newPwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "New Password and Confirm New Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public String changeEmployeePassword() {
        // check orid is null
        System.out.println(orid);
        if (isNull(orid)) {
            return "debug";
        }

        // newpassword and cpassword are the same - checked by the validator during input to JSF form
        // newpassword is the plain password entered
        memo = newpassword;
        String hashedPassword = MyHash.getSHA256HashedString(memo);
        boolean result = allorderManagement.updateEmployeePassword(orid, hashedPassword, memo);

        System.out.println("result = " + result);

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }
    
    public String changeMyPassword() {

        // newpassword and cpassword are the same - checked by the validator during input to JSF form
        // newpassword is the plain password entered
        memo = newpassword;
        String hashedPassword = MyHash.getSHA256HashedString(memo);
        boolean result = allorderManagement.updateMyPassword(hashedPassword, memo);

        System.out.println("result = " + result);

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }*/
    public String removeOrder() {
        // check orid is null
        if (isNull(orid)) {
            return "debug";
        }

        // only set employee status to inactive, do not physically remove the record
        boolean result = allorderManagement.removeOrder(orid);

        if (result) {
            return "success";
        } else {
            return "failure";
        }

    }

    public String displayOrder() {
        // check orid is null
        System.out.println(orid);
        if (isNull(orid) || conversation == null) {
            return "debug";
        }

        return setOrderDetails();
    }

    private boolean isNull(String s) {
        return (s == null);
    }

    public ArrayList<AllorderDTO> getMyOrderList() {
        myOrderList = allorderManagement.getMyOrderDetails();

        return myOrderList;
    }

    public ArrayList<AllorderDTO> getPendingList() {

        pendingList = allorderManagement.getPendingOrders();

        return pendingList;
    }

    private String setOrderDetails() {

        if (tmpOrder == null || conversation == null) {
            return "debug";
        }

        //AllorderDTO e = allorderManagement.getOrderDetails(orid);

        if (tmpOrder == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.orid = tmpOrder.getOrid();
            this.cusid = tmpOrder.getCusid();
            this.ordate = tmpOrder.getOrdate();
            this.dedate = tmpOrder.getDedate();
            this.quantity = tmpOrder.getQuantity();
            this.total = tmpOrder.getTotal();
            this.paid = tmpOrder.getPaid();
            this.delivered = tmpOrder.getDelivered();
            return "success";
        }
    }

    public void setTmpOrder(AllorderDTO tmpOrder) {
        this.tmpOrder = tmpOrder;
    }

    /*private boolean validAddEmployeeInfo() {

        if (quantity == 0) {
            return false;
        }
        
        double d_salary = quantity.doubleValue();
        
        if (d_salary <= 0) {
            // salary must be positive
            return false;
        }
        
        return (orid != null && cusid != null && ordate!= null 
                && dedate != null && paid != null && delivered != null 
                );
    }*/

    public AllorderDTO getTmpOrder() {
        return tmpOrder;
    }
}
