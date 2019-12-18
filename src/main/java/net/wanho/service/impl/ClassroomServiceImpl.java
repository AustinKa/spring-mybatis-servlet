package net.wanho.service.impl;

import net.wanho.mapper.ClassroomMapper;
import net.wanho.pojo.Classroom;
import net.wanho.service.ClassroomServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomServiceI {

    @Autowired
    private ClassroomMapper classroomMapper;


    @Override
    public List<Classroom> getAllClasses() {
        return classroomMapper.getAllClasses();
    }
}
