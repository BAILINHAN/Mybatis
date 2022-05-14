package com.solo.mybatis.mapper;

import com.solo.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    /**
     *根据Id查询用户信息
     */
    List<User> getUserById(@Param("id") Integer id);

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();

    /**
     * 查询用户信息的总记录数
     */
    Long getCount();

    /**
     * 根据id查询用户信息为一个map集合
     */
    Map<String, User> getUserByIdToMap(@Param("id") Integer id);

    /**
     * 查询所有用户的Map集合
     */
    List<Map<String, User>> getAllUserToMap();

    /**
     * 使用id作为Map的键
     * @return
     */
    @MapKey("id")
    Map<String, User> getAllUserToMapWithMapKey();

}
