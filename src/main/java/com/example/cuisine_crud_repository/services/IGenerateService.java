package com.example.cuisine_crud_repository.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();
    void save(T t);
    Optional<T> findById(Long id);
    void remove(Long id);
    void update(Long id, T t);
    long count();
    Page<T> findAll(Pageable pageable);
    Page<T> findByNameContaining(String name, Pageable pageable);
    Page<T> findAllSortedAsc(Pageable pageable);
    Page<T> findAllSortedDesc(Pageable pageable);
    Page<T> findAllRandom(Pageable pageable);
}
