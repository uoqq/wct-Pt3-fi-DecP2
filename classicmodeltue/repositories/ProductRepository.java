package sit.int202.classicmodeltue.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sit.int202.classicmodeltue.entities.Product;

import java.util.List;

public class ProductRepository {
    private static int PAGE_SIZE = 10;
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }

    public int getDefaultPageSize() {
        return PAGE_SIZE;
    }

    public List<Product> findAll(int page, int pageSize) {
        int startPosition = (page - 1) * pageSize;
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("PRODUCT.FIND_ALL");
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<Product> productList = query.getResultList();
        return productList;
    }

    public int countAll() {
        EntityManager entityManager = getEntityManager();
        int number = ((Number) entityManager.createNamedQuery("PRODUCT.COUNT").getSingleResult()).intValue();
        return number;
    }

    public Product findProduct(String productCode) {
        return getEntityManager().find(Product.class, productCode);
    }

    public List<Product> findByCatOrDesc(String searchParam) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("PRODUCT.FIND_BY_ALL_COLUMN");
//        query.setFirstResult(0);
//        query.setMaxResults(PAGE_SIZE);
        query.setParameter("p1", "%"+searchParam+"%");
        query.setParameter("p2", "%"+searchParam+"%");
        query.setParameter("p3", "%"+searchParam+"%");
//        System.out.println(query.getParameter("p1").getName()+ ", "+  query.getParameterValue("p1"));

        List<Product> productList = query.getResultList();
        return productList;
    }
}
