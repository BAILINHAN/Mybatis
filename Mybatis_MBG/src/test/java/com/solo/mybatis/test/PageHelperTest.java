package com.solo.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solo.mybatis.bean.Emp;
import com.solo.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {

    /**
     * 分页
     *
     * limit index, pageSize;
     * index: 当前页起始索引
     * pageSize: 每页显示条数
     * pageNum: 当前页页码
     * index = (pageNum-1) * pageSize
     *
     * 使用MyBatis分页插件实现分页功能
     * 1、需要在查询功能之前开启分页
     * PageHelper.startPage(int pageNum, int pageSize)
     * 2、在查询功能之后获取分页相关信息
     * PageInfo<Emp> page = new PageInfo<>(list, 5);
     * list: 表示需要分页的数据
     * 5: 表示当前导航分页的数量
     *
     * @throws IOException
     */
    @Test
    public void testPageHelper() throws IOException {

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        Page<Object> page = PageHelper.startPage(2, 5);

        List<Emp> emps = mapper.selectByExample(null);

        //先查询 后分页
        PageInfo<Emp> empPageInfo = new PageInfo<>(emps, 5);
        //输出分页后结果
        System.out.println(empPageInfo);

        emps.stream().forEach(s -> System.out.println(s));
        //输出page对象
        System.out.println(page);

    }

}
