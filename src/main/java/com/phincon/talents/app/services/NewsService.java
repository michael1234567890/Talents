package com.phincon.talents.app.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.NewsRepository;
import com.phincon.talents.app.model.hr.News;

@Service
public class NewsService {
	
	@Autowired
	NewsRepository newsRepository;
	
	@Transactional
	public Iterable<News> findAll(){
		return newsRepository.findAll();
	}
	
	@Transactional
	public void save(News news){
		newsRepository.save(news);
	}
	
	@Transactional
	public Iterable<News> getCurrentNews(Long company, Date today){
		return newsRepository.getCurrentNews(company, today);
	}
}
