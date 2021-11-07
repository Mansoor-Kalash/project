package com.dammak.project401;

import com.dammak.project401.models.Hospital;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HospitalRepo extends CrudRepository<Hospital,Long> {
//    Hospital findByPlaceName(String placeName);
    Hospital findByUserName(String username);
    List<Hospital> findAllByPlaceName(String placeName);


}
