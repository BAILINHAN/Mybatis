package com.solo.mybatis.test;

import com.solo.mybatis.mapper.CacheMapper;
import com.solo.mybatis.pojo.Emp;
import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisCacheTest {

    @Test
    public void testCache(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp empByEid = mapper.getEmpByEid(3);
        //MyBatis一级缓存是以sqlSession为维度
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp empByEid1 = mapper1.getEmpByEid(3);

        System.out.println(empByEid);
        System.out.println(empByEid1);

    }

    @Test
    public void testCacheAfterInsert(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp empByEid = mapper.getEmpByEid(3);
        System.out.println(empByEid);
//        mapper.insertEmp(
//                new Emp(null, "abc", 23, "男", "123@qq.com")
//        );
        //手动清空一级缓存
        sqlSession.clearCache();

        Emp empByEid1 = mapper.getEmpByEid(3);
        System.out.println(empByEid1);

    }

    @Test
    public void testTwoCache() throws IOException {

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        SqlSession sqlSession1 = build.openSession(true);

        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp empByEid = mapper.getEmpByEid(1);
        System.out.println(empByEid);
//        sqlSession.commit();
        //只有两次查询中执行了增删改操作 二级缓存才会失效
//        mapper.insertEmp(new Emp(99, "avc", 15, "男", "nana@.com"));
        sqlSession.close();

        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp empByEid1 = mapper1.getEmpByEid(1);
        sqlSession1.close();
        System.out.println(empByEid1);

    }

}
