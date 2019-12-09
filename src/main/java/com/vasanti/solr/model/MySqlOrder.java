/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Kiran
 */

@Entity
@Table(name="orders")
public class MySqlOrder implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long orderId;
    
    @Column(name="ordername")
    private String orderName;
    
    @Column(name="orderdescription")	
    private String orderDescription;
       
    @Column(name="productname")
    private String productName;
	
    @Column(name="customername")
    private String customerName;
        
    @Column(name="customermobile")
    private String customerMobile;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    @Override
    public String toString() {
        return "MySqlOrder{" + "orderId=" + orderId + ", orderName=" + orderName + ", orderDescription=" + orderDescription + ", productName=" + productName + ", customerName=" + customerName + ", customerMobile=" + customerMobile + '}';
    }

    
    
    
}
