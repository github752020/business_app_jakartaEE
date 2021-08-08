/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Secure - SecureAppClient
 *
 * This is the application client that calls the stateless session beans
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package ed;

import entity.EmsEmployeeDTO;
import java.math.BigDecimal;
import java.util.Scanner;
import session.EmsEmployeeManagementRemote;
import util.MyHash;

/**
 *
 * @author elau
 */
public class SecureAppClient {

    @javax.ejb.EJB
    private static EmsEmployeeManagementRemote emsEmployeeManagement;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SecureAppClient client = new SecureAppClient();

        String memo = "88888888";
        String pwd = MyHash.getSHA256HashedString(memo);
        EmsEmployeeDTO emsEmployeeDTO = new EmsEmployeeDTO("000099", "Luke Skywalker", pwd,
                "lskywalker@secure.com.au", "9214436789", "Secure EN555a",
                "What is your name?", "Skywalker",
                "000111", "12345678", new BigDecimal(12345.0), "ED-EMS-USERS",
                true, memo);

        String empid = emsEmployeeDTO.getEmpid();
        System.out.println("Want to add an employee whose empid is " + empid);

        boolean wantSimpleAddNoCheck = true;
        if (wantSimpleAddNoCheck) {
            client.addEMSEmployee(emsEmployeeDTO);
        } else {
            // want to check first
            System.out.println("But, let us check whether it is in the database first.");
            boolean hasEmployee = client.findEMSEmployee(empid);
            if (hasEmployee) {
                System.out.println("Found the employee wanted to add, update it with this set of new information");
                client.updateEMSEmployee(emsEmployeeDTO);
            } else {
                System.out.println("Cannot find the employee wanted to add, add a new one");
                client.addEMSEmployee(emsEmployeeDTO);
            }
        }

        System.out.println("Want to remove the employee record whose employee id is \'" + emsEmployeeDTO.getEmpid() + "\' ? (Y/N)");

        Scanner sc = new Scanner(System.in);
        char answer = sc.next().trim().toUpperCase().charAt(0);
        if (answer == 'Y') {
            client.removeEMSEmployee(emsEmployeeDTO);
        } else {
            // do nothing
        }

        System.out.println("Bye!");
    }

    public void addEMSEmployee(EmsEmployeeDTO emsEmployeeDTO) {
        boolean result = emsEmployeeManagement.addEmployee(emsEmployeeDTO);
        if (result) {
            System.out.println("The add operation is successful.");
        } else {
            System.out.println("The add operation fails!");
        }
    }

    public void removeEMSEmployee(EmsEmployeeDTO emsEmployeeDTO) {
        boolean result = emsEmployeeManagement.removeEmployee(emsEmployeeDTO.getEmpid());
        if (result) {
            System.out.println("The remove operation is successful.");
        } else {
            System.out.println("The remove operation fails!");
        }
    }

    public boolean findEMSEmployee(String empid) {
        return emsEmployeeManagement.hasEmployee(empid);
    }

    public void updateEMSEmployee(EmsEmployeeDTO emsEmsEmployeeDTO) {
        boolean result = emsEmployeeManagement.updateEmpolyeeDetails(emsEmsEmployeeDTO);
        if (result) {
            System.out.println("The remove operation is successful.");
        } else {
            System.out.println("The remove operation fails!");
        }
    }

}
