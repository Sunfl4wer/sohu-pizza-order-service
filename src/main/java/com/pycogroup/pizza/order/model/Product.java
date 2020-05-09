package com.pycogroup.pizza.order.model;

import java.util.ArrayList;
import java.util.List;

import com.pycogroup.pizza.order.dto.ProductDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Product {

  @Getter
  @Setter
  private String id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String imageURL;

  @Getter
  @Setter
  private Pricing pricing;

  @Getter
  @Setter
  private AdditionalOption additionalOption;

  @Getter
  @Setter
  private Category category;

  @Getter
  @Setter
  private int quantity;

}
