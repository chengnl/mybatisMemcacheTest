package com.vrv.im.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 下午2:10:13
 *@version 1.0
 *@Description:更新测试
 */
public class Main {

	public static void main(String[] args) throws IOException {
		   String resource = "mybatis-config.xml";
		   InputStream inputStream = Resources.getResourceAsStream(resource);
		   SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		   Update  update = new Update(sqlSessionFactory);
		   Select  select = new Select(sqlSessionFactory);
		   Thread  updateThread = new Thread(update);
		   Thread  selectThread = new Thread(select);
		   updateThread.start();
		   selectThread.start();

	}

}
