/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - CDI Bean - EmsEmployeeCDIBean
 *
 * This is the CDI bean for holding information for communications to the stateless session beans
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package web;

import entity.EmsEmployeeDTO;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import session.EmsEmployeeManagementRemote;
import util.MyHash;

/**
 *
 * @author elau
 */
@Named(value = "emsEmployeeCDIBean")
@ConversationScoped
public class EmsEmployeeCDIBean implements Serializable {
    
    @Inject
    private Conversation conversation;
    
    @EJB
    private EmsEmployeeManagementRemote emsEmployeeManagement;

    private String empid;
    private String name;
    private String password;
    private String cpassword;
    private String newpassword;
    private String phone;
    private String address;
    private String email;
    private String secqn;
    private String secans;
    private String bsbid;
    private String accid;
    private BigDecimal salary;
    private String appgroup;
    private Boolean active;
    private String memo;

    /**
     * Creates a new instance of EmsEmployeeCDIBean
     */
    public EmsEmployeeCDIBean() {
    }
    
    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecqn() {
        return secqn;
    }

    public void setSecqn(String secqn) {
        this.secqn = secqn;
    }

    public String getSecans() {
        return secans;
    }

    public void setSecans(String secans) {
        this.secans = secans;
    }

    public String getBsbid() {
        return bsbid;
    }

    public void setBsbid(String bsbid) {
        this.bsbid = bsbid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAppgroup() {
        return appgroup;
    }

    public void setAppgroup(String appgroup) {
        this.appgroup = appgroup;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean validSalary() {
        if (salary == null) {
            return false;
        }

        double d_salary = salary.doubleValue();
        boolean b = (d_salary > 0.0);
        return b;
    }

    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
    
    public boolean isEmployeeActive(){
        boolean result = emsEmployeeManagement.isEmployeeActive();
        return result;
    }

    public String addEmployee() {

        // check empid is null
        if (isNull(empid)) {
            return "debug";
        }

        // all information seems to be valid
        // try add the employee
        String hashedPassword = MyHash.getSHA256HashedString(password);
        EmsEmployeeDTO emsEmployeeDTO = new EmsEmployeeDTO(empid, name, hashedPassword,
                email, phone, address, secqn, secans, bsbid, accid, salary, 
                appgroup, active, password);
        boolean result = emsEmployeeManagement.addEmployee(emsEmployeeDTO);
        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String setEmployeeDetailsForChange() {
        // check empid is null
        if (isNull(empid) || conversation == null) {
            System.out.println(empid);
            return "debug";
        }

        if (!emsEmployeeManagement.hasEmployee(empid)) {
            return "failure";
        }

        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setEmployeeDetails();
    }
    
    public String setMyDetailsForChange() {
        
        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setMyDetails();
    }

    public String changeEmployee() {
        // check empid is null
        if (isNull(empid)) {
            return "debug";
        }

        String hashedPassword = MyHash.getSHA256HashedString(password);
        EmsEmployeeDTO emsEmployeeDTO = new EmsEmployeeDTO(empid, name, hashedPassword,
                email, phone, address, secqn, secans, bsbid, accid, salary, 
                appgroup, active, memo);
        boolean result = emsEmployeeManagement.updateEmpolyeeDetails(emsEmployeeDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }
    
    public String changeMyEmployee() {
        
        String hashedPassword = MyHash.getSHA256HashedString(password);
        EmsEmployeeDTO emsEmployeeDTO = new EmsEmployeeDTO(empid, name, hashedPassword,
                email, phone, address, secqn, secans, bsbid, accid, salary, 
                appgroup, active, memo);
        boolean result = emsEmployeeManagement.updateMyEmpolyeeDetails(emsEmployeeDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void validateNewPassword(FacesContext context,
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
        // check empid is null
        System.out.println(empid);
        if (isNull(empid)) {
            return "debug";
        }

        // newpassword and cpassword are the same - checked by the validator during input to JSF form
        // newpassword is the plain password entered
        memo = newpassword;
        String hashedPassword = MyHash.getSHA256HashedString(memo);
        boolean result = emsEmployeeManagement.updateEmployeePassword(empid, hashedPassword, memo);

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
        boolean result = emsEmployeeManagement.updateMyPassword(hashedPassword, memo);

        System.out.println("result = " + result);

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String deleteEmployee() {
        // check empid is null
        if (isNull(empid)) {
            return "debug";
        }

        // only set employee status to inactive, do not physically remove the record
        boolean result = emsEmployeeManagement.deleteEmployee(empid);

        if (result) {
            return "success";
        } else {
            return "failure";
        }

    }

    public String displayEmployee() {
        // check empid is null
        System.out.println(empid);
        if (isNull(empid) || conversation == null) {
            return "debug";
        }

        return setEmployeeDetails();
    }

    private boolean isNull(String s) {
        return (s == null);
    }

    public String displayMyDetails() {
        return setMyDetails();
    }
    
    private String setMyDetails() {

        EmsEmployeeDTO e = emsEmployeeManagement.getEmployeeDetails2();

        if (e == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.empid = e.getEmpid();
            this.name = e.getName();
            // this should be the plain password
            this.password = e.getMemo();
            this.email = e.getEmail();
            this.phone = e.getPhone();
            this.address = e.getAddress();
            this.secqn = e.getSecqn();
            this.secans = e.getSecans();
            this.bsbid = e.getBsbid();
            this.accid = e.getAccid();
            this.salary = e.getSalary();
            this.appgroup = e.getAppgroup();
            this.active = e.getActive();
            this.memo = e.getMemo();
            return "success";
        }
    }
    
    private String setEmployeeDetails() {

        if (isNull(empid) || conversation == null) {
            return "debug";
        }

        EmsEmployeeDTO e = emsEmployeeManagement.getEmployeeDetails(empid);

        if (e == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.empid = e.getEmpid();
            this.name = e.getName();
            // this should be the plain password
            this.password = e.getMemo();
            this.email = e.getEmail();
            this.phone = e.getPhone();
            this.address = e.getAddress();
            this.secqn = e.getSecqn();
            this.secans = e.getSecans();
            this.bsbid = e.getBsbid();
            this.accid = e.getAccid();
            this.salary = e.getSalary();
            this.appgroup = e.getAppgroup();
            this.active = e.getActive();
            this.memo = e.getMemo();
            return "success";
        }
    }

    private boolean validAddEmployeeInfo() {

        if (salary == null) {
            return false;
        }
        
        double d_salary = salary.doubleValue();
        
        if (d_salary <= 0) {
            // salary must be positive
            return false;
        }
        
        return (empid != null && name != null && password != null 
                && email != null && phone != null && address != null 
                && secqn != null && secans != null
                && bsbid != null && accid != null 
                && appgroup != null && active != null
                && memo != null);
    }

    
}
