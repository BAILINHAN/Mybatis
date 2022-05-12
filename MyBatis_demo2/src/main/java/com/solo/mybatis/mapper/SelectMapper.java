package com.solo.mybatis.mapper;

import com.solo.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface SelectMapper {

    /**
     *根据Id查询用户信息
     */
    User getUserById(@Param("id") Integer id);

}
