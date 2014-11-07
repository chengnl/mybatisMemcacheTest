package com.vrv.im.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vrv.im.domain.Blog;
import com.vrv.im.mapper.BlogMapper;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 下午12:05:31
 *@version 1.0
 *@Description:单线程更新查询测试
 */
public class Update implements Runnable{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Update.class.getName());
	private SqlSessionFactory sqlSessionFactory;
    public Update(SqlSessionFactory sqlSessionFactory){
    	this.sqlSessionFactory=sqlSessionFactory;
    }
	@Override
	public void run() {
		SqlSession session= sqlSessionFactory.openSession();
		int i=0;
		while(true){
			   i++;
			   String addr ="china wuhan"+i;
			   BlogMapper mapper = session.getMapper(BlogMapper.class);
			   Blog blog = new Blog();
			   blog.setId(1);
			   blog.setTestid(2);
			   blog.setName("zhangsan");
			   blog.setAddr(addr);
			   mapper.updateBlogByObj(blog);
			   LOGGER.debug("update blog to :"+blog==null?"blog is null ":blog.toString());
			   session.commit();
			   try {
				  TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
	
}
