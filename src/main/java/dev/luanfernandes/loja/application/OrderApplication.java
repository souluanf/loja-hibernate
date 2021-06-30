package dev.luanfernandes.loja.application;

import dev.luanfernandes.loja.dao.CategoryDAO;
import dev.luanfernandes.loja.dao.CustomerDAO;
import dev.luanfernandes.loja.dao.OrderDAO;
import dev.luanfernandes.loja.dao.ProductDAO;
import dev.luanfernandes.loja.model.*;
import dev.luanfernandes.loja.util.JpaUtil;
import dev.luanfernandes.loja.vo.SalesReportVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderApplication {
    public static void main(String[] args) {
        popularDatabase();

        EntityManager em = JpaUtil.getEntityManager();

        CustomerDAO customerDAO = new CustomerDAO(em);
        ProductDAO productDAO = new ProductDAO(em);

        Customer customer = customerDAO.findById(1L);

        Product product = productDAO.findById(1L);
        Product product2 = productDAO.findById(2L);
        Product product3 = productDAO.findById(2L);


        em.getTransaction().begin();

        Order order = new Order(customer);
        order.addItem(new OrderItem(10, order, product));
        order.addItem(new OrderItem(40, order, product2));

        Order order2 = new Order(customer);
        order.addItem(new OrderItem(2, order2, product3));

        OrderDAO orderDAO = new OrderDAO(em);
        orderDAO.save(order);

        em.getTransaction().commit();

        BigDecimal totalSold = orderDAO.totalAmountSold();

        System.out.println("Total Sold: " + totalSold);

        List<SalesReportVO> salesReport = orderDAO.salesReport();
        salesReport.forEach(System.out::println);

        em.close();

    }

    public static void popularDatabase() {
        EntityManager em = JpaUtil.getEntityManager();

        Category smartphones = new Category("SMARTPHONE");
        Category videogames = new Category("VIDEOGAME");
        Category musicalInstruments = new Category("MUSICAL INSTRUMENTS");

        Product phone = new Product("POCO X3", "Poco X3 NFC", new BigDecimal("1400"),smartphones );
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("3000"), videogames);
        Product musicalInstrument = new Product("LES PAUL", "LES PAUL ELECTRIC GUITAR", new BigDecimal("1500"), musicalInstruments);

        Customer customer = new Customer("Bill Fernandes", "321321");

        CategoryDAO categoryDAO = new CategoryDAO(em);
        ProductDAO productDAO = new ProductDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);


        em.getTransaction().begin();

        categoryDAO.save(smartphones);
        productDAO.save(phone);

        categoryDAO.save(videogames);
        productDAO.save(videogame);

        categoryDAO.save(musicalInstruments);
        productDAO.save(musicalInstrument);

        customerDAO.save(customer);

        em.getTransaction().commit();
        em.close();
    }
}
