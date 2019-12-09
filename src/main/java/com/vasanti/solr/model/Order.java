/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 *
 * @author Kiran
 */
@SolrDocument(collection = "Order")
public class Order {
    
    public final static String CUSTOMER_MOBILE = "customerMobile";

    public Order() {
    }

    public Order(Long orderid, String orderName, String orderDescription, String productName, String customerName, String customerMobile) {
        this.orderid = orderid;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
        this.productName = productName;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
    }

    
    
    @Id
    @Field("oid")
    @Indexed(name = "oid", type = "long")
    private Long orderid;

    @Indexed(name = "oname", type = "string")
    @Field
    private String orderName;

    @Field
    @Indexed(name = "odesc", type = "string")
    private String orderDescription;

    @Field
    @Indexed(name = "pname", type = "string")
    private String productName;

    @Field
    @Indexed(name = "cname", type = "string")
    private String customerName;

    @Field
    @Indexed(name = "cmobile", type = "string")
    private String customerMobile;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
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
        return "Order{" + "orderid=" + orderid + ", orderName=" + orderName + ", orderDescription=" + orderDescription + ", productName=" + productName + ", customerName=" + customerName + ", customerMobile=" + customerMobile + '}';
    }
    
    

}
