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
public class SetupCustomerDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        MyCustomerDB db = new MyCustomerDB();

        // make sure no name conflicts
        db.dropCustomerTable();

        // create the database table
        System.out.println("Create an empty CUSTOMER database table");
        db.createCustomerTable();

        // prepare list
        ArrayList<Customer> cusList = prepareCustomerData();

        // add data to db
        System.out.println("Add several static records in the database table");
        db.addRecords(cusList);
    }

    public static ArrayList<Customer> prepareCustomerData() {
        ArrayList<Customer> cusList = new ArrayList<>();

        String memo1 = "123456";
        String pwd1 = MyHash.getSHA256HashedString(memo1);

        Customer customer1 = new Customer("100001", "Absolute Bakery", pwd1,"absolute@bakery.com.au",
                "9876543210", "Shop 510 Swinburne Avenue Hawthorn VIC 3000", memo1);

        String memo2 = "234567";
        String pwd2 = MyHash.getSHA256HashedString(memo2);

        Customer customer2 = new Customer("100002", "Beautiful Cafe", pwd2,"beautiful@cafe.com.au",
                "9876543210", "Shop 999 Autumn Street Hawthorn VIC 3000", memo2);

        String memo3 = "345678";
        String pwd3 = MyHash.getSHA256HashedString(memo3);

        Customer customer3 = new Customer("100003", "Luna Catering", pwd3,"luna@catering.com.au",
                "9876543210", "483 Monash Avenue Dandenong VIC 3000", memo3);

        String memo4 = "456789";
        String pwd4 = MyHash.getSHA256HashedString(memo4);

        Customer customer4 = new Customer("100004", "Mariott Hotel", pwd4,"mariott@hotel.com.au",
                "9876543210", "777-888 Southern Road Melbourne VIC 3000", memo4);

        
        cusList.add(customer1);
        cusList.add(customer2);
        cusList.add(customer3);
        cusList.add(customer4);

        return cusList;
    }
}
