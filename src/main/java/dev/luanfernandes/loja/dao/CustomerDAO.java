package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Customer;

import javax.persistence.EntityManager;

public class CustomerDAO {
    private final EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Customer customer) {
        this.em.persist(customer);
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }
}
