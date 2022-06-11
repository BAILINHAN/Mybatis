package com.solo.mybatis.test;

import com.solo.mybatis.mapper.DynamicSQLMapper;
import com.solo.mybatis.pojo.Emp;
import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class DynamicSQLMapperTest {

    /**
     * 动态SQL:
     * 1、if: 根据标签中test属性对应的表达式决定标签中的内容是否需要拼接到SQL中
     * 2、where: 当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉
     * 当where标签中没有内容时，此时where标签没有任何效果
     * 注意：where标签不能将其中内容后面多余的and或or去掉
     * 3.trim:
     * 若标签中有内容时：
     * prefix|suffix: 将trim标签中内容前面或后面添加指定内容
     * suffixOverrides|prefixOverrides: 将trim标签中内容前面或后面去掉指定内容
     * 若标签中没有内容时，trim标签也没有任何效果
     * 4.choose、when、otherwise, 相当于if....else if....else
     */
    @Test
    public void testGetEmpByCondition(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empByCondition = dynamicSQLMapper.getEmpByCondition(new Emp(null, null, null, "男", "123@qq.com"));

        System.out.println(empByCondition);

    }

}
