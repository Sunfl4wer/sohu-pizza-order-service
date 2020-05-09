package com.pycogroup.pizza.order.service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pycogroup.pizza.order.common.ErrorResponseBody;
import com.pycogroup.pizza.order.common.GenericResponse;
import com.pycogroup.pizza.order.common.GenericResponseError;
import com.pycogroup.pizza.order.common.LogExecutionStatus;
import com.pycogroup.pizza.order.common.Message;
import com.pycogroup.pizza.order.common.Reason;
import com.pycogroup.pizza.order.model.Order;
import com.pycogroup.pizza.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

  @Autowired
  OrderRepository orderRepository;

  @Override
  @LogExecutionStatus
  public GenericResponse createOrder(Order order) {
    Object cartProducts = order.getCartInfo();
    Order createdOrder = orderRepository.save(Order.builder().userInfo(order.getUserInfo()).cartInfo(cartProducts).build());
    if (orderRepository.existsById(createdOrder.getId())) {
      return new GenericResponse("Order Created Successfully!");
    } else {
      LinkedHashMap<String , Object> where = new LinkedHashMap<String , Object>();
      where.put("orderInfo", order);
      LinkedHashMap<String , Object> when = new LinkedHashMap<String , Object>();
      when.put("Internal Error", "orderInfo saved fail!");
      return new GenericResponseError(ErrorResponseBody.builder()
                                              .timestamp(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")))
                                              .status(HttpStatus.NOT_FOUND)
                                              .code(HttpStatus.NOT_FOUND.value())
                                              .message(Message.DOCUMENT_NOT_EXIST.getMessage())
                                              .reason(Reason.DOCUMENT_NOT_SAVED.getReason())
                                              .where(where)
                                              .when(when)
                                              .build());
    }

  }
}
