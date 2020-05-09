package com.pycogroup.pizza.order.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pycogroup.pizza.order.common.GenericResponse;
import com.pycogroup.pizza.order.common.ResponseDto;
import com.pycogroup.pizza.order.dto.OrderDto;
import com.pycogroup.pizza.order.service.OrderService;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/pizza")
public class OrderApi {

  @Autowired
  OrderService orderService;

  @RequestMapping(
      value="/orders", 
      method=RequestMethod.POST, 
      consumes="application/JSON")
  public ResponseEntity<Object> createOrder(
      @RequestBody OrderDto order,
      UriComponentsBuilder ucb) {
    String baseUri = ucb.build().toString();
    HttpHeaders headers = new HttpHeaders();
    URI locationUri = URI.create(baseUri + "/orders");
    headers.setLocation(locationUri);
    Object response = orderService.createOrder(order);
    if (response instanceof GenericResponse) {
      
      return new ResponseEntity<Object>(ResponseDto.builder().code(HttpStatus.CREATED.value()).data(response).build(),
                                      headers,
                                      HttpStatus.CREATED);
    } else {
      return new ResponseEntity<Object>(ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).data(response).build(),
                                      headers,
                                      HttpStatus.INTERNAL_SERVER_ERROR);
      
    }
  }
}
