package com.solo.mybatis.test;

import com.solo.mybatis.mapper.UserMapper;
import com.solo.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    /**
     * SqlSession默认不自动提交事务，若需要自动提交事务，
     * 可以使用sqlSessionFactory.openSession(true);
     */

    @Test
    public void testMyBatis() throws IOException {

        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        /**
         * SqlSessionFactory:是'生产'SqlSession的'工厂'
         */
        //获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);

        /**
         * sqlSession:代表Java程序中和数据库之间的会话。(HttpSession是Java程序和浏览器之间的会话)
         */
        //获取SqlSession true为设置成自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取Mapper接口对象,获取实例化对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //测试功能
        int result = mapper.insertUser();
        System.out.println("result:" + result);
        //提交事务-实际执行
//        sqlSession.commit();

    }

    @Test
    public void testRUD() throws IOException {

        //读取mybatis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取sqlSessionFactory
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        //开启自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取接口实例对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行更新方法
        mapper.updateUser();
        //执行删除方法
        mapper.deleteUser();
        //执行查询方法
        User userById = mapper.getUserById();
        System.out.println(userById);
        //查询所有用户
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(user -> System.out.println(user));

    }

}
