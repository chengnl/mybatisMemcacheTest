package com.vrv.im.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 下午2:10:13
 *@version 1.0
 *@Description:测试mybatis的memcached插件在并发访问下，缓存bug问题
 *
 */
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		   String resource = "mybatis-config.xml";
		   InputStream inputStream = Resources.getResourceAsStream(resource);
		   SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		   
		   select(sqlSessionFactory);
		   
		   TimeUnit.SECONDS.sleep(2);
		   
		   update(sqlSessionFactory);
		   
		   TimeUnit.SECONDS.sleep(2);
           
           select(sqlSessionFactory);
	}

	private static  void select(SqlSessionFactory sqlSessionFactory){
		   CountDownLatch  startSignal  = new CountDownLatch(1);
		   //同一mapper下的不同方法同时查询相同记录
		   Thread  selectReturnObjThread = new Thread(new SelectReturnObj(sqlSessionFactory,startSignal));
		   Thread  selectReturnStringThread = new Thread(new SelectReturnString(sqlSessionFactory,startSignal));
		   selectReturnObjThread.start();
		   selectReturnStringThread.start();
		   startSignal.countDown();
	}
	private static void update(SqlSessionFactory sqlSessionFactory){
		   Update  update = new Update(sqlSessionFactory);
		   Thread  updateThread = new Thread(update);
           updateThread.start();
	}
	
}
