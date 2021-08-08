/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Stateless Session Bean - EmsEmployeeManagement
 *
 * This is the stateless bean
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package session;

import entity.EmsEmployee;
import entity.EmsEmployeeDTO;
import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author elau
 */
@DeclareRoles({"ED-EMS-ADMIN", "ED-EMS-USERS"})
@Stateless
public class EmsEmployeeManagement implements EmsEmployeeManagementRemote {

    @Resource
    SessionContext context;

    @EJB
    private EmsEmployeeFacadeLocal emsEployeeFacade;

    private EmsEmployee convertEmsEmployeeDTO2Entity(EmsEmployeeDTO emsEmployeeDTO) {
        if (emsEmployeeDTO == null) {
            // just in case
            return null;
        }

        String empid = emsEmployeeDTO.getEmpid();
        String name = emsEmployeeDTO.getName();
        String password = emsEmployeeDTO.getPassword();
        String email = emsEmployeeDTO.getEmail();
        String phone = emsEmployeeDTO.getPhone();
        String address = emsEmployeeDTO.getAddress();
        String secqn = emsEmployeeDTO.getSecqn();
        String secans = emsEmployeeDTO.getSecans();
        String bsbid = emsEmployeeDTO.getBsbid();
        String accid = emsEmployeeDTO.getAccid();
        BigDecimal salary = emsEmployeeDTO.getSalary();
        String appgroup = emsEmployeeDTO.getAppgroup();
        Boolean active = emsEmployeeDTO.getActive();
        String memo = emsEmployeeDTO.getMemo();

        EmsEmployee emsEmployee = new EmsEmployee();
        emsEmployee.setEmpid(empid);
        emsEmployee.setName(name);
        emsEmployee.setPassword(password);
        emsEmployee.setEmail(email);
        emsEmployee.setPhone(phone);
        emsEmployee.setAddress(address);
        emsEmployee.setSecqn(secqn);
        emsEmployee.setSecans(secans);
        emsEmployee.setBsbid(bsbid);
        emsEmployee.setAccid(accid);
        emsEmployee.setSalary(salary);
        emsEmployee.setAppgroup(appgroup);
        emsEmployee.setActive(active);
        emsEmployee.setMemo(memo);

        return emsEmployee;
    }

