/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Stateless Session Bean - EmsEmployeeFacade
 *
 * This is the local interface for the stateless bean
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package session;

import entity.EmsEmployee;
import javax.ejb.Local;

/**
 *
 * @author elau
 */
@Local
public interface EmsEmployeeFacadeLocal {
    public EmsEmployee find(String id);

    public boolean hasEmployee(String empId);
    
    public boolean addEmployee(EmsEmployee employee);
    
    public boolean updateEmployeeDetails(EmsEmployee employee);
    public boolean updateMyEmployeeDetails(EmsEmployee employee);
    
    public boolean updatePassword(String empId, String password, String memo);

    public boolean deleteEmployee(String empId);
    
    public boolean removeEmployee(String empId);
}
