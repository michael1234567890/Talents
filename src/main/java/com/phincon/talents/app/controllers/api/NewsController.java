package com.phincon.talents.app.controllers.api;

import java.util.Date;

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

import com.phincon.talents.app.dao.NewsRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.NewsDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.News;
import com.phincon.talents.app.services.NewsService;
import com.phincon.talents.app.utils.CustomMessage;

@RestController
@RequestMapping("api")
public class NewsController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	NewsService newsService;

	@Autowired
	NewsRepository newsRepository;

	@RequestMapping(value = "/user/news/current", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<News>> news(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Date today = new Date();
		Long company = user.getCompany();
		Iterable<News> listNews = newsService.getCurrentNews(company, today);

		return new ResponseEntity<Iterable<News>>(listNews, HttpStatus.OK);
	}

	@RequestMapping(value = "/news", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> submitNews(@RequestBody NewsDTO request) {

		News news = new News();
		news.setActive(request.getActive());
		news.setContent(request.getContent());
		news.setPublishedDate(request.getPublishedDate());
		news.setEndDate(request.getEndDate());
		news.setTitle(request.getTitle());
		news.setCompany(request.getCompany());
		newsService.save(news);

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Submit News Success", false), HttpStatus.OK);
	}

	@RequestMapping(value = "/news/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<CustomMessage> updateNews(
			@RequestBody NewsDTO request, @PathVariable Long id) {

		News news = find(id);
		news.setActive(request.getActive());
		news.setContent(request.getContent());
		news.setPublishedDate(request.getPublishedDate());
		news.setEndDate(request.getEndDate());
		news.setTitle(request.getTitle());
		news.setCompany(request.getCompany());
		newsService.save(news);

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Update News Success", false), HttpStatus.OK);
	}

	@RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
	public HttpEntity deleteNews(@PathVariable Long id) {
		News news = find(id);
		newsRepository.delete(news);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	@ResponseBody
	public News find(@PathVariable Long id) {
		News news = newsRepository.findOne(id);
		if (news == null) {
			throw new NotFound();
		} else {
			return news;
		}
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class NotFound extends RuntimeException {

	}

}
