package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    private final EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Product product) {
        this.em.persist(product);
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        String jpql = "SELECT p from Product p ";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> findAByName(String name) {
        String jpql = "SELECT p from Product p where p.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findAByCategoryName(String name) {
        String jpql = "SELECT p from Product p where p.category.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal findAPriceByProductName(String name) {
        String jpql = "SELECT p.price from Product p where p.name = :name";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
