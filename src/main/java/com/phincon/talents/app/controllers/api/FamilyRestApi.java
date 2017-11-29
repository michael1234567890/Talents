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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.FamilyRepository;
import com.phincon.talents.app.model.hr.Family;

@RestController
@RequestMapping("api")
public class FamilyRestApi {
	private static String SORT = "name";

	@Autowired
	FamilyRepository repository;

	@RequestMapping(value = "/family", method = RequestMethod.GET)
	public Iterable findAll(
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "count", defaultValue = "10", required = false) int count,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "sort", defaultValue = "firstName", required = false) String sortProperty) {
		Page result = repository.findAll(new PageRequest(page, count, new Sort(
				direction, sortProperty)));
		return result.getContent();
	}

	@RequestMapping(value = "/family/{id}", method = RequestMethod.GET)
	public Family find(@PathVariable Long id) {
		Family detail = repository.findOne(id);
		if (detail == null) {
			throw new CustomException("Family not found");
		} else {
			return detail;
		}
	}

	@RequestMapping(value = "/family/search", method = RequestMethod.GET)
	public List search(@RequestParam("q") String queryTerm) {
		List listFamily = repository.search("%" + queryTerm + "%");
		return listFamily == null ? new ArrayList<>() : listFamily;
	}

	@RequestMapping(value = "/family/{id}", method = RequestMethod.DELETE)
	public HttpEntity delete(@PathVariable Long id) {
		Family family = find(id);
		repository.delete(family);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/family", method = RequestMethod.POST)
	public Family create(@RequestBody @Valid Family family) {
		return repository.save(family);
	}



}
