package dev.luanfernandes.loja;

import dev.luanfernandes.loja.dao.CategoryDAO;
import dev.luanfernandes.loja.dao.ProdutoDAO;
import dev.luanfernandes.loja.model.Category;
import dev.luanfernandes.loja.model.Product;
import dev.luanfernandes.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        registryProduct();

        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        List<Product> products = produtoDAO.findAByCategoryName("smartphone");
        products.forEach(System.out::println);

        BigDecimal productPrice = produtoDAO.findAPriceByProductName("Poco X3");
        System.out.println(productPrice);


    }

    public static void registryProduct() {
        Category category = new Category("smartphone");
        Product product = new Product("Poco X3", "Poco X3 NFC", new BigDecimal("1400"), category);

        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);

        em.getTransaction().begin();

        categoryDAO.save(category);
        produtoDAO.save(product);

        em.getTransaction().commit();
        em.close();
    }
}
