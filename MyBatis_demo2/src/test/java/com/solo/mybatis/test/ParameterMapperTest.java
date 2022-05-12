package com.solo.mybatis.test;

import com.solo.mybatis.mapper.ParameterMapper;
import com.solo.mybatis.pojo.User;
import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {

    /**
     * MyBatis获取参数值的两种方式${}和#{}
     * ${}本质字符串拼接
     * #{}本质占位符赋值
     * MyBatis获取参数值的各种情况
     * 1、mapper接口方法的参数为单个的字面量类型
     * 可以通过#{}或${}以任意的名称获取参数值，但是需要注意${}的单引号问题，需要用'${}'
     * 2、mapper接口方法的参数为多个的时候，此时MyBatis会将这些参数放到一个map集合中，以两种
     * 方式进行存储
     * a> 以arg0,arg1....为键，以参数为值
     * b> 以param1,param2....为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，仍然需要注意使用'${}'
     * 3、若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
     * 4、若mapper接口方法的参数是一个实体类类型的参数，只需通过#{}或${}以属性值的方式
     * 访问属性即可，仍然需要注意使用'${}'
     * 5、使用@Param注解命名参数
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a>以@Param注解的的值为键，以参数为值
     * b>以param1, param2....为键，以参数为值
     */
    @Test
    public void testSelectAllUser(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> users = mapper.selectAllUser();

        users.stream().forEach(System.out::println);

        User userByUserName = mapper.selectUserByUserName("张三");
        System.out.println(userByUserName);

    }

    @Test
    public void testSelectUserByUserName(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User selectUserByUserName = mapper.selectUserByUserName("张三");
        System.out.println(selectUserByUserName);
    }

    @Test
    public void testCheckLogin(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int checkResult = mapper.checkLogin("张三", "123456");

        System.out.println(checkResult == 1 ? true : false);

    }

    @Test
    public void testCheckLoginByMap(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        Map userMap = new HashMap();
        userMap.put("userName", "张三");
        userMap.put("passWord", "123456");
        Integer integer = mapper.checkLoginByMap(userMap);

        System.out.println(integer == 1 ? true : false);

    }

    @Test
    public void testInsertUser(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int result = mapper.insertUser(User.builder().username("测试-1")
                .age(26)
                .email("ceshi_2@foxmail.com")
                .password("ceshi123456")
                .sex("男")
                .build());

        System.out.println(result == 1 ? true : false);

    }

    @Test
    public void testLoginByParam(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User checkResult = mapper.checkLoginByParam("张三", "123456");

        System.out.println(checkResult);

    }

}
