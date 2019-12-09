/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.service;

import com.vasanti.solr.model.MySqlOrder;

/**
 *
 * @author Kiran
 */
public interface MySqlOrderService {
    
    MySqlOrder saveService(MySqlOrder mysqlOrder);
    
    Iterable<MySqlOrder> saveAllService(Iterable<MySqlOrder> MySqlOrders);
    
}
