/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ALLORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Allorder.findAll", query = "SELECT a FROM Allorder a"),
    @NamedQuery(name = "Allorder.findByOrid", query = "SELECT a FROM Allorder a WHERE a.orid = :orid"),
    @NamedQuery(name = "Allorder.findByCusid", query = "SELECT a FROM Allorder a WHERE a.cusid = :cusid"),
    @NamedQuery(name = "Allorder.findByOrdate", query = "SELECT a FROM Allorder a WHERE a.ordate = :ordate"),
    @NamedQuery(name = "Allorder.findByDedate", query = "SELECT a FROM Allorder a WHERE a.dedate = :dedate"),
    @NamedQuery(name = "Allorder.findByQuantity", query = "SELECT a FROM Allorder a WHERE a.quantity = :quantity"),
    @NamedQuery(name = "Allorder.findByTotal", query = "SELECT a FROM Allorder a WHERE a.total = :total"),
    @NamedQuery(name = "Allorder.findByPaid", query = "SELECT a FROM Allorder a WHERE a.paid = :paid"),
    @NamedQuery(name = "Allorder.findByDelivered", query = "SELECT a FROM Allorder a WHERE a.delivered = :delivered")})
public class Allorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "ORID")
    private String orid;
    @Size(max = 6)
    @Column(name = "CUSID")
    private String cusid;
    @Size(max = 50)
    @Column(name = "ORDATE")
    private String ordate;
    @Size(max = 50)
    @Column(name = "DEDATE")
    private String dedate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "PAID")
    private Boolean paid;
    @Column(name = "DELIVERED")
    private Boolean delivered;

    public Allorder() {
    }

    public Allorder(String orid) {
        this.orid = orid;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getOrdate() {
        return ordate;
    }

    public void setOrdate(String ordate) {
        this.ordate = ordate;
    }

    public String getDedate() {
        return dedate;
    }

    public void setDedate(String dedate) {
        this.dedate = dedate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orid != null ? orid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Allorder)) {
            return false;
        }
        Allorder other = (Allorder) object;
        if ((this.orid == null && other.orid != null) || (this.orid != null && !this.orid.equals(other.orid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Allorder[ orid=" + orid + " ]";
    }
    
}
