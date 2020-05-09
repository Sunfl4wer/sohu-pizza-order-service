package com.pycogroup.pizza.order.dto;

import java.util.ArrayList;
import java.util.List;

import com.pycogroup.pizza.order.model.AdditionalOption;
import com.pycogroup.pizza.order.model.Category;
import com.pycogroup.pizza.order.model.Option;
import com.pycogroup.pizza.order.model.Pricing;
import com.pycogroup.pizza.order.model.Product;
import com.pycogroup.pizza.order.model.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ProductDto {

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
  private Object pricing;

  @Getter
  @Setter
  private Object additionalOption;

  @Getter
  @Setter
  private Category category;

  @Getter
  @Setter
  private int quantity;

  public Product toProduct() {
    if (this.category.equals(Category.PIZZA)) {
      return Product.builder()
        .id(this.id)
        .name(this.name)
        .imageURL(this.imageURL)
        .pricing((Pricing)this.pricing)
        .additionalOption((AdditionalOption)this.additionalOption)
        .category(this.category)
        .quantity(this.quantity)
        .build();
    } else {
      List<Size> sizes = new ArrayList<Size>();
      sizes.add(Size.ONE_SIZE);
      List<Integer> sizePrices = new ArrayList<Integer>();
      sizePrices.add((Integer)this.pricing);
      Pricing pricing = new Pricing(sizes,sizePrices,1);
      List<Option> options = new ArrayList<Option>();
      options.add(Option.NONE);
      List<Integer> optionPrices = new ArrayList<Integer>();
      optionPrices.add(0);
      AdditionalOption additionalOption = new AdditionalOption(options,optionPrices,0);
      return Product.builder()
        .id(this.id)
        .name(this.name)
        .imageURL(this.imageURL)
        .pricing(pricing)
        .additionalOption(additionalOption)
        .category(this.category)
        .quantity(this.quantity)
        .build();
    }
  }

  public static List<Product> toProductList(List<?> cardProducts) {
    List<Product> products = new ArrayList<>();
    for (int elem = 0; elem < cardProducts.size(); elem++) {
      products.add(( (ProductDto)(cardProducts.get(elem)) ).toProduct());
    }
    return products;
  }
}
