package com.vrv.im.test;

import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vrv.im.domain.Blog;
import com.vrv.im.mapper.BlogMapper;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 下午12:08:12
 *@version 1.0
 *@Description:查询线程
 */
public class Select implements Runnable{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Select.class.getName());
   private SqlSessionFactory sqlSessionFactory;
   public Select(SqlSessionFactory sqlSessionFactory){
	   this.sqlSessionFactory=sqlSessionFactory;
   }
	@Override
	public void run() {
		while(true){
			   SqlSession session= sqlSessionFactory.openSession();
			   BlogMapper mapper = session.getMapper(BlogMapper.class);
			   Blog sblog = new Blog();
			   sblog.setId(1);
			   sblog.setTestid(2);
			   Blog blog = mapper.selectBlogByObj(sblog);
			   LOGGER.debug("select blog to :"+blog==null?"blog is null ":blog.toString());
			   try {
				  TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   session.close();
		}
	}
}
