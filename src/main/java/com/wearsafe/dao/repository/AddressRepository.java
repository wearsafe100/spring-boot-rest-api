package com.wearsafe.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wearsafe.dao.entity.AddressEntity;

/**
 * 
 * This is repository class based on spring data + jpa
 * @author nagendra
 * 
 */
@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
