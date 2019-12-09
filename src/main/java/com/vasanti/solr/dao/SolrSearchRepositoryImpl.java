/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.dao;

import com.vasanti.solr.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.SimpleField;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.GroupPage;

/**
 *
 * @author Kiran
 */
public class SolrSearchRepositoryImpl implements SolrSearchRepository{

    @Autowired
    private SolrTemplate solrTemplate;
    
    @Override
    public GroupPage<Order> findAllGroupByCustomerMobile() {
        Field field = new SimpleField(Order.CUSTOMER_MOBILE);
        SimpleQuery query = new SimpleQuery(new SimpleStringCriteria("*:*"));
        GroupOptions options = new GroupOptions().addGroupByField(field);
        query.setGroupOptions(options);
        GroupPage<Order> page = solrTemplate.queryForGroupPage("Order",query, Order.class);
        return page;  
    }   
}
