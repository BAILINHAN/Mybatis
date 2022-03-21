package com.solo.mybatis.mapper;

import com.solo.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {

    /**
     * 查询所有的员工信息
     * @return
     */
    List<User> selectAllUser();

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User selectUserByUserName(String userName);

    /**
     * 验证用户登录
     * @param userName
     * @param passWord
     * @return
     */
    Integer checkLogin(@Param("userName") String userName, @Param("passWord") String passWord);

    /**
     * 根据Map集合传参
     * @param userMap
     * @return
     */
    Integer checkLoginByMap(Map<String, Object> userMap);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

}
