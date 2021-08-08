/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - CDI Bean - MyLoginCDIBean
 *
 * This is the CDI bean for the login features
 *
 * Created on 24/03/2017
 *
 * Modified on 5/04/2020
 *
 * Purposes: Redo in JavaEE 8
 */
package web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elau
 */
@Named(value = "myLoginCDIBean")
@SessionScoped
public class MyLoginCDIBean implements Serializable {

    private static final String LOGOUT = "logout";
    
    @Inject
    private EmsEmployeeCDIBean emsEmployeeCDIBean;
    
     @Inject
    private AllorderCDIBean allorderCDIBean;

    /**
     * Creates a new instance of MyLoginCDIBean
     */
    public MyLoginCDIBean() {
    }

    public void isActive(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();
        try{
        if (!emsEmployeeCDIBean.isEmployeeActive()){
           
            fc.getExternalContext().redirect("/ED-Secure-war/faces/authFailure.xhtml");
        }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public String logoutResult() {
        // terminate the session by invalidating the session context
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.logout();
            if (request.isUserInRole("ED-EMS-ADMIN")) {
                System.out.println("Yes, user is in ED-EMS-ADMIN role");
            }
        } catch (Exception ex) {
            // do nothing
            ex.printStackTrace();
            System.out.println("log out ...");
        }
        // terminate the session by invalidating the session context
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        // terminate the user's login credentials
        return LOGOUT;
    }
}
