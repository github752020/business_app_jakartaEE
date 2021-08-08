/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Stateless Session Bean - EmsEmployeeFacade
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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author elau
 */
@Stateless
public class EmsEmployeeFacade implements EmsEmployeeFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public EmsEmployeeFacade() {
    }

    private void create(EmsEmployee entity) {
        em.persist(entity);
    }

    private void edit(EmsEmployee entity) {
        em.merge(entity);
    }

    private void remove(EmsEmployee entity) {
        em.remove(em.merge(entity));
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public EmsEmployee find(String id) {
        return em.find(EmsEmployee.class, id);
    }

    /**
     * checks whether an employee exist using empId
     *
     * @param empId
     * @return true if exist, false otherwise
     */
    @Override
    public boolean hasEmployee(String empId) {
        return (find(empId) != null);
    }

    /**
     * add an employee to the system
     *
     * @param employee
     * @return true if addition is successful, false otherwise
     */
    @Override
    public boolean addEmployee(EmsEmployee employee) {
        // check again - just to play it safe
        EmsEmployee e = find(employee.getEmpid());

        if (e != null) {
            // could not add one
            return false;
        }

        create(employee);

        return true;
    }

    /**
     * update employee details without changing password
     *
     * @param employee
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateEmployeeDetails(EmsEmployee employee) {
        // find the employee, just in case
        EmsEmployee e = find(employee.getEmpid());

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // no need to update the primary key - empId
        edit(employee);
        return true;
    }

    @Override
    public boolean updateMyEmployeeDetails(EmsEmployee employee) {
        // find the employee, just in case
        EmsEmployee e = find(employee.getEmpid());

        // check again - just to play it safe
        if (e == null) {
            return false;
        }
        if (!e.getPassword().equals(employee.getPassword())) {
            employee.setPassword(e.getPassword());
        } 
        if (!e.getSalary().equals(employee.getSalary())) {
            employee.setSalary(e.getSalary());
        } 
        if (!e.getAppgroup().equals(employee.getAppgroup())) {
            employee.setAppgroup(e.getAppgroup());
        } 
        if (!e.getActive().equals(employee.getActive())) {
            employee.setActive(e.getActive());
        } 
        if (!e.getMemo().equals(employee.getMemo())) {
            employee.setMemo(e.getMemo());
        } 

        edit(employee);
        return true;
    }

    /**
     * update employee's password
     *
     * @param empId
     * @param password (encrypted with SHA256)
     * @param memo (plain password)
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updatePassword(String empId, String password, String memo) {
        // find the employee
        EmsEmployee e = find(empId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // only need to update the password field
        e.setPassword(password);
        e.setMemo(memo);
        return true;
    }

    /**
     * delete the employee by setting active to false - cannot physically remove
     * the record due to taxation purposes
     *
     * @param empId
     * @return true if successful, false otherwise
     */
    @Override
    public boolean deleteEmployee(String empId) {
        // find the employee
        EmsEmployee e = find(empId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        if (e.getActive() == null) {
            return false;
        }

        if (!e.getActive()) {
            // employee not active already
            return false;
        }

        e.setActive(false);
        return true;
    }

    /**
     * physically remove the employee record from database table
     *
     * this is only for lab purposes - never ever do this in real world
     * applications
     *
     * @param empId
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeEmployee(String empId) {
        // find the employee
        EmsEmployee e = find(empId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        em.remove(e);
        return true;
    }
}
