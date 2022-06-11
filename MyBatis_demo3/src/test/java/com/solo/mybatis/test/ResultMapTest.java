import com.solo.mybatis.mapper.DeptMapper;
import com.solo.mybatis.mapper.EmpMapper;
import com.solo.mybatis.pojo.Dept;
import com.solo.mybatis.pojo.Emp;
import com.solo.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ResultMapTest {

    /**
     * 解决字段名和属性名不一致的情况
     * a> 为字段起别名，保持和属性名一致
     * b> 设置全局配置，将_自动映射为驼峰
     * <setting name="mapUnderscoreToCamelCase">true</setting>
     * c> 通过resultMap设置自定义的映射关系
     * <resultMap id="empResultMap" type="Emp">
     *     <id property="eid" column="eid" />
     *     <result property="empName" column="emp_name"/>
     *     <result property="age" column="age"/>
     *     <result property="sex" column="sex"/>
     *     <result property="empName" column="emp_name"/>
     * </resultMap>
     *
     * 处理多对一的映射关系：
     * a> 级联属性赋值
     * b> association
     * c> 分步查询
     *
     * 处理一对多的映射关系
     * a> collection
     * b> 分步查询
     *
     */
    @Test
    public void testGetAllEmp(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = empMapper.getAllEmp();

        allEmp.forEach(System.out::println);

    }

    @Test
    public void testGetEmpAndDept(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDept = empMapper.getEmpAndDept(4);

        System.out.println(empAndDept);

    }

    @Test
    public void testGetEmpAndDeptByStep(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDeptByStepOne = mapper.getEmpAndDeptByStepOne(3);
        System.out.println(empAndDeptByStepOne.getEmpName());
        System.out.println(empAndDeptByStepOne.getDept());

    }

    @Test
    public void testGetDeptAndEmp(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept deptAndEmp = deptMapper.getDeptAndEmp(1);
        System.out.println(deptAndEmp);

    }

    @Test
    public void testGetDeptAndEmpByStep(){

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept deptAndEmpByStepOne = deptMapper.getDeptAndEmpByStepOne(1);
        System.out.println(deptAndEmpByStepOne.getDeptName());
        System.out.println(deptAndEmpByStepOne);

    }

}
