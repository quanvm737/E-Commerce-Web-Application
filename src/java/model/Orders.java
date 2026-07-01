/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wuann_
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderId", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Orders.findByCustName", query = "SELECT o FROM Orders o WHERE o.custName = :custName"),
    @NamedQuery(name = "Orders.findByCustAddr", query = "SELECT o FROM Orders o WHERE o.custAddr = :custAddr"),
    @NamedQuery(name = "Orders.findByCustPhone", query = "SELECT o FROM Orders o WHERE o.custPhone = :custPhone"),
    @NamedQuery(name = "Orders.findByDeliveredDate", query = "SELECT o FROM Orders o WHERE o.deliveredDate = :deliveredDate"),
    @NamedQuery(name = "Orders.findByTotalValue", query = "SELECT o FROM Orders o WHERE o.totalValue = :totalValue"),
    @NamedQuery(name = "Orders.findByOrderState", query = "SELECT o FROM Orders o WHERE o.orderState = :orderState"),
    @NamedQuery(name = "Orders.findByPaymentMethods", query = "SELECT o FROM Orders o WHERE o.paymentMethods = :paymentMethods")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;
    @Column(name = "custName")
    private String custName;
    @Column(name = "custAddr")
    private String custAddr;
    @Column(name = "custPhone")
    private String custPhone;
    @Column(name = "deliveredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredDate;
    @Column(name = "totalValue")
    private Integer totalValue;
    @Column(name = "orderState")
    private String orderState;
    @Column(name = "paymentMethods")
    private String paymentMethods;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderDetails> orderDetailsList;

    public Orders() {
    }

    public Orders(Integer orderId) {
        this.orderId = orderId;
    }

    public Orders(Integer orderId, String custName, String custAddr, String custPhone, Date deliveredDate, Integer totalValue, String orderState, String paymentMethods, List<OrderDetails> orderDetailsList) {
        this.orderId = orderId;
        this.custName = custName;
        this.custAddr = custAddr;
        this.custPhone = custPhone;
        this.deliveredDate = deliveredDate;
        this.totalValue = totalValue;
        this.orderState = orderState;
        this.paymentMethods = paymentMethods;
        this.orderDetailsList = orderDetailsList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    @XmlTransient
    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orders[ orderId=" + orderId + " ]";
    }

}
