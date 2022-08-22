package com.tk.simple.mybatis.test;

import com.tk.simple.mybatis.entity.CountryEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryEntityMapperTest {
	
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void init(){
		try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException ignore) {
        	ignore.printStackTrace();
        }
	}

	@Test
	public void testSelectAll(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<CountryEntity> countryEntityList = sqlSession.selectList("selectAll");
			printCountryList(countryEntityList);
		} finally {
			sqlSession.close();
		}
	}

	private void printCountryList(List<CountryEntity> countryEntityList){
		for(CountryEntity countryEntity : countryEntityList){
			System.out.printf("%-4d%4s%4s\n", countryEntity.getId(), countryEntity.getCountryName(), countryEntity.getCountryCode());
		}
	}
}
