package com.pycogroup.pizza.order.service;

import com.pycogroup.pizza.order.common.GenericResponse;
import com.pycogroup.pizza.order.model.Order;

public interface OrderService {

  GenericResponse createOrder(Order order);
}
