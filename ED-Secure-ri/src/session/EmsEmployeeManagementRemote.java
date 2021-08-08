/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Stateless Session Bean - EmsEmployeeManagement
 *
 * This is the remote interface for the stateless bean
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package session;

import entity.EmsEmployeeDTO;
import javax.ejb.Remote;

/**
 *
 * @author elau
 */
@Remote
public interface EmsEmployeeManagementRemote {

    public boolean hasEmployee(String empid);

    public boolean addEmployee(EmsEmployeeDTO emsEmployeeDTO);

    public boolean updateEmpolyeeDetails(EmsEmployeeDTO emsEmployeeDTO);
    public boolean updateMyEmpolyeeDetails(EmsEmployeeDTO emsEmployeeDTO);

    public boolean updateEmployeePassword(String empid, String newPassword, String memo);
    public boolean updateMyPassword(String newPassword, String memo);
    public boolean isEmployeeActive();

    public EmsEmployeeDTO getEmployeeDetails(String empid);

    public EmsEmployeeDTO getEmployeeDetails2();

    public boolean deleteEmployee(String empid);

    public boolean removeEmployee(String empid);
}
