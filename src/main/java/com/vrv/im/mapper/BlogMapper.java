package com.vrv.im.mapper;

import java.util.List;
import java.util.Map;

import com.vrv.im.domain.Blog;

public interface BlogMapper {
	Blog selectBlog(int id);
	String selectBlogName(Blog blog);
	Blog selectBlogByObj(Blog blog);
	List<Map> selectBlogByMap(Map map);
	void insertBlogByObj(Blog blog);
	void insertBlogByMap(Map map);
	void updateBlogByObj(Blog blog);
}
