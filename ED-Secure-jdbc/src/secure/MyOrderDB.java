/**
 * ED - Secure - jdbc
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - MyEmsEmployeeDB
 *
 * This is the class that implements the CRUD operations of the database
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package secure;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author elau
 */
public class MyOrderDB {

    // Database parameters for connection - url, username, password
    static String url;
    static String username;
    static String password;

    /**
     * constructor using default url, username and password
     */
    public MyOrderDB() {
        // set default parameters for Derby and JavaDB
        url = "jdbc:derby://localhost:1527/sun-appserv-samples";
        username = "APP";
        password = "APP";
    }

    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection(url, username, password);
    }

    public void createOrderTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE ALLORDER ( "
                    + "ORID VARCHAR(18) , "
                    + "CUSID VARCHAR(6) , "
                    + "ORDATE VARCHAR(50), "
                    + "DEDATE VARCHAR(50), "
                    + "QUANTITY DOUBLE, "
                    + "TOTAL DOUBLE, "
                    + "PAID BOOLEAN,"
                    + "DELIVERED BOOLEAN,"
                    + "CONSTRAINT PK_ORDER PRIMARY KEY (ORID))");

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void dropOrderTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE ALLORDER");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void addRecords(ArrayList<Order> orders) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO ALLORDER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            for (Order o : orders) {
                pStmnt.setString(1, o.getOrid());
                pStmnt.setString(2, o.getCusid());
                pStmnt.setString(3, o.getOrdate());
                pStmnt.setString(4, o.getDedate());
                pStmnt.setDouble(5, o.getQuantity());
                pStmnt.setDouble(6, o.getTotal());
                pStmnt.setBoolean(7, o.isPaid());
                pStmnt.setBoolean(8, o.isDelivered());
                

                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
}
