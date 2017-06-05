package com.phincon.talents.app.controllers.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.News;
import com.phincon.talents.app.services.NewsService;

@RestController
@RequestMapping("api")
public class NewsController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NewsService newsService;

	@RequestMapping(value = "/news", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<News>> news(OAuth2Authentication authentication){
    	
    	User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
    	
    	if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
    	
    	
    	Date publishedDate = new Date();
    	Date endDate = new Date();
    	Long company = user.getCompany();
    	
    	Iterable<News> listNews = newsService.getCurrentNews(company, publishedDate, endDate);
    	
    	System.out.println("news");
    	
    	return new ResponseEntity<Iterable<News>>(listNews, HttpStatus.OK);
    }
	

}
