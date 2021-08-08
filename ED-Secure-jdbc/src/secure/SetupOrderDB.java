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

/**
 *
 * @author elau
 */
public class SetupOrderDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        MyOrderDB db = new MyOrderDB();

        // make sure no name conflicts
        db.dropOrderTable();

        // create the database table
        System.out.println("Create an empty ALLORDER database table");
        db.createOrderTable();

        // prepare list
        ArrayList<Order> orList = prepareOrderData();

        // add data to db
        System.out.println("Add several static records in the database table");
        db.addRecords(orList);
    }

    public static ArrayList<Order> prepareOrderData() {
        ArrayList<Order> orList = new ArrayList<>();

        Order order1 = new Order("100001123456789012", "100001", "2020-06-01",
                "2020-07-14", 5, 10, true, true);
        Order order2 = new Order("100002123456789012", "100002", "2020-08-16",
                "2020-08-23", 3, 6, true, true);
        Order order3 = new Order("100003123456789012", "100003", "2021-05-22",
                "2021-07-05", 2, 4, true, false);
        Order order4 = new Order("100002343456789012", "100002", "2021-06-01",
                "2021-06-28", 8, 16, false, false);

        orList.add(order1);
        orList.add(order2);
        orList.add(order3);
        orList.add(order4);

        return orList;
    }
}
