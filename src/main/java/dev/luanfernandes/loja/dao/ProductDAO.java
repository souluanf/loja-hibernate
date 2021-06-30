package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductDAO {
    private final EntityManager em;

    public ProductDAO(EntityManager em) {
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

    public List<Product> productsByCategoryName(String name) {
        return em.createNamedQuery("productsByCategoryName", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal findAPriceByProductName(String name) {
        String jpql = "SELECT p.price from Product p where p.name = :name";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Product> findByParameters(String name, BigDecimal price, LocalDate registrationDate) {
        String jpql = "SELECT o FROM Order o WHERE 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " AND o.name = :name";
        }
        if (price != null) {
            jpql += " AND o.name = :name";
        }
        if (registrationDate != null) {
            jpql += " AND o.registrationDate = :registrationDate";
        }

        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (price != null) {
            query.setParameter("price", price);
        }
        if (registrationDate != null) {
            query.setParameter("registrationDate", registrationDate);
        }

        return query.getResultList();
    }

    public List<Product> findByParametersWithCriteria(String name, BigDecimal price, LocalDate registrationDate) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);
        Predicate and = builder.and();

        if (name != null && !name.trim().isEmpty()) {
            and = builder.and(and, builder.equal(from.get("name"), name));
        }
        if (price != null) {
            and = builder.and(and, builder.equal(from.get("price"), price));
        }
        if (registrationDate != null) {
            and = builder.and(and, builder.equal(from.get("registrationDate"), registrationDate));
        }

        query.where(and);

        return em.createQuery(query).getResultList();


    }

}
