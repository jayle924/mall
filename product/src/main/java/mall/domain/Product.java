package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.ProductApplication;

@Entity
@Table(name = "Product_table")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Integer stock;

    public static ProductRepository repository() {
        ProductRepository productRepository = ProductApplication.applicationContext.getBean(
            ProductRepository.class
        );
        return productRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseStock(DeliveryCompleted deliveryCompleted) {
        /** Example 1:  new item 
        Product product = new Product();
        repository().save(product);

        */

        /** Example 2:  finding and process */
        
        repository().findById(deliveryCompleted.getProductId()).ifPresent(product->{
            System.out.println("1: "+product.getStock());
            System.out.println("2: "+deliveryCompleted.getQty());
            product.setStock(product.getStock() - deliveryCompleted.getQty());
            repository().save(product);
         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increaseStock(DeliveryCanceled deliveryCanceled) {
        /** Example 1:  new item 
        Product product = new Product();
        repository().save(product);

        */

        /** Example 2:  finding and process */
        
        repository().findById(deliveryCanceled.getProductId()).ifPresent(product->{
            
            product.setStock(product.getStock() + deliveryCanceled.getQty());
            repository().save(product);
         });

    }
    //>>> Clean Arch / Port Method

}
