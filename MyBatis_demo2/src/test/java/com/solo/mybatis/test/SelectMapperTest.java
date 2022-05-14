package com.solo.mybatis.test;

import com.solo.mybatis.mapper.SelectMapper;
import com.solo.mybatis.pojo.User;
import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SelectMapperTest {

    /**
     * MyBatis的各种查询功能：
     * 1.若查询出的数据只有一条
     * a>可以通过实体对象接收
     * b>可以通过List集合接收
     * c>可以通过Map集合接收
     * 结果：{password=123456, sex=男, id=3, age=23, email=admin@qq.com, username=admin}
     *
     * 2.若查询出的数据有多条，一定不能通过实体类对象接收，此时会抛异常TooManyResultsException
     * a>可以通过List接收
     * b>可以通过Map集合接收
     * c>可以在Mapper接口的方法上加上@MapKey()注解，此时就可以将每条数据转换为Map集合作为值，
     * 以某个字段的值作为键，放到Map集合中
     *
     * MyBatis中设置了默认的类型别名
     * java.lang.Integer --> int, integer
     * int-->_int,_integer
     * Map-->map
     * String-->String
     *
     */

    @Test
    public void testGetUserById(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> user = selectMapper.getUserById(3);
        System.out.println(user);

    }

    @Test
    public void testGetAllUser(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> allUser = selectMapper.getAllUser();
        System.out.println(allUser);

    }

    @Test
    public void testGetCount(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper sessionMapper = sqlSession.getMapper(SelectMapper.class);
        Long count = sessionMapper.getCount();
        System.out.println(count);

    }

    @Test
    public void testGetUserForMap(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, User> userByIdToMap = mapper.getUserByIdToMap(3);

        System.out.println(userByIdToMap);
    }

    @Test
    public void testGetAllUsersForMap(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<Map<String, User>> allUserToMap = mapper.getAllUserToMap();
        System.out.println(allUserToMap);

    }

    @Test
    public void testGetAllUsersForMapWithMapKey(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, User> allUserToMap = mapper.getAllUserToMapWithMapKey();
        System.out.println(allUserToMap);

    }

}
