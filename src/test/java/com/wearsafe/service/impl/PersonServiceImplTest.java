package com.wearsafe.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wearsafe.dao.entity.PersonEntity;
import com.wearsafe.dao.repository.PersonRepository;
import com.wearsafe.helper.Utils;
import com.wearsafe.web.controller.vo.PersonVO;
import com.wearsafe.web.exception.RecordNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceImplTest {
	
	//dao layer we are auto wiring in service 
	@Mock
	private PersonRepository personRepository;
	
	
	@InjectMocks // this is creating an instance of StudentServiceImpl and injecting all the mock object inside
	private PersonServiceImpl personServiceImpl;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		System.out.println("@)@((@(@");
	}

	@Test
	public void testFindPersonsWhenListIsEmpty() {
		List<PersonEntity> personEntityList=new ArrayList<PersonEntity>();
	    Iterable<PersonEntity> iterable = personEntityList;
	    when(personRepository.findAll()).thenReturn(iterable);
	    List<PersonVO> personVOs=personServiceImpl.findPersons();
	    assertEquals(0, personVOs.size());
	}
	
	@Test
	public void testFindPersonsWhenListNotEmpty() {
		List<PersonEntity> personEntityList=new ArrayList<PersonEntity>();
		PersonEntity personEntity=new PersonEntity();
		personEntity.setDoe(Utils.currentDate());
		personEntity.setDom(Utils.currentDate());
		personEntity.setEmail("nagendra@gmail.com");
		personEntity.setFirstName("Jack");
		personEntity.setLastName("King");
		personEntity.setId(100L);
		personEntityList.add(personEntity);
	    Iterable<PersonEntity> iterable = personEntityList;
	    when(personRepository.findAll()).thenReturn(iterable);
	    List<PersonVO> personVOs=personServiceImpl.findPersons();
	    assertEquals(1, personVOs.size());
	    assertEquals("nagendra@gmail.com", personVOs.get(0).getEmail());
	}

	@Test
	public void testFindPersonByIdWhenRecordExist() {
		PersonEntity personEntity=new PersonEntity();
		personEntity.setDoe(Utils.currentDate());
		personEntity.setDom(Utils.currentDate());
		personEntity.setEmail("nagendra@gmail.com");
		personEntity.setFirstName("Jack");
		personEntity.setLastName("King");
		personEntity.setId(100L);
		Optional<PersonEntity> optionalPersonEntity=Optional.of(personEntity);
		//stubbing
		when(personRepository.findById(100L)).thenReturn(optionalPersonEntity);
		PersonVO personVO=personServiceImpl.findPersonById(100L);
		assertEquals("nagendra@gmail.com", personVO.getEmail());
		assertEquals("Jack", personVO.getFirstName());
		assertEquals("King", personVO.getLastName());
		assertEquals(new Long(100), personVO.getId());
	}
	
	@Test(expected=RecordNotFoundException.class)
	public void testFindPersonByIdWhenRecordNotExist() {
		Optional<PersonEntity> optionalPersonEntity=Optional.ofNullable(null);
		//stubbing
		when(personRepository.findById(104L)).thenReturn(optionalPersonEntity);
		personServiceImpl.findPersonById(104L);
	}

}
