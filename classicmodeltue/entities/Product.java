package sit.int202.classicmodeltue.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
@NamedQueries({
    @NamedQuery(name = "PRODUCT.FIND_ALL", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "PRODUCT.COUNT", query = "SELECT count(p) as count FROM Product p"),
    @NamedQuery(name = "PRODUCT.FIND_BY_ALL_COLUMN",
            query = "SELECT p FROM Product p where p.productName like :p1 or p.productLine like :p2 or p.productDescription like :p3")
})
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private Double buyPrice;  // Cost
    @Column(name = "MSRP") // Manufacturer Suggestion Retail Price
    private Double price;
}
