package com.vrv.im.test;

import java.io.InputStream;

import junit.framework.TestCase;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vrv.im.domain.Blog;
import com.vrv.im.mapper.BlogMapper;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午11:38:53
 *@version 1.0
 *@Description:测试程序
 */
public class CacheTest extends TestCase{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheTest.class.getName());
	private SqlSession session;
	@Before
	public void setUp() throws Exception {
		  String resource = "mybatis-config.xml";
		   InputStream inputStream = Resources.getResourceAsStream(resource);
		   SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		   session = sqlSessionFactory.openSession();
	}
	@Test
	public void testInsertBlogByObj() throws Exception{
		   BlogMapper mapper = session.getMapper(BlogMapper.class);
		   Blog blog = new Blog();
		   blog.setId(1);
		   blog.setTestid(2);
		   blog.setName("zhangsan");
		   blog.setAddr("china beijing");
		   mapper.insertBlogByObj(blog);
		   session.commit();
	}
	@Test
	public void testUpdateBlogByObj() throws Exception{
		   BlogMapper mapper = session.getMapper(BlogMapper.class);
		   Blog blog = new Blog();
		   blog.setId(1);
		   blog.setTestid(2);
		   blog.setName("zhangsan");
		   blog.setAddr("china wuhan");
		   mapper.updateBlogByObj(blog);
		   session.commit();
	}
	@Test
	public void testSeletBlogByObj() throws Exception{
		   BlogMapper mapper = session.getMapper(BlogMapper.class);
		   Blog sblog = new Blog();
		   sblog.setId(1);
		   sblog.setTestid(2);
		   Blog blog = mapper.selectBlogByObj(sblog);
		   LOGGER.debug(blog.toString());
	}
	@After
	public void tearDown() throws Exception {
		session.close();
	}
}