    private EmsEmployeeDTO convertEmsEmployeeEntity2DTO(EmsEmployee emsEmployee) {
        if (emsEmployee == null) {
            // just in case
            return null;
        }

        EmsEmployeeDTO emsEmployeeDTO = new EmsEmployeeDTO(
                emsEmployee.getEmpid(), emsEmployee.getName(),
                emsEmployee.getPassword(), emsEmployee.getEmail(),
                emsEmployee.getPhone(), emsEmployee.getAddress(),
                emsEmployee.getSecqn(), emsEmployee.getSecans(),
                emsEmployee.getBsbid(), emsEmployee.getAccid(),
                emsEmployee.getSalary(), emsEmployee.getAppgroup(),
                emsEmployee.getActive(), emsEmployee.getMemo()
        );

        return emsEmployeeDTO;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * check whether the employee is in the system
     *
     * @param empid
     * @return true if the employee is in the system, false otherwise
     */
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public boolean hasEmployee(String empid) {
        return emsEployeeFacade.hasEmployee(empid);
    }

    /**
     * add an employee to the system
     *
     * @param emsEmployeeDTO
     * @return true if addition is successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public boolean addEmployee(EmsEmployeeDTO emsEmployeeDTO) {

        if (emsEmployeeDTO == null) {
            // just in case
            return false;
        }

        // check employee exist?
        if (hasEmployee(emsEmployeeDTO.getEmpid())) {
            // employee exists, cannot add one
            return false;
        }

        // employee not exist
        // convert to entity
        EmsEmployee emsEmployee = this.convertEmsEmployeeDTO2Entity(emsEmployeeDTO);
        // add one
        return emsEployeeFacade.addEmployee(emsEmployee);
    }

    /**
     * update employee details without updating password
     *
     * @param emsEmployeeDTO
     * @return true if update is successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public boolean updateEmpolyeeDetails(EmsEmployeeDTO emsEmployeeDTO) {
        // check employee exist?
        if (!hasEmployee(emsEmployeeDTO.getEmpid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        EmsEmployee emsEmployee = this.convertEmsEmployeeDTO2Entity(emsEmployeeDTO);
        
        // update details
        return emsEployeeFacade.updateEmployeeDetails(emsEmployee);
    }
    
    @Override
    @RolesAllowed({"ED-EMS-USERS"})
    public boolean updateMyEmpolyeeDetails(EmsEmployeeDTO emsEmployeeDTO){
        if (!hasEmployee(emsEmployeeDTO.getEmpid())) {
            return false;
        }

        EmsEmployee emsEmployee = this.convertEmsEmployeeDTO2Entity(emsEmployeeDTO);

        return emsEployeeFacade.updateMyEmployeeDetails(emsEmployee);
        
    }

    /**
     * update employee's password
     *
     * @param empid
     * @param newPassword (encrypted with SHA256)
     * @param memo (plain password store in memo field)
     * @return true if update successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public boolean updateEmployeePassword(String empid, String newPassword, String memo) {
        return emsEployeeFacade.updatePassword(empid, newPassword, memo);
    }

    /**
     * get employee details and use a DTO to transmit the details
     *
     * @param empid
     * @return a DTO containing the information of the employee if exists, null
     * otherwise
     */
    
    @Override
    @RolesAllowed({"ED-EMS-USERS"})
    public boolean updateMyPassword(String newPassword, String memo){
        String empid = context.getCallerPrincipal().getName();
        return emsEployeeFacade.updatePassword(empid, newPassword, memo);
    }
    
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public EmsEmployeeDTO getEmployeeDetails(String empid) {
        // get the employee
        EmsEmployee emsEmployee = emsEployeeFacade.find(empid);

        if (emsEmployee == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            EmsEmployeeDTO emsEmployeeDTO = this.convertEmsEmployeeEntity2DTO(emsEmployee);

            return emsEmployeeDTO;
        }
    }
    
    
    @Override
    @RolesAllowed({"ED-EMS-USERS"})
    public EmsEmployeeDTO getEmployeeDetails2() {

        String empid = context.getCallerPrincipal().getName();

        // get the employee
        EmsEmployee emsEmployee = emsEployeeFacade.find(empid);

        if (emsEmployee == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            EmsEmployeeDTO emsEmployeeDTO = this.convertEmsEmployeeEntity2DTO(emsEmployee);

            return emsEmployeeDTO;
        }
    }

    @Override
    @RolesAllowed({"ED-EMS-ADMIN","ED-EMS-USERS"})
    public boolean isEmployeeActive() {

        String empid = context.getCallerPrincipal().getName();

        // get the employee
        EmsEmployee emsEmployee = emsEployeeFacade.find(empid);

        if (emsEmployee == null) {
            // not found - no such employee
            return false;
        } else {
            // found - prepare details
            boolean isActive = emsEmployee.getActive();
            return isActive;
        }
    }
    
    /**
     * set the employee's active status to false
     *
     * @param empid
     * @return true if it can be set to inactive and have set to inactive; false
     * otherwise
     */
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public boolean deleteEmployee(String empid) {
        return emsEployeeFacade.deleteEmployee(empid);
    }

    /**
     * physically remove an employee's record from database
     *
     * This is for lab purposes - never ever do this in real world applications
     *
     * @param empid
     * @return true if the employee record has been physically removed from the
     * database, false otherwise
     */
    @Override
    @RolesAllowed({"ED-EMS-ADMIN"})
    public boolean removeEmployee(String empid) {
        return emsEployeeFacade.removeEmployee(empid);
    }

}
