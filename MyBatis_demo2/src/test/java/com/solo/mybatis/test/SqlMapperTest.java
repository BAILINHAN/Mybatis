package com.solo.mybatis.test;

import com.solo.mybatis.mapper.SelectMapper;
import com.solo.mybatis.mapper.SqlMapper;
import com.solo.mybatis.pojo.User;
import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class SqlMapperTest {

    @Test
    public void testGetUserByLike(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper sqlMapper = sqlSession.getMapper(SqlMapper.class);
        List<User> dm = sqlMapper.getUserByLike("dm");

        System.out.println(dm);

    }

    @Test
    public void testDeleteMore(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper sqlMapper = sqlSession.getMapper(SqlMapper.class);
        int dm = sqlMapper.deleteMore("1,2,3");

        System.out.println(dm);

    }

    @Test
    public void testGetUserByTableName(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper mapper = sqlSession.getMapper(SqlMapper.class);
        List<User> t_user = mapper.getUserByTableName("t_user");
        System.out.println(t_user);

    }

    @Test
    public void testInsertUser(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqlMapper mapper = sqlSession.getMapper(SqlMapper.class);
        User user = new User(null, "王五", "123", 23, "男", "wangwu@qq.com");
        mapper.insertUser(user);
        System.out.println(user);
    }

    @Test
    public void testJDBC() throws Exception {
        Class.forName("");
        Connection connection = DriverManager.getConnection("", "", "");
        PreparedStatement preparedStatement = connection.prepareStatement("insert", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        //获取自动递增的主键
        preparedStatement.getGeneratedKeys();

    }

}
