package com.phincon.talents.app.controllers.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employee;

@RestController
@RequestMapping("api")
public class EmployeeRestApi {
	private static String SORT = "firstName";

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Iterable findAll(OAuth2Authentication authentication,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "count", defaultValue = "10", required = false) int count,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "sort", defaultValue = "firstName", required = false) String sortProperty) {
		
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
		//System.out.println(details.toString());
		//System.out.println(String.format("Hello (%s)",authentication.getUserAuthentication().toString()));
		
		// User user = userRepository.findByUsernameCaseInsensitive(authentication.getUserAuthentication().getName());
		System.out.println(authentication.getPrincipal().toString());
		
		Page result = repository.findAll(new PageRequest(page, count, new Sort(
				direction, sortProperty)));
		return result.getContent();
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee find(@PathVariable Long id) {
		
		Employee detail = repository.findOne(id);
		if (detail == null) {
			throw new EmployeeNotFound();
		} else {
			return detail;
		}
	}

	@RequestMapping(value = "/employee/search", method = RequestMethod.GET)
	public List search(@RequestParam("q") String queryTerm) {
		List listEmployee = repository.search("%" + queryTerm + "%");
		return listEmployee == null ? new ArrayList<>() : listEmployee;
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public HttpEntity delete(@PathVariable Long id) {
		Employee employee = find(id);
		repository.delete(employee);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public Employee create(@RequestBody @Valid Employee employee) {
		return repository.save(employee);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class EmployeeNotFound extends RuntimeException {
	}

}
