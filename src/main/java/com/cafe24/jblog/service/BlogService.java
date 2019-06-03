package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {

	private static final String SAVE_PATH = "/jblog-uploads/profile";
	private static final String URL = "/profile";
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	public Map<String,Object> getBlogInfo(String blogId,Optional<Long> pathCategory,Optional<Long> pathPost){
		
		Map<String,Object> blogInfoMap = new HashMap<String, Object>();
		
		blogInfoMap.put("categoryList", categoryDao.getCategoryList(blogId));
		blogInfoMap.put("blogVo", blogDao.getInfoById(blogId));
		
		Long categoryNo = 1L;
		Long postNo = 1L;
		if( pathPost.isPresent() ) {
			postNo = pathPost.get();
			categoryNo = pathCategory.get();
			
			blogInfoMap.put("postTitleList", postDao.getVoListByCategoryNo(categoryNo)); //path가 정상적으로 들어왔을때.
			blogInfoMap.put("postVo",postDao.getVoByPostNo(postNo));
			
		} else if( pathCategory.isPresent() ){
			categoryNo = pathCategory.get();
			
			blogInfoMap.put("postTitleList", postDao.getVoListByCategoryNo(categoryNo)); // path가 category까지만 들어왔을 때. 카테고리의 첫번째 글
			blogInfoMap.put("postVo",postDao.getVoByPostNo(postDao.getDefaultPostNoByCategoryNo(categoryNo)));
			
		}
		else {
			blogInfoMap.put("postTitleList", postDao.getVoListByCategoryNo(categoryDao.getDefaultCategoryNoById(blogId))); //path가 아이디만 들어왔을때 첫번째 카테고리의 첫번째 글
			blogInfoMap.put("postVo",postDao.getVoByPostNo(postDao.getDefaultPostNoByCategoryNo(categoryDao.getDefaultCategoryNoById(blogId))));
		}
		return blogInfoMap;
	}
	
	public String getAdminBlogInfo(String blogId, String siteName, Model model) {
		
		
		if("basic".equals(siteName)) {
			model.addAttribute("blogVo",blogDao.getInfoById(blogId));//헤더에 쓸정보 보냄
			return "blog/blog-admin-basic";
		}
		else if("category".equals(siteName)) {
			model.addAttribute("blogVo",blogDao.getInfoById(blogId));//헤더에 쓸정보 보냄
			model.addAttribute("categoryList",categoryDao.getCategoryList(blogId));
			return "blog/blog-admin-category";
		}
		else {
			model.addAttribute("blogVo",blogDao.getInfoById(blogId));//헤더에 쓸정보 보냄
			model.addAttribute("categoryList",categoryDao.getCategoryList(blogId));//카테고리 리스트 객체에담음
			return "blog/blog-admin-write";	
		}
			
	}
	
	public void updateBlogVo(String blogId,String title,String logo) {
		
		BlogVo vo = new BlogVo();
		vo.setId(blogId);
		vo.setTitle(title);
		vo.setLogo(logo);
		if("".equals(logo)) {
			blogDao.updateTitleById(vo);
		}else {
			blogDao.updateVoById(vo);
		}
	}
	
	public List<String> getCategoryNameById(String blogId){
		return categoryDao.getCategoryNameById(blogId);
	}
	
	public void writePost(String blogId,PostVo vo) {
		//카테고리 넘버값 찾아오기
		vo.setCategoryNo(categoryDao.getCategoryNoByIdandName(blogId,vo.getCategoryName()));
		postDao.insertVo(vo);
		
	}
	
	public List<CategoryVo> getCategoryList(String blogId){
		return categoryDao.getCategoryList(blogId);
	}
	
	public void addCategory(CategoryVo vo) {
		categoryDao.insertVo(vo);
	}
	public void deleteCategory(CategoryVo vo) {
		
		postDao.deleteVo(vo);
		categoryDao.deleteVo(vo);
	}
	
	public String restore(MultipartFile multipartFile) {
		
		String url = "";
		try {

			if (multipartFile.isEmpty()) {
				return url;
			}

			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
			
			// 저장
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			
			
			url = URL + "/" + saveFileName;
			//url = saveFileName;
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		return url;
	}

	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();

		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}
	
	
	

}
