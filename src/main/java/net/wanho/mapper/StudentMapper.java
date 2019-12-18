package net.wanho.mapper;

import net.wanho.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    void addStu(Student student);

    List<Student> getAllStus();

    List<Student> getAllStusPage(@Param("gender") String gender, @Param("address") String address);

    void delStuById(Integer id);

    Student getStuById(Integer id);

    void updateStu(Student student);

}
