package com.wearsafe.web.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wearsafe.helper.Utils;
import com.wearsafe.service.PersonService;
import com.wearsafe.web.controller.vo.PersonVO;
import com.wearsafe.web.exception.RecordNotFoundException;

/**
 * 
 * @author nagendra
 *
 */
//We are using Spring Runner here since we want to utilize spring features inside test cases
@RunWith(SpringRunner.class) // this is software which executes your test cases and finally generates summary of it.
@WebMvcTest(value = PersonController.class,secure=false)
public class PersonControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private PersonService mockPersonService;

	@Test
	public void testGetAllPersonsWhenListNotEmpty() throws Exception {
		List<PersonVO> personVos=new ArrayList<PersonVO>();
		PersonVO personVO1=new PersonVO(10L,"James","King","james@gmail.com",Utils.currentDate(),Utils.currentDate());
		PersonVO personVO2=new PersonVO(20L,"Maks","Padera","maks@gmail.com",Utils.currentDate(),Utils.currentDate());
		personVos.add(personVO1);
		personVos.add(personVO2);
		//below line is called stubbing the behavior
		given(mockPersonService.findPersons()).willReturn(personVos);
		  // when + then
		mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("list-persons"))
        .andExpect(model().attribute("personList", hasSize(2)))
        .andExpect(model().attribute("personList", hasItem(
                allOf(
                        hasProperty("id", is(10L)),
                        hasProperty("email", is("james@gmail.com")),
                        hasProperty("firstName", is("James"))
                )
        )))
        .andExpect(model().attribute("personList", hasItem(
                allOf(
                        hasProperty("id", is(20L)),
                        hasProperty("email", is("maks@gmail.com")),
                        hasProperty("firstName", is("Maks"))
                )
        )));
		verify(mockPersonService, times(1)).findPersons();
		verifyNoMoreInteractions(mockPersonService);
	}
	
	
	@Test
	public void testGetAllPersonsWhenListEmpty() throws Exception {
		List<PersonVO> personVos=new ArrayList<PersonVO>();
		//below line is called stubbing the behavior
		given(mockPersonService.findPersons()).willReturn(personVos);
		  // when + then
		mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("list-persons"))
        .andExpect(model().attribute("personList", hasSize(0)));
		verify(mockPersonService, times(1)).findPersons();
		verifyNoMoreInteractions(mockPersonService);
	}
	
	
	
	@Test
	public void  testCreatePerson() throws Exception {
		   PersonVO personVO1=new PersonVO(0L,"James","King","james@gmail.com",Utils.currentDate(),Utils.currentDate());
		   PersonVO personVO2=new PersonVO(10L,"James","King","james@gmail.com",Utils.currentDate(),Utils.currentDate());
	       given(mockPersonService.createOrUpdatePerson(personVO1)).willReturn(personVO2);
	       this.mockMvc.perform(MockMvcRequestBuilders.post("/createPerson")
	    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .param("email", "james@gmail.com")
	            .param("firstName", "James")
	            .param("lastName", "King"))
	       		.andExpect(status().isFound())
	       		.andExpect(view().name("redirect:/"));
	}
	
	@Test
	public void  editPersonWhenExist() throws Exception {
		    PersonVO personVO=new PersonVO(10L,"James","King","james@gmail.com",Utils.currentDate(),Utils.currentDate());
	        given(mockPersonService.findPersonById(10L)).willReturn(personVO);
	        mockMvc.perform(get("/edit/{id}", 10L))
           .andExpect(status().isOk())
           .andExpect(view().name("add-edit-person"))
           .andExpect(model().attribute("person", hasProperty("id", is(10L))))
           .andExpect(model().attribute("person", hasProperty("email", is("james@gmail.com"))))
           .andExpect(model().attribute("person", hasProperty("firstName", is("James"))))
           .andExpect(model().attribute("person", hasProperty("lastName", is("King"))));
	       	verify(mockPersonService, times(1)).findPersonById(10L);
	       	verifyNoMoreInteractions(mockPersonService);
	}
	
	
	@Test
	public void  editPersonWhenNoExist() throws Exception {
		    PersonVO personVO=new PersonVO(10L,"James","King","james@gmail.com",Utils.currentDate(),Utils.currentDate());
	        given(mockPersonService.findPersonById(10L)).willReturn(personVO);
	        mockMvc.perform(get("/edit"))
           .andExpect(status().isOk())
           .andExpect(view().name("add-edit-person"))
           .andExpect(model().attribute("person", hasProperty("id", is(0L))));
	}

}
