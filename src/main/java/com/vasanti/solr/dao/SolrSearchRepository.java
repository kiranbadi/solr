/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.dao;

import com.vasanti.solr.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;

/**
 *
 * @author Kiran
 */
public interface SolrSearchRepository {

    GroupPage<Order> findAllGroupByCustomerMobile();


}
