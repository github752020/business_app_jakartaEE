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
public class MyEmsEmployeeDB {

    // Database parameters for connection - url, username, password
    static String url;
    static String username;
    static String password;

    /**
     * constructor using default url, username and password
     */
    public MyEmsEmployeeDB() {
        // set default parameters for Derby and JavaDB
        url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        username = "APP";
        password = "APP";
    }

    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection(url, username, password);
    }

    public void createEmsEmployeeTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE EMS_EMPLOYEE ( "
                    + "EMPID CHAR(6) , "
                    + "NAME VARCHAR(50), "
                    + "PASSWORD CHAR(64), "
                    + "EMAIL VARCHAR(50), "
                    + "PHONE VARCHAR(10), "
                    + "ADDRESS VARCHAR(50), "
                    + "SECQN VARCHAR(60), "
                    + "SECANS VARCHAR(60), "
                    + "BSBID CHAR(6), "
                    + "ACCID VARCHAR(10), "
                    + "SALARY DECIMAL(10,2), "
                    + "APPGROUP VARCHAR(15), "
                    + "ACTIVE BOOLEAN,"
                    + "MEMO VARCHAR(255), "
                    + "CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMPID))");

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

    public void dropEmsEmployeeTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE EMS_EMPLOYEE");
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

    public void addRecords(ArrayList<EmsEmployee> emsEmployees) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO EMS_EMPLOYEE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            for (EmsEmployee emsEmployee : emsEmployees) {
                pStmnt.setString(1, emsEmployee.getEmpid());
                pStmnt.setString(2, emsEmployee.getName());
                pStmnt.setString(3, emsEmployee.getPassword());
                pStmnt.setString(4, emsEmployee.getEmail());
                pStmnt.setString(5, emsEmployee.getPhone());
                pStmnt.setString(6, emsEmployee.getAddress());
                pStmnt.setString(7, emsEmployee.getSecqn());
                pStmnt.setString(8, emsEmployee.getSecans());
                pStmnt.setString(9, emsEmployee.getBsbid());
                pStmnt.setString(10, emsEmployee.getAccid());
                pStmnt.setDouble(11, emsEmployee.getSalary());
                pStmnt.setString(12, emsEmployee.getAppgroup());
                pStmnt.setBoolean(13, emsEmployee.isActive());
                pStmnt.setString(14, emsEmployee.getMemo());

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
