package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<Student> selectStudent();
    int insertStudent(Student student);

    //foreach用法1
    List<Student> selectForeach(List<Integer> idList);

    //foreach用法2
    List<Student> selectForeachTwo(List<Student> stuList);

    //@Param声明指定的方法参数
    List<Student> selectMultiParam(@Param("myname") String name,@Param("myage") Integer age);

    Map<Object,Object> selectMapById(@Param("stuId") Integer id);

    //用resultMap定义映射关系
    List<Student> selectAllStudents();

    //模糊查询，在java代码指定 like的内容
    List<Student> selectLikeOne(String name);
    List<Student> selectLikeOne1(String name);
}
