package net.wanho.task;

import net.wanho.pojo.Student;
import net.wanho.service.StudentServiceI;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019/12/18.
 */
@Component
public class MyTak {
    @Autowired
    private StudentServiceI studentServiceI;
    @Autowired
    private JedisPool jedisPool;


    @Scheduled(cron = "0/5 * * * * ?")
    public void task1(){
        Jedis jedis = jedisPool.getResource();
        List<Student> allStus = studentServiceI.getAllStus();
        if(CollectionUtils.isEmpty(allStus)){
            throw new RuntimeException("没有学士信息");
        }
       for(Student student:allStus){
          jedis.zadd("sortedStuByAge",(double) student.getAge(),student.getSname());
       }
        Set<String> stuByAge = jedis.zrevrange("sortedStuByAge", 0, 9);
        System.out.println(stuByAge);
    }

}
