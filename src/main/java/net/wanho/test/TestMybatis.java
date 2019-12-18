package net.wanho.test;

import net.wanho.pojo.Student;
import net.wanho.service.StudentServiceI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatis {


    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        StudentServiceI studentServiceI = ctx.getBean(StudentServiceI.class);
        studentServiceI.addStu(new Student(null,"zhangsan",12,"男","北京",1));


    }


}
