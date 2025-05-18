package com.spring.product_service_new.controller;


import com.spring.product_service_new.beans.ProductResponse;
import com.spring.product_service_new.beans.UserResponse;
import com.spring.product_service_new.dto.ProductDetailsRequestDto;
import com.spring.product_service_new.services.ProductService;
import com.spring.product_service_new.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String HelloWorld(){
        return "hello from product-service";
    }


    @PostMapping("/createProduct")
    public ProductResponse createProduct(@RequestBody ProductDetailsRequestDto productRequest){
        ProductResponse productResponse = new ProductResponse();
        log.info("saving of product process is started");
        Object res = productService.saveProduct(productRequest);

        log.info("product saved..");
        productResponse.setData(res);
        productResponse.setMessage("data saved..!!");
        return productResponse;

    }

    @PostMapping("/createUser")
    public UserResponse createUser(@RequestParam(name = "userName") String userName){
        UserResponse response = new UserResponse();
        log.info("saving of user process is started..!");

        Object res = userService.saveUser(userName);

        log.info("product saved..");
        response.setData(res);
        response.setMessage("data saved..!!");
        return response;

    }
}
