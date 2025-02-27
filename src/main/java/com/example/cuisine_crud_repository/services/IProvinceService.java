package com.example.cuisine_crud_repository.services;

import com.example.cuisine_crud_repository.model.IProvinceDTO;
import com.example.cuisine_crud_repository.model.Province;

public interface IProvinceService extends IGenerateService<Province> {
    Iterable<IProvinceDTO> getAllProvinces();
}
