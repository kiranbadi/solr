/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.dao;

import com.vasanti.solr.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 *
 * @author Kiran
 */
public interface SolrOrderRepository extends SolrCrudRepository<Order, Long> {

    Order findByOrderid(Long orderid);

    @Query("odesc:*?0*")
    Page<Order> findByOrderDescription(String searchTerm, Pageable pageable);

    @Query("odesc:*?0* OR oname:*?0* OR pname:*?0*")
    Page<Order> findByCustomerQuery(String searchTerm, Pageable pageable);

    @Query(value = "*:*")
    @Facet(fields = {Order.CUSTOMER_MOBILE})
    FacetPage<Order> findAllAndFacetByCustomerMobile(Pageable pageable);

}
