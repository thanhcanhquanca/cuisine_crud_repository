package com.example.cuisine_crud_repository.repository;

import com.example.cuisine_crud_repository.model.Cuisine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ICuisineRepository extends CrudRepository<Cuisine, Long> {
    Page<Cuisine> findByNameContaining(String name, Pageable pageable);

    Page<Cuisine> findAll(Pageable pageable);
}
