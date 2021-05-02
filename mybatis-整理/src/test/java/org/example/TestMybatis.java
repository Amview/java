package org.example;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.utils.MyBatisUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    /*
     * 使用mybatis的动态代理是指，使用SqlSession.getMapper(dao接口)
     * getMapper能获取dao接口对于的实现类对象
     * */

    @Test
    public void testSelectStudents(){
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        StudentDao dao= sqlSession.getMapper(StudentDao.class);
        List<Student> students =dao.selectStudent();
        for(Student stu:students){
            System.out.println("学生"+stu);
        }
    }
    @Test
    public void testInsertStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setId(1005);
        student.setName("吕布");
        student.setEmail("lvbu@qq.com");
        student.setAge(21);
        int nums=dao.insertStudent(student);
        sqlSession.commit();
        System.out.println("添加对象的数量："+nums);

    }

    @Test
    public void testForeachTwo() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> stulist = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1002);
        stulist.add(s1);

        s1 = new Student();
        s1.setId(1005);
        stulist.add(s1);

        List<Student> students = dao.selectForeachTwo(stulist);
        for (Student stu : students) {
            System.out.println("foreach：" + stu);
        }
    }
    @Test
        public void testForeach() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);
        List<Student> students = dao.selectForeach(list);
        for (Student stu : students) {
            System.out.println("foreach：" + stu);
        }

    }

    @Test
    public void selectMultiParam(){
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        StudentDao dao= sqlSession.getMapper(StudentDao.class);
        List<Student> student = dao.selectMultiParam("李白",20);
        for(Student stu : student){
            System.out.println("student= "+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMapById(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Map<Object, Object> map = dao.selectMapById(1004);
        System.out.println("map==="+map);
        sqlSession.close();
    }

    @Test
    public void testSelectAllStudents(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<Student> student = dao.selectAllStudents();
        for(Student stu : student){
            System.out.println("student==="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectLikeOne(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        String name="%李%";
        List<Student> student=dao.selectLikeOne(name);
        for(Student stu : student){
            System.out.println("student==="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectLikeOne1(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        String name="李";
        List<Student> student=dao.selectLikeOne1(name);
        for(Student stu : student){
            System.out.println("student==="+stu);
        }
        sqlSession.close();
    }
}
