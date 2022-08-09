package com.example.duanlon.service;

public interface IAbstractService<T>{
    T save(T entity);
    T findById(Long entityId);
    void delete(T entity);
    void deleteById(Long entityId);
    Iterable<T> findAll();
}
