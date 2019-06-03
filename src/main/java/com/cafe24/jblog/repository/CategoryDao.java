package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertVo(CategoryVo vo) {
		return sqlSession.insert("category.insertVo",vo);
	}
	
	public List<CategoryVo> getCategoryList(String blogId){
		return sqlSession.selectList("category.getCategoryList",blogId);
	}
	
	public Long getDefaultCategoryNoById(String blogId) {
		return sqlSession.selectOne("category.getDefaultCategoryNoById",blogId);
	}
	
	public List<String> getCategoryNameById(String blogId){
		return sqlSession.selectList("category.getCategoryNameById",blogId);
	}
	
	public Long getCategoryNoByIdandName(String blogId,String categoryName) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("blogId", blogId);
		map.put("categoryName", categoryName);
		return sqlSession.selectOne("category.getCategoryNoByIdandName",map);
	}
	
	public int deleteVo(CategoryVo vo) {
		return sqlSession.delete("category.deleteVo",vo);
	}
}
