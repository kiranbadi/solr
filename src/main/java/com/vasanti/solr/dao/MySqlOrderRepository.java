/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.dao;

import com.vasanti.solr.model.MySqlOrder;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kiran
 */

@Repository
public interface MySqlOrderRepository extends CrudRepository<MySqlOrder, Long> {
    @Override
    MySqlOrder save(MySqlOrder mysqlOrder);
    
 //  Iterable<MySqlOrder> saveAll(Iterable<MySqlOrder> MySqlOrders);
    
}
