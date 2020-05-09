package com.pycogroup.pizza.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.pizza.order.model.Order;

public interface OrderRepository extends MongoRepository<Order , String>{

}
