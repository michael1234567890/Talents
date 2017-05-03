package com.phincon.talents.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.services.CompanyService;

@RestController
@RequestMapping("api")
public class CompanyApiController {
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> listCompany() {
        List<Company> companies = companyService.findAll();
        if (companies.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
    }
 
}
