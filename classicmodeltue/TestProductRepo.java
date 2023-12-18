package sit.int202.classicmodeltue;

import sit.int202.classicmodeltue.entities.Product;
import sit.int202.classicmodeltue.repositories.ProductRepository;

import java.util.List;

public class TestProductRepo {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        List<Product> productList = repository.findByCatOrDesc("moto");
       productList.forEach(p-> System.out.println(p.getProductName()));
    }
}
