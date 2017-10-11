package com.phincon.talents.app.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.HolidayRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.HolidayDTO;
import com.phincon.talents.app.model.Holiday;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.services.HolidayService;
import com.phincon.talents.app.utils.CustomMessage;

@RestController
@RequestMapping("api")
public class HolidayController {

	@Autowired
	HolidayService holidayService;
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/holiday", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<Holiday>> listHoliday(OAuth2Authentication authentication){
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
    	
    	if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
    	
    	Iterable<Holiday> holidays = holidayService.findByCompany(user.getCompany());
    	if(holidays.equals(null)){
    		return new ResponseEntity(HttpStatus.NO_CONTENT);
    	}
		
		return new ResponseEntity<Iterable<Holiday>>(holidays, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/holiday", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> submitHoliday(@RequestBody HolidayDTO request){
		
		Holiday holiday = new Holiday();
		holiday.setName(request.getName());
		holiday.setDescription(request.getDescription());
		holiday.setDate(request.getDate());
		holiday.setCompany(request.getCompany());
		holidayService.save(holiday);
		
		return new ResponseEntity<CustomMessage>(new CustomMessage("Submit Holiday Success", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/holiday/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<CustomMessage> updateHoliday(
			@RequestBody HolidayDTO request, @PathVariable Long id){
		
		Holiday holiday = find(id);
		holiday.setName(request.getName());
		holiday.setDescription(request.getDescription());
		holiday.setDate(request.getDate());
		holiday.setCompany(request.getCompany());
		holidayService.save(holiday);
		
		return new ResponseEntity<CustomMessage>(new CustomMessage("Update Holiday Success", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/holiday/{id}", method = RequestMethod.DELETE)
	public HttpEntity deleteCompany(@PathVariable Long id){
		Holiday holiday = find(id);
		holidayRepository.delete(holiday);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/holiday/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Holiday find(@PathVariable Long id){
		Holiday holiday = holidayRepository.findOne(id);
		if(holiday == null){
			throw new NotFound();
		}else{
			return holiday;
		}
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class NotFound extends RuntimeException{
		
	}
	
}
