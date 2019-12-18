package net.wanho.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//get set方法
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String sname;
    private Integer age;
    private String gender;
    private String address;
    private Integer classId;



}
