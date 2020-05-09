package com.pycogroup.pizza.order.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "products")
@Builder
public class Product {

  @Id
  @Getter
  private String id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String imageURL;
  
  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private String ingredients;

  @Getter
  @Setter
  private String servingSize;

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
