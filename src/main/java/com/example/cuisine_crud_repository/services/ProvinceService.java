package com.example.cuisine_crud_repository.services;

import com.example.cuisine_crud_repository.model.IProvinceDTO;
import com.example.cuisine_crud_repository.model.Province;
import com.example.cuisine_crud_repository.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService{

    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Province province) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Province> findByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Province> findAllSortedAsc(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Province> findAllSortedDesc(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Province> findAllRandom(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<IProvinceDTO> getAllProvinces() {
        return provinceRepository.getAllProvinces();
    }
}
