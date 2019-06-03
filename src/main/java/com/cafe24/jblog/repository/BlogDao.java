package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertVo(BlogVo vo) {
		return sqlSession.insert("blog.insertVo",vo);
	}
	
	public BlogVo getInfoById(String id) {
		return sqlSession.selectOne("blog.getInfoById",id);
	}
	
	public void updateTitleById(BlogVo vo) {
		sqlSession.update("blog.updateTitleById",vo);
	}
	
	public void updateVoById(BlogVo vo) {
		sqlSession.update("blog.updateVoById",vo);
	}

}
