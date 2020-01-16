package com.wearsafe.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wearsafe.dao.entity.PersonEntity;

/**
 * 
 * This is repository class based on spring data + jpa
 * @author nagendra
 * 
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
