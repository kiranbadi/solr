/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.service;

import com.vasanti.solr.dao.MySqlOrderRepository;
import com.vasanti.solr.model.MySqlOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kiran
 */

@Service
public class MySqlOrderServiceImpl implements MySqlOrderService{

    @Autowired
    MySqlOrderRepository mysqlOrderRepository;
    
    @Override
    public MySqlOrder saveService(MySqlOrder mysqlOrder) {
        return mysqlOrderRepository.save(mysqlOrder);
    }


    @Override
    public Iterable<MySqlOrder> saveAllService(Iterable<MySqlOrder> MySqlOrders) {
        return mysqlOrderRepository.saveAll(MySqlOrders); 
    }
    
}
