package com.example.cuisine_crud_repository.repository;

import com.example.cuisine_crud_repository.model.IProvinceDTO;
import com.example.cuisine_crud_repository.model.Province;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IProvinceRepository extends CrudRepository<Province,Long> {

        @Query(nativeQuery = true ,
        value = "\n" +
                "select\n" +
                "    province.id as id,\n" +
                "    province.name as name,\n" +
                "    COUNT(cuisine.name) as count\n" +
                "from province left join cuisine on province.id = cuisine.province_id group by province.id, province.name;")
        Iterable<IProvinceDTO> getAllProvinces();
}
