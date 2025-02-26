package com.example.cuisine_crud_repository.repository;

import com.example.cuisine_crud_repository.model.Cuisine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICuisineRepository extends CrudRepository<Cuisine, Long> {
    Page<Cuisine> findByNameContaining(String name, Pageable pageable);

    Page<Cuisine> findAll(Pageable pageable);

    @Query("SELECT c FROM Cuisine c ORDER BY c.name ASC")
    Page<Cuisine> findAllSortedAsc(Pageable pageable);

    @Query("SELECT c FROM Cuisine c ORDER BY c.name DESC")
    Page<Cuisine> findAllSortedDesc(Pageable pageable);

    @Query("SELECT c FROM Cuisine c ORDER BY RAND()")
    Page<Cuisine> findAllRandom(Pageable pageable);
}
