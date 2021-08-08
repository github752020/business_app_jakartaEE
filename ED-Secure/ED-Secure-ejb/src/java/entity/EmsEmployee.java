/**
 * ED - Secure
 *
 * Purpose : A simple application to perform CRUD operations on Secure company's employee database
 *
 * ED - Entity Class - EmsEmployee
 *
 * This is the entity class
 *
 * Created on 24/03/2017
 * 
 * Modified on 5/04/2020
 * 
 * Purposes: Redo in JavaEE 8
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author elau
 */
@Entity
@Table(name = "EMS_EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmsEmployee.findAll", query = "SELECT e FROM EmsEmployee e"),
    @NamedQuery(name = "EmsEmployee.findByEmpid", query = "SELECT e FROM EmsEmployee e WHERE e.empid = :empid"),
    @NamedQuery(name = "EmsEmployee.findByName", query = "SELECT e FROM EmsEmployee e WHERE e.name = :name"),
    @NamedQuery(name = "EmsEmployee.findByPassword", query = "SELECT e FROM EmsEmployee e WHERE e.password = :password"),
    @NamedQuery(name = "EmsEmployee.findByEmail", query = "SELECT e FROM EmsEmployee e WHERE e.email = :email"),
    @NamedQuery(name = "EmsEmployee.findByPhone", query = "SELECT e FROM EmsEmployee e WHERE e.phone = :phone"),
    @NamedQuery(name = "EmsEmployee.findByAddress", query = "SELECT e FROM EmsEmployee e WHERE e.address = :address"),
    @NamedQuery(name = "EmsEmployee.findBySecqn", query = "SELECT e FROM EmsEmployee e WHERE e.secqn = :secqn"),
    @NamedQuery(name = "EmsEmployee.findBySecans", query = "SELECT e FROM EmsEmployee e WHERE e.secans = :secans"),
    @NamedQuery(name = "EmsEmployee.findByBsbid", query = "SELECT e FROM EmsEmployee e WHERE e.bsbid = :bsbid"),
    @NamedQuery(name = "EmsEmployee.findByAccid", query = "SELECT e FROM EmsEmployee e WHERE e.accid = :accid"),
    @NamedQuery(name = "EmsEmployee.findBySalary", query = "SELECT e FROM EmsEmployee e WHERE e.salary = :salary"),
    @NamedQuery(name = "EmsEmployee.findByAppgroup", query = "SELECT e FROM EmsEmployee e WHERE e.appgroup = :appgroup"),
    @NamedQuery(name = "EmsEmployee.findByActive", query = "SELECT e FROM EmsEmployee e WHERE e.active = :active"),
    @NamedQuery(name = "EmsEmployee.findByMemo", query = "SELECT e FROM EmsEmployee e WHERE e.memo = :memo")})
public class EmsEmployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "EMPID")
    private String empid;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 64)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 10)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 60)
    @Column(name = "SECQN")
    private String secqn;
    @Size(max = 60)
    @Column(name = "SECANS")
    private String secans;
    @Size(max = 6)
    @Column(name = "BSBID")
    private String bsbid;
    @Size(max = 10)
    @Column(name = "ACCID")
    private String accid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Size(max = 15)
    @Column(name = "APPGROUP")
    private String appgroup;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "MEMO")
    private String memo;

    public EmsEmployee() {
    }

    public EmsEmployee(String empid) {
        this.empid = empid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecqn() {
        return secqn;
    }

    public void setSecqn(String secqn) {
        this.secqn = secqn;
    }

    public String getSecans() {
        return secans;
    }

    public void setSecans(String secans) {
        this.secans = secans;
    }

    public String getBsbid() {
        return bsbid;
    }

    public void setBsbid(String bsbid) {
        this.bsbid = bsbid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAppgroup() {
        return appgroup;
    }

    public void setAppgroup(String appgroup) {
        this.appgroup = appgroup;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmsEmployee)) {
            return false;
        }
        EmsEmployee other = (EmsEmployee) object;
        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmsEmployee[ empid=" + empid + " ]";
    }
    
}
