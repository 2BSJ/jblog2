package com.cafe24.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.security.AuthAdmin;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Controller
@RequestMapping("/{blogId:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping({ "", "/{pathCategory}", "/{pathCategory}/{pathPost}" })
	public String blogIndex(@PathVariable String blogId, @PathVariable Optional<Long> pathCategory,
			@PathVariable Optional<Long> pathPost, Model model) {

		model.addAllAttributes(blogService.getBlogInfo(blogId, pathCategory, pathPost));

		return "blog/blog-main";

	}

	@AuthAdmin
	@RequestMapping({ "/goadmin/{siteName}" })
	public String goAdminBasic(@PathVariable String blogId, @PathVariable String siteName, Model model) {

		return blogService.getAdminBlogInfo(blogId, siteName, model);

	}

	@PostMapping("/basicsetting")
	public String basicSetting(@PathVariable String blogId,
							   @RequestParam(value = "title", required = true, defaultValue = "") String title,
							   @RequestParam(value = "file", required = false) MultipartFile multipartFile) {

		String logo = blogService.restore(multipartFile);
		blogService.updateBlogVo(blogId,title,logo);


		return "redirect:/" + blogId + "/goadmin/basic";
	}

	@RequestMapping("/addcategory")
	public String addcategory(@PathVariable String blogId, @ModelAttribute CategoryVo vo) {
		vo.setBlogId(blogId);
		blogService.addCategory(vo);
		return "redirect:/" + blogId + "/goadmin/category";
	}

	@RequestMapping("/deletecategory")
	public String deletecategory(@PathVariable String blogId, @ModelAttribute CategoryVo vo,
			@RequestParam("no") Long no) {
		vo.setBlogId(blogId);
		vo.setNo(no);
		blogService.deleteCategory(vo);
		return "redirect:/" + blogId + "/goadmin/category";
	}

	@RequestMapping("/write")
	public String write(@PathVariable String blogId, @ModelAttribute PostVo vo) {

		blogService.writePost(blogId, vo);
		return "redirect:/" + blogId + "/goadmin/write";
	}

}
