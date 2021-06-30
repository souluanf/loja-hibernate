package dev.luanfernandes.loja.application;

import dev.luanfernandes.loja.dao.CategoryDAO;
import dev.luanfernandes.loja.dao.ProductDAO;
import dev.luanfernandes.loja.model.Category;
import dev.luanfernandes.loja.model.Product;
import dev.luanfernandes.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CriteriaApplication {
    public static void main(String[] args) {
        popularDatabase();

        EntityManager em = JpaUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        productDAO.findByParametersWithCriteria("PS5", new BigDecimal("100"),LocalDate.now());


    }

    public static void popularDatabase() {

        Category smartphones = new Category("SMARTPHONE");
        Category videogames = new Category("VIDEOGAME");
        Category musicalInstruments = new Category("MUSICAL INSTRUMENTS");

        Product poco = new Product("POCO X3", "Poco X3 NFC", new BigDecimal("1400"), smartphones);
        Product ps5 = new Product("PS5", "Playstation 5", new BigDecimal("3000"), videogames);
        Product guitar = new Product("LES PAUL", "LES PAUL ELECTRIC GUITAR", new BigDecimal("1500"), musicalInstruments);

        EntityManager em = JpaUtil.getEntityManager();

        CategoryDAO categoryDAO = new CategoryDAO(em);
        ProductDAO productDAO = new ProductDAO(em);

        em.getTransaction().begin();

        categoryDAO.save(smartphones);
        productDAO.save(poco);

        categoryDAO.save(videogames);
        productDAO.save(ps5);

        categoryDAO.save(musicalInstruments);
        productDAO.save(guitar);

        em.getTransaction().commit();
        em.close();
    }
}
