package com.pycogroup.pizza.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UserInfo {

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String email;

  @Getter
  @Setter
  private String phoneNumber;

  @Getter
  @Setter
  private String address;

  @Getter
  @Setter
  private String method;

  @Getter
  @Setter
  private String cardNumber;

  @Getter
  @Setter
  private String nameOnCard;

  @Getter
  @Setter
  private String expireDate;

  @Getter
  @Setter
  private String security;
}
