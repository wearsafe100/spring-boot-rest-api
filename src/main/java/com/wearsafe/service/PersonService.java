package com.wearsafe.service;

import java.util.List;

import com.wearsafe.web.controller.vo.PersonVO;
import com.wearsafe.web.exception.RecordNotFoundException;

/**
 * 
 * @author nagendra
 *
 */
public interface PersonService {

	public PersonVO findPersonById(Long pid);

	public List<PersonVO> findPersons();

	PersonVO createOrUpdatePerson(PersonVO person);

	void deletePersonById(Long id) throws RecordNotFoundException;

}
