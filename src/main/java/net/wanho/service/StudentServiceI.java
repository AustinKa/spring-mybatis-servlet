package net.wanho.service;

import com.github.pagehelper.PageInfo;
import net.wanho.pojo.Student;

import java.util.List;

public interface StudentServiceI {

    void addStu(Student student);

    List<Student> getAllStus();

     PageInfo<Student> getAllStusPage(Integer pageNum, String gender, String address);

    void delStuById(Integer id);

    Student getStuById(Integer id);

    void updateStu(Student student);
}
