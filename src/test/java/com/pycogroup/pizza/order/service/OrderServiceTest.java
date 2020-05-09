package com.pycogroup.pizza.order.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pycogroup.pizza.order.common.GenericResponse;
import com.pycogroup.pizza.order.model.AdditionalOption;
import com.pycogroup.pizza.order.model.Category;
import com.pycogroup.pizza.order.model.Option;
import com.pycogroup.pizza.order.model.Order;
import com.pycogroup.pizza.order.model.Pricing;
import com.pycogroup.pizza.order.model.Product;
import com.pycogroup.pizza.order.model.Size;
import com.pycogroup.pizza.order.model.UserInfo;
import com.pycogroup.pizza.order.repository.OrderRepository;

@SpringBootTest
public class OrderServiceTest {


  @Autowired
  OrderService orderService;
  
  @Autowired
  OrderRepository orderRepository;

  @BeforeEach
  public void init() {
  }
  
  @AfterEach
  public void cleaning() {
    orderRepository.deleteAll();
  }

  @DisplayName("createOrder test function")
  @Test
  public void createOrderTest() {
    //given
    UserInfo userInfo = UserInfo.builder()
                                  .name("Chi Pheo")
                                  .email("peacefulman@gmail.com")
                                  .phoneNumber("777777")
                                  .address("1 Vu Dai village")
                                  .paymentMethod("COD")
                                  .build();
    List<Size> size = new ArrayList<Size>();
    size.add(Size.SMALL);
    size.add(Size.MEDIUM);
    size.add(Size.LARGE);
    List<Integer> price = new ArrayList<Integer>();
    price.add(100000);
    price.add(200000);
    price.add(250000);
    List<Option> option = new ArrayList<Option>();
    option.add(Option.NONE);
    option.add(Option.EXTRA_CHEESE);
    List<Integer> optionPrice = new ArrayList<Integer>();
    optionPrice.add(0);
    optionPrice.add(40000);
    Product product = Product.builder()
                                 .name("Octopus Pizza")
                                 .imageURL("https://goodle.com/octopus")
                                 .pricing(new Pricing(size,price,0)) 
                                 .additionalOption(new AdditionalOption(option,optionPrice,0))
                                 .category(Category.PIZZA)
                                 .quantity(1)
                                 .id("5eb53755c9cb6804f57f9045")
                                 .build();
    List<Product> products = new ArrayList<>();
    products.add(product);
    Order order = Order.builder().cartInfo(products).userInfo(userInfo).build();
    GenericResponse response = orderService.createOrder(order);

    // when
    Object object = response.getData();

    // then
    Assertions.assertNotNull(object);
    Assertions.assertEquals(object, "Order Created Successfully!");
  }
}
