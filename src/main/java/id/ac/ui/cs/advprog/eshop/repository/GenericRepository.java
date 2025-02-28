package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface GenericRepository<T> {
    T create(T entity);
    Iterator<T> findAll();
    T findById(String id);
    T edit(String id, T entity);
    void delete(String id);
}
