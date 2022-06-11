package com.solo.mybatis.test;

import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class DynamicSQLMapper {

    @Test
    public void testGetEmpByCondition(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        dynamicSQLMapper.testGetEmpByCondition();

    }

}
