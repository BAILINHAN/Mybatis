package com.solo.mybatis.test;

import com.solo.mybatis.bean.Emp;
import com.solo.mybatis.bean.EmpExample;
import com.solo.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MBGTest {

    @Test
    public void testMBG(){

        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
            List<Emp> emps = mapper.selectByExample(null);

            emps.stream().forEach(s -> System.out.println(s));

            //根据条件查询
            EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
            example.or().andDidIsNotNull();
            List<Emp> emps1 = mapper.selectByExample(example);

            emps1.stream().forEach(s -> System.out.println(s));

            mapper.updateByPrimaryKeySelective(new Emp(1, "admin", 22,  null, "456@qq.com", 3));



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
