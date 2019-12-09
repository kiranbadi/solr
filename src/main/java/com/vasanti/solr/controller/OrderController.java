/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasanti.solr.controller;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.vasanti.solr.dao.SolrOrderRepository;
import com.vasanti.solr.model.MySqlOrder;
import com.vasanti.solr.model.Order;
import com.vasanti.solr.service.MySqlOrderService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kiran
 */
@RestController
public class OrderController {

    @Autowired
    SolrOrderRepository solrOrderRepository;

    @Autowired
    MySqlOrderService mysqlOrderService;

    @PostMapping("/order")
    public String createOrder(@RequestBody Order order) {
        String description = "Order Created";
        solrOrderRepository.save(order);
        return description;
    }

    @GetMapping("/order/{orderid}")
    public Order readOrder(@PathVariable Long orderid) {
        return solrOrderRepository.findByOrderid(orderid);
    }

    //FIXME: For some reason solr is not updating by id.It is rather adding a new record
    @PutMapping("/order/{orderid}")
    public String updateOrder(@PathVariable Long orderid, @RequestBody Order order) {
        String description = "Order Updated";
        solrOrderRepository.save(order);
        return description;
    }

    @DeleteMapping("/order/{orderid}")
    public String deleteOrder(@PathVariable Long orderid) {
        String description = "Order Deleted";
        solrOrderRepository.delete(solrOrderRepository.findByOrderid(orderid));
        return description;
    }

    @GetMapping("/order/desc/{orderDesc}/{page}")
    public List<Order> findOrder(@PathVariable String orderDesc, @PathVariable int page) {
        return solrOrderRepository.findByOrderDescription(orderDesc, PageRequest.of(page, 2)).getContent();
    }

    @GetMapping("/order/search/{searchTerm}/{page}")
    public List<Order> findOrderBySearchTerm(@PathVariable String searchTerm, @PathVariable int page) {
        return solrOrderRepository.findByCustomerQuery(searchTerm, PageRequest.of(page, 2)).getContent();
    }

    @PostMapping("/orderdata")
    public String createOrderData(@RequestBody MySqlOrder mySqlOrder) {
        String description = "Order Data Created";
        mysqlOrderService.saveService(mySqlOrder);
        return description;
    }

    @GetMapping("/datageneration/{count}")
    public String generatedata(@PathVariable int count) {
        String description = " Order count craated is  " + count;
        for (int i = 0; i <= count; i++) {
            MySqlOrder orders = getOrders();
            mysqlOrderService.saveService(orders);
        }
        return description;
    }

    // Imports the data into solr collection from mysql database
    @GetMapping("/solrj/dataimport/fullimport")
    public String index() throws SolrServerException, IOException {
        String description = "Successfully indexed data from mysql ";
        SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/Order").build();
        ModifiableSolrParams params = new ModifiableSolrParams();
        QueryRequest request = new QueryRequest(params);
        request.setPath("/dataimport");
        params.set("command", "full-import");
        client.request(request);
        return description;
    }

    // Bulk inserts of orders
    @GetMapping("/bulkdatageneration/{count}")
    public String generatebulkdata(@PathVariable int count) {
        String description = " Order count craated is  " + count;
        List<MySqlOrder> orderList = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            MySqlOrder orders = getOrders();
            orderList.add(orders);
        }
        mysqlOrderService.saveAllService(orderList);
        return description;
    }

    //Solrj populate and search collection    
    @GetMapping("/populatesolrj")
    public String populateSolr() throws IOException, SolrServerException {
        SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/Order").build();
        for (int i = 0; i < 1000; ++i) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("cat", "book");
            doc.addField("id", "book-" + i);
            doc.addField("name", "The Legend of the Hobbit part " + i);
            doc.addField("store", "amazon.com");
            client.add(doc);
            if (i % 100 == 0) {
                client.commit();  // periodically flush
            }
        }
        client.commit();
        SolrQuery query = new SolrQuery();
        query.setQuery("sony digital camera");
        query.addFilterQuery("cat:electronics", "store:amazon.com");
        query.setFields("id", "price", "merchant", "cat", "store");
        query.setStart(0);
        query.set("defType", "edismax");

        QueryResponse response = client.query(query);
        SolrDocumentList results = response.getResults();
        for (int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i));
        }
        return "Successfully uploaded data to solr server";
    }
    

    // Class to generate test orders data on the fly.
    public MySqlOrder getOrders() {
        Lorem lorem = LoremIpsum.getInstance();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        MySqlOrder order = new MySqlOrder();
        String orderName = lorem.getTitle(8,12);
        order.setOrderName(orderName + " - " + generatedString);
        String para = lorem.getParagraphs(3, 4);
        order.setOrderDescription(para);
        String productName = lorem.getName();
        order.setProductName(productName);
        String customerName = lorem.getNameMale();
        order.setCustomerName(customerName);
        String customerMobile = lorem.getPhone();
        order.setCustomerMobile(customerMobile);
        return order;
    }

}
