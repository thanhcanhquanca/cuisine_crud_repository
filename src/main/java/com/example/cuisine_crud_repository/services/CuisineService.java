package com.example.cuisine_crud_repository.services;

import com.example.cuisine_crud_repository.model.Cuisine;
import com.example.cuisine_crud_repository.repository.ICuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuisineService implements ICuisineService {

    @Autowired
    private ICuisineRepository cuisineRepository;

    @Override
    public Iterable<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    @Override
    public void save(Cuisine cuisine) {
        cuisineRepository.save(cuisine);
    }

    @Override
    public Optional<Cuisine> findById(Long id) {
        return cuisineRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        cuisineRepository.deleteById(id);

    }

    @Override
    public void update(Long id, Cuisine cuisine) {
        Optional<Cuisine> existingCuisine = cuisineRepository.findById(id);
        if (existingCuisine.isPresent()) {
            Cuisine updatedCuisine = existingCuisine.get();
            updatedCuisine.setName(cuisine.getName());
            updatedCuisine.setDescription(cuisine.getDescription());
            updatedCuisine.setCategory(cuisine.getCategory());
            updatedCuisine.setImage(cuisine.getImage());
            cuisineRepository.save(updatedCuisine);
        } else {
            throw new RuntimeException("Cuisine with id " + id + " not found");
        }
    }

    @Override
    public long count() {
        return cuisineRepository.count();
    }

    @Override
    public Page<Cuisine> findAll(Pageable pageable) {
        return cuisineRepository.findAll(pageable);
    }

    @Override
    public Page<Cuisine> findByNameContaining(String name, Pageable pageable) {
        return cuisineRepository.findByNameContaining(name,pageable);
    }

}
