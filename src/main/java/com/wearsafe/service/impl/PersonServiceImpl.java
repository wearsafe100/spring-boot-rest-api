package com.wearsafe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wearsafe.dao.entity.AddressEntity;
import com.wearsafe.dao.entity.PersonEntity;
import com.wearsafe.dao.repository.PersonRepository;
import com.wearsafe.helper.Utils;
import com.wearsafe.service.PersonService;
import com.wearsafe.web.controller.mapper.PersonMapper;
import com.wearsafe.web.controller.vo.PersonVO;
import com.wearsafe.web.exception.RecordNotFoundException;

/**
 * 
 * @author nagendra
 * 
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	public PersonRepository personRepository;
	

	public PersonRepository getPersonRepository() {
		return personRepository;
	}


	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}


	@Override
	public List<PersonVO> findPersons() {
		List<PersonVO> personVOs = new ArrayList<PersonVO>();
		for (PersonEntity personEntity : personRepository.findAll()) {
			personVOs.add(PersonMapper.toVO(personEntity));
		}
		return personVOs;
	}

	
	@Override
	public PersonVO createOrUpdatePerson(PersonVO person) {
		PersonEntity entity = PersonMapper.toEntity(person,Utils.MODE.CREATE);
		if (person.getId() == null) {
			entity = personRepository.save(entity);
			person.setId(entity.getId());
		} else {
			Optional<PersonEntity> optionalPerson = personRepository.findById(person.getId());
			if (optionalPerson.isPresent()) {
				PersonEntity newEntity = optionalPerson.get();
				BeanUtils.copyProperties(entity, newEntity,"addresses");
				List<AddressEntity> newAddressEntityList=new ArrayList<>();
				List<AddressEntity> addressEntityList=newEntity.getAddresses();
				for(AddressEntity addressEntity:addressEntityList) {
					if(entity.getAddresses()!=null && entity.getAddresses().size()>0) {
						  for(AddressEntity uaddressEntity:entity.getAddresses()) {
							  if(uaddressEntity.getAid()==null) {
								  newAddressEntityList.add(uaddressEntity);
								  break;
							  }else if(uaddressEntity.getAid().longValue()==addressEntity.getAid().longValue()){
								  BeanUtils.copyProperties(uaddressEntity, addressEntity);
								  newAddressEntityList.add(addressEntity);
								  break;
							  }
						  }
					}
				}
				newEntity.setAddresses(newAddressEntityList);
				//Utils.copyNonNullProperties(entity, newEntity);
				System.out.println(newEntity);
			}
		}
		return person;
	}

	@Override
	public PersonVO findPersonById(Long pid) {
		Optional<PersonEntity> optionalPerson = personRepository.findById(pid);
		PersonVO personVO = null;
		if (optionalPerson.isPresent()) {
			personVO = PersonMapper.toVO(optionalPerson.get());
		} else {
			throw new RecordNotFoundException("No person record exist for given id");
		}
		return personVO;
	}

	
	@Override
	public void deletePersonById(Long id) throws RecordNotFoundException {
		Optional<PersonEntity> persion = personRepository.findById(id);
		if (persion.isPresent()) {
			personRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No person record exist for given id");
		}
	}

}
