/**
 * ED - Secure - jdbc
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - EmsEmployee
 *
 * This is the class that holds the information for the database record
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package secure;

import java.io.Serializable;

/**
 *
 * @author elau
 */
public class Order implements Serializable {
    
    private String orid;
    private String cusid;
    private String ordate;
    private String dedate;
    private double quantity;
    private double total;
    private boolean paid;
    private boolean delivered;

    public Order(String orid, String cusid, String ordate, String dedate, 
             double quantity, double total, boolean paid, boolean delivered) {
        this.orid = orid;
        this.cusid = cusid;
        this.ordate = ordate;
        this.dedate = dedate;
        this.quantity = quantity;
        this.total = total;
        this.paid = paid;
        this.delivered = delivered;
    }

    public String getOrid() {
        return orid;
    }

    public String getCusid() {
        return cusid;
    }

    public String getOrdate() {
        return ordate;
    }

    public String getDedate() {
        return dedate;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public void setOrdate(String ordate) {
        this.ordate = ordate;
    }

    public void setDedate(String dedate) {
        this.dedate = dedate;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }


}
