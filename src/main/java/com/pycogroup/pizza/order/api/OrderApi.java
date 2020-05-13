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

import com.pycogroup.pizza.order.common.ErrorResponseBody;
import com.pycogroup.pizza.order.common.GenericResponse;
import com.pycogroup.pizza.order.common.GenericResponseError;
import com.pycogroup.pizza.order.common.LinkEntity;
import com.pycogroup.pizza.order.common.Links;
import com.pycogroup.pizza.order.common.ResponseDto;
import com.pycogroup.pizza.order.model.Order;
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
      @RequestBody Order order,
      UriComponentsBuilder ucb) {
    String baseUri = ucb.build().toString();
    HttpHeaders headers = new HttpHeaders();
    URI locationUri = URI.create(baseUri + "/pizza/orders");
    headers.setLocation(locationUri);
    Object response = orderService.createOrder(order);
    Links links = new Links();
    links.add(LinkEntity.builder().href(locationUri).relation("Self").method(RequestMethod.POST).build());
    if (!(response instanceof GenericResponseError)) {
      
      return new ResponseEntity<Object>(ResponseDto.builder().code(HttpStatus.CREATED.value()).data(((GenericResponse) response).getData()).links(links.getLinks()).build(),
                                      headers,
                                      HttpStatus.CREATED);
    } else {
      ErrorResponseBody responseError = (ErrorResponseBody)((GenericResponseError) response).getData();
      if (responseError.getCode() == HttpStatus.UNAUTHORIZED.value()) {
        return new ResponseEntity<Object>(ResponseDto.builder().code(HttpStatus.UNAUTHORIZED.value()).data(response).links(links.getLinks()).build(),
                                      headers,
                                      HttpStatus.UNAUTHORIZED);
      } else {
        return new ResponseEntity<Object>(ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).data(response).links(links.getLinks()).build(),
                                      headers,
                                      HttpStatus.INTERNAL_SERVER_ERROR);
      }
      
    }
  }
}
