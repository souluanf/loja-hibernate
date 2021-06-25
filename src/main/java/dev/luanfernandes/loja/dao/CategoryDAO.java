package dev.luanfernandes.loja.dao;

import dev.luanfernandes.loja.model.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {
    private final EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Category category) {
        this.em.persist(category);
    }
}
