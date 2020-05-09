package com.pycogroup.pizza.order.service;

import com.pycogroup.pizza.order.common.GenericResponse;
import com.pycogroup.pizza.order.dto.OrderDto;

public interface OrderService {

  GenericResponse createOrder(OrderDto order);
}
