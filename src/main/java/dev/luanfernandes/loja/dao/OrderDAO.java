package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Order;
import dev.luanfernandes.loja.vo.SalesReportVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderDAO {
    private final EntityManager em;

    public OrderDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Order product) {
        this.em.persist(product);
    }

    public BigDecimal totalAmountSold() {
        String jpql = "SELECT SUM(o.totalPrice) FROM Order o";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<SalesReportVO> salesReport() {
        String jpql =
                "SELECT new dev.luanfernandes.loja.vo.SalesReportVO(" +
                        "product.name, " +
                        "SUM(item.quantity), " +
                        "MAX(orders.date)) " +
                        "FROM Order orders " +
                        "JOIN orders.items item " +
                        "JOIN item.product product " +
                        "GROUP BY product.name " +
                        "ORDER BY item.quantity DESC";

        return em.createQuery(jpql, SalesReportVO.class).getResultList();
    }

    public Order findOrderByCustomerId(Long id) {
        return em.createQuery("SELECT o FROM Order o JOIN FETCH o.customer WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
