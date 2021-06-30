package dev.luanfernandes.loja.application;

import dev.luanfernandes.loja.dao.CategoryDAO;
import dev.luanfernandes.loja.dao.CustomerDAO;
import dev.luanfernandes.loja.dao.OrderDAO;
import dev.luanfernandes.loja.dao.ProductDAO;
import dev.luanfernandes.loja.model.*;
import dev.luanfernandes.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceApplication {
    public static void main(String[] args) {
        popularDatabase();

        EntityManager em = JpaUtil.getEntityManager();

        OrderDAO orderDAO = new OrderDAO(em);
        Order order = orderDAO.findOrderByCustomerId(1L);
        em.close();

        System.out.println(order.getCustomer().getName());


    }

    public static void popularDatabase() {

        Category smartphones = new Category("SMARTPHONE");
        Category videogames = new Category("VIDEOGAME");
        Category musicalInstruments = new Category("MUSICAL INSTRUMENTS");

        Product poco = new Product("POCO X3", "Poco X3 NFC", new BigDecimal("1400"), smartphones);
        Product ps5 = new Product("PS5", "Playstation 5", new BigDecimal("3000"), videogames);
        Product guitar = new Product("LES PAUL", "LES PAUL ELECTRIC GUITAR", new BigDecimal("1500"), musicalInstruments);

        Customer customer = new Customer("Bill Fernandes", "321321");

        Order order = new Order(customer);
        order.addItem(new OrderItem(10, order, poco));
        order.addItem(new OrderItem(40, order, ps5));

        Order order2 = new Order(customer);
        order2.addItem(new OrderItem(10, order2, guitar));

        EntityManager em = JpaUtil.getEntityManager();

        CategoryDAO categoryDAO = new CategoryDAO(em);
        ProductDAO productDAO = new ProductDAO(em);
        OrderDAO orderDAO = new OrderDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);

        em.getTransaction().begin();

        categoryDAO.save(smartphones);
        productDAO.save(poco);

        categoryDAO.save(videogames);
        productDAO.save(ps5);

        categoryDAO.save(musicalInstruments);
        productDAO.save(guitar);

        customerDAO.save(customer);

        orderDAO.save(order);
        orderDAO.save(order2);

        em.getTransaction().commit();
        em.close();
    }
}
