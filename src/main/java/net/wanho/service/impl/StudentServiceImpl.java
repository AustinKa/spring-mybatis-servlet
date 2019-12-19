package net.wanho.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.wanho.constant.PageConstant;
import net.wanho.mapper.StudentMapper;
import net.wanho.pojo.Student;
import net.wanho.service.StudentServiceI;
import net.wanho.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceI {

    @Autowired
    private JedisPool jedisPool;


    @Autowired
    private StudentMapper studentMapper;



    @Override
    public void addStu(Student student) {

        studentMapper.addStu(student);
        jedisPool.getResource().del("stus");

    }

    @Override
    public List<Student> getAllStus() {

        List<Student> stus = studentMapper.getAllStus();
        jedisPool.getResource().del("stus");
        return stus;
    }

    @Override
    public PageInfo<Student> getAllStusPage(Integer pageNum, String gender, String address) {

        Jedis jedis = jedisPool.getResource();

//        先从缓存中取值
        String json = jedis.get("stus");
        if (!StringUtils.isEmpty(json)) {
            //            缓存中有
            PageInfo<Student> pageInfo = JsonUtils.jsonToPojo(json, PageInfo.class);
            System.out.println("从缓存中获取数据++++++++++++++++");
            return pageInfo;
        }

//            缓存中没有

        PageHelper.startPage(pageNum, PageConstant.PAGESIZE);
        List<Student> list = studentMapper.getAllStusPage(gender, address);
        PageInfo<Student> pageInfo = new PageInfo<>(list);

//        缓存中存储
        String s = JsonUtils.objectToJson(pageInfo);
        jedis.set("stus", s);
        jedis.expire("stus", 3600 * 2);
        System.out.println("从数据库中获取数据++++++++++++++++");


        return pageInfo;
    }

    @Override
    public void delStuById(Integer id) {

        studentMapper.delStuById(id);
        jedisPool.getResource().del("stus");

    }

    @Override
    public Student getStuById(Integer id) {

        Student stu = studentMapper.getStuById(id);
        return stu;
    }

    @Override
    public void updateStu(Student student) {
        studentMapper.updateStu(student);
        jedisPool.getResource().del("stus");


    }
}
