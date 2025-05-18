package com.spring.product_service_new.services;

import com.spring.product_service_new.dto.ProductDetailsRequestDto;
import com.spring.product_service_new.model.DatabaseSequence;
import com.spring.product_service_new.model.Product;
import com.spring.product_service_new.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Autowired
    private MongoOperations mongoOperations;

    private final ProductRepository productRepository;


    public Long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("id").is(seqName)),
                new Update().inc("sequence",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }


    public Product saveProduct(ProductDetailsRequestDto productRequest){

        Product product = Product.builder()
                .id(generateSequence(Product.SEQUENCE_GENERATOR))
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        Product obj = productRepository.save(product);
        log.info("product is saved in DB...");
        return obj;

    }
}
