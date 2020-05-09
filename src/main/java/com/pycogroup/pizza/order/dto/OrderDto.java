package com.pycogroup.pizza.order.dto;

import java.util.List;

import com.pycogroup.pizza.order.model.UserInfo;

import lombok.Getter;
import lombok.Setter;

public class OrderDto {

  public OrderDto(UserInfo userInfo, List<ProductDto> cartInfo) {
    super();
    this.userInfo = userInfo;
    this.cartInfo = cartInfo;
  }

  @Getter
  @Setter
  private UserInfo userInfo;

  @Getter
  @Setter
  private List<ProductDto> cartInfo;
}
