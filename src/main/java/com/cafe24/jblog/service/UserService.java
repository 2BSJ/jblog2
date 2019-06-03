package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public int signup(UserVo vo) {
		
		boolean result = false;
		
		//유저가입
		result= userDao.insertVo(vo)==1;
		if(result==false) {
			return 0;
		} 
		
		//가입완료되면 블로그생성
		BlogVo blogVo = new BlogVo();
		blogVo.setId(vo.getId());
		blogVo.setTitle(vo.getName()+"의 Spring 이야기");
		blogVo.setLogo("/images/spring-logo.jpg");
		
		result = blogDao.insertVo(blogVo)==1;
		if(result==false) {
			return 0;
		}
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(blogVo.getId());
		categoryVo.setDescription("카테고리 지정을 안한경우");
		categoryVo.setName("미분류");
		
		result = categoryDao.insertVo(categoryVo) == 1;
		if(result==false) {
			return 0; //exception handler로 처리할것
		}

		return 1;
			

	}
	
	public UserVo getByIdandPassword(String id,String password) {
		return userDao.getByIdandPassword(id, password);
	}
}
