package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertVo(UserVo vo) {
		return sqlSession.insert("user.insertVo",vo);
	}
	
	public UserVo getByIdandPassword(String id,String password) {		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlSession.selectOne("user.getByIdandPassword",map);
		
		
		
		}
	}

