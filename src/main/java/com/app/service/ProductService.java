package com.app.service;

import java.util.Comparator;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.dto.ProductDto;
import com.app.repository.category.CategoryRepository;
import com.app.repository.category.CategoryRepositoryImpl;
import com.app.repository.producer.ProducerRepository;
import com.app.repository.producer.ProducerRepositoryImpl;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

public class ProductService {

    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();

    public void addProduct(ProductDto productDto) {
        try {
            String categoryName = productDto.getCategoryDto().getName();
            String producerName = productDto.getProducerDto().getName();

            Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing category with name " + categoryName));

            Producer producer = producerRepository.findByName(producerName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing producer with name " + producerName));

            Product product = fromProductDtoToProduct(productDto);
            product.setCategory(category);
            product.setProducer(producer);

            productRepository.addOrUpdate(product);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "Add product exception");
        }
    }

    public void printAllProducts() {
        productRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Product::getId))
            .forEach(product -> System.out.println(product.getId() + ". " + product.getName()));
    }
}
