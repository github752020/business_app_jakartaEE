/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Data Transfer Object - EmsEmployeeDTO
 *
 * This is the DTO for EmsEmployee entity class
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author elau
 */
public class AllorderDTO implements Serializable {
    
    private String orid;
    private String cusid;
    private String ordate;
    private String dedate;
    private double quantity;
    private double total;
    private boolean paid;
    private boolean delivered;

    public AllorderDTO(String orid, String cusid, String ordate, String dedate, double quantity, double total, boolean paid, boolean delivered) {
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

    public boolean getPaid() {
        return paid;
    }

    public boolean getDelivered() {
        return delivered;
    }

    
}
