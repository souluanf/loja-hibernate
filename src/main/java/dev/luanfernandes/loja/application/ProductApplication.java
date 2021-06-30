package dev.luanfernandes.loja.application;

import dev.luanfernandes.loja.dao.CategoryDAO;
import dev.luanfernandes.loja.dao.ProductDAO;
import dev.luanfernandes.loja.model.Category;
import dev.luanfernandes.loja.model.Product;
import dev.luanfernandes.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductApplication {
    public static void main(String[] args) {
        registryProduct();

        EntityManager em = JpaUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);

        List<Product> products = productDAO.productsByCategoryName("SMARTPHONE");
        products.forEach(System.out::println);

    }

    public static void registryProduct() {
        Category category = new Category("smartphone");
        Product product = new Product("Poco X3", "Poco X3 NFC", new BigDecimal("1400"), category);

        EntityManager em = JpaUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);

        em.getTransaction().begin();

        categoryDAO.save(category);
        productDAO.save(product);

        em.getTransaction().commit();

        em.close();
    }
}
