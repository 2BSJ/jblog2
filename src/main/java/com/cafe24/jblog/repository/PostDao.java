package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getVoListByCategoryNo(Long categoryNo) {
		return sqlSession.selectList("post.getVoListByCategoryNo",categoryNo);
	}
	
	public PostVo getVoByPostNo(Long postNo) {
		return sqlSession.selectOne("post.getVoByPostNo",postNo);
	}
	
	public long getDefaultPostNoByCategoryNo(Long categoryNo) {
		try {
		return sqlSession.selectOne("post.getDefaultPostNoByCategoryNo",categoryNo);
		}
		catch(Exception e){
			System.out.println("PostDao.getDefaultPostNoByCategoryNo::dont have defaultPost");
			return 0L;
		}
	}
	
	public void insertVo(PostVo vo) {
		sqlSession.insert("post.insertVo",vo);
	}
	
	public void deleteVo(CategoryVo vo) {
		sqlSession.delete("post.deleteVo",vo);
	}
}
