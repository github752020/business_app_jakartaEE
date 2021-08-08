/**
 * ED - Secure - jdbc
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - SetupEmsEmployeeDB
 *
 * This is the main program to set up the database table and records
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package secure;

import java.util.ArrayList;
import util.MyHash;

/**
 *
 * @author elau
 */
public class SetupEmsEmployeeDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        MyEmsEmployeeDB db = new MyEmsEmployeeDB();

        // make sure no name conflicts
        db.dropEmsEmployeeTable();

        // create the database table
        System.out.println("Create an empty EMS_EMPLOYEE database table");
        db.createEmsEmployeeTable();

        // prepare list
        ArrayList<EmsEmployee> empList = prepareEmsEmployeeData();

        // add data to db
        System.out.println("Add several static records in the database table");
        db.addRecords(empList);
    }

    public static ArrayList<EmsEmployee> prepareEmsEmployeeData() {
        ArrayList<EmsEmployee> empList = new ArrayList<>();

        String memo1 = "123456";
        String pwd1 = MyHash.getSHA256HashedString(memo1);

        EmsEmployee emsEmployee1 = new EmsEmployee("000001", "Man Lau", pwd1,
                "elau@secure.com.au", "9876543210", "Secure EN510a", "What is my name?",
                "Man", "123456", "12345678", 50000, "ED-EMS-ADMIN", true, memo1);

        String memo2 = "234567";
        String pwd2 = MyHash.getSHA256HashedString(memo2);

        EmsEmployee emsEmployee2 = new EmsEmployee("000002", "James T. Kirk", pwd2,
                "jkirk@secure.com.au", "8765432109", "Secure EN511a", "What is my name?",
                "James", "234567", "98765432", 90000, "ED-EMS-ADMIN", true, memo2);

        String memo3 = "345678";
        String pwd3 = MyHash.getSHA256HashedString(memo3);

        EmsEmployee emsEmployee3 = new EmsEmployee("000003", "Sheldon Cooper", pwd3,
                "scooper@secure.com.au", "7654321098", "Secure EN512a", "What is my last name?",
                "Cooper", "345678", "56789012", 60000, "ED-EMS-USERS", true, memo3);

        String memo4 = "456789";
        String pwd4 = MyHash.getSHA256HashedString(memo4);

        EmsEmployee emsEmployee4 = new EmsEmployee("000004", "Harry Potter", pwd4,
                "hpotter@secure.com.au", "6543210987", "Secure EN513a", "What is my last name?",
                "Potter", "012345", "67890123", 70000, "ED-EMS-USERS", true, memo4);

        String memo5 = "567890";
        String pwd5 = MyHash.getSHA256HashedString(memo5);

        EmsEmployee emsEmployee5 = new EmsEmployee("000005", "Clark Kent", pwd5,
                "ckent@secure.com.au", "5432109876", "Secure EN514a", "What is my last name?",
                "Kent", "123456", "78901234", 80000, "ED-EMS-USERS", true, memo5);

        empList.add(emsEmployee1);
        empList.add(emsEmployee2);
        empList.add(emsEmployee3);
        empList.add(emsEmployee4);
        empList.add(emsEmployee5);

        return empList;
    }
}
