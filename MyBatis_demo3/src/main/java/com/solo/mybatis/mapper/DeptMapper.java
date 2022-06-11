package com.solo.mybatis.mapper;

import com.solo.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

    /**
     * 通过did查询员工所对应的部门信息
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /**
     * 获取部门以及部门中所有员工的信息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);

    /**
     * 通过分步查询部门以及部门所有的员工信息
     * 分步查询第一步：查询部门信息
     */
    Dept getDeptAndEmpByStepOne(@Param("did") Integer did);

}
