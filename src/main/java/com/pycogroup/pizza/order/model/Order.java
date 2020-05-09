package com.pycogroup.pizza.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "orders")
@Builder
public class Order {

  @Id
  @Getter
  private String id;

  @Getter
  @Setter
  private UserInfo userInfo;

  @Getter
  @Setter
  private Object cartInfo;
}
