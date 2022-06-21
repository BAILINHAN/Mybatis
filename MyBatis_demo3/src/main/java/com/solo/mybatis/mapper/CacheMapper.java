package com.solo.mybatis.mapper;

import com.solo.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {

    Emp getEmpByEid(@Param("eid") Integer id);

    void insertEmp(Emp emp);

}
