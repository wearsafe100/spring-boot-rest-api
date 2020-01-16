package com.wearsafe.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wearsafe.service.PersonService;
import com.wearsafe.web.controller.vo.PersonVO;
import com.wearsafe.web.exception.RecordNotFoundException;

/**
 * 
 * @author nagendra
 *
 */

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;
	
	public PersonController() {
		System.out.println("_@)@(@PersonController)@!)@)@");
		System.out.println("_@)@(@PersonController)@!)@)@");
	}

	@GetMapping("/")
	public String getAllPersons(Model model) {
		List<PersonVO> list = personService.findPersons();
		model.addAttribute("personList", list);
		return "list-persons";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			PersonVO personVO = personService.findPersonById(id.get());
			model.addAttribute("person", personVO);
		} else {
			PersonVO personVO=new PersonVO();
			personVO.setId(0L);
			model.addAttribute("person", personVO);
		}
		return "add-edit-person";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException {
		personService.deletePersonById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createPerson", method = RequestMethod.POST)
	public String createOrUpdateEmployee(PersonVO personVO){
		personService.createOrUpdatePerson(personVO);
		return "redirect:/";
	}
}
