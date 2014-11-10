package com.vrv.im.test;

import java.util.concurrent.CountDownLatch;
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
public class SelectReturnString implements Runnable{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SelectReturnString.class.getName());
   private SqlSessionFactory sqlSessionFactory;
   private CountDownLatch  startSignal;
   public SelectReturnString(SqlSessionFactory sqlSessionFactory,CountDownLatch  startSignal){
	   this.sqlSessionFactory=sqlSessionFactory;
	   this.startSignal=startSignal;
   }
	@Override
	public void run() {
		   try {
			   this.startSignal.await();
			   SqlSession session= sqlSessionFactory.openSession();
			   BlogMapper mapper = session.getMapper(BlogMapper.class);
			   Blog sblog = new Blog();
			   sblog.setId(3);
			   sblog.setTestid(2);
			   String blog = mapper.selectBlogName(sblog);
			   LOGGER.debug("SelectReturnString blog to :"+blog==null?"blog is null ":blog.toString());
			   session.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
