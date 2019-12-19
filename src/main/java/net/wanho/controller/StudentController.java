package net.wanho.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.wanho.constant.PageConstant;
import net.wanho.pojo.Classroom;
import net.wanho.pojo.Student;
import net.wanho.service.ClassroomServiceI;
import net.wanho.service.StudentServiceI;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentServiceI studentServiceI;

    @Autowired
    private ClassroomServiceI classroomServiceI;


    @RequestMapping("stus")
    public String stus(Map map) {

        List<Student> stus = studentServiceI.getAllStus();
        map.put("stus", stus);
        return "student";
    }

    @RequestMapping("getAllStus")
    public String getAllStus(@RequestParam(defaultValue = "1") Integer pageNum,String gender,String address,Map map) {
        System.out.println(address);
        System.out.println(gender);
        PageInfo<Student> pageInfo = studentServiceI.getAllStusPage(pageNum, gender, address);
        map.put("pageInfo", pageInfo);
        map.put("gender", gender);
        map.put("address", address);
        System.out.println(pageInfo);
        return "student";
    }


    @RequestMapping("addStu")
    public String addStu(Student student) {
        studentServiceI.addStu(student);

        return "redirect:/getAllStus";
    }


    @RequestMapping("toAddStu")
    public String toAddStu(Map map) {
        List<Classroom> allClasses = classroomServiceI.getAllClasses();
        map.put("classes", allClasses);
        return "addStu";
    }


    @RequestMapping("delStu")
    public String delStu(Integer id) {
        studentServiceI.delStuById(id);
        return "student";
    }

    @RequestMapping("toUpdateStu")
    public String toUpdateStu(Integer id,Map map) {


        Student stu = studentServiceI.getStuById(id);
        List<Classroom> allClasses = classroomServiceI.getAllClasses();


        map.put("stu", stu);
        map.put("classes", allClasses);

        return "updateStu";
    }

    @RequestMapping("updateStu")
    public String updateStu(Student student) {
        System.out.println(student);
        studentServiceI.updateStu(student);

        return "student";
    }


    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

        List<Student> stus = studentServiceI.getAllStus();


        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //3.创建标题行
        HSSFRow titlerRow = sheet.createRow(0);
        titlerRow.createCell(0).setCellValue("sname");
        titlerRow.createCell(1).setCellValue("age");
        titlerRow.createCell(2).setCellValue("gender");
        titlerRow.createCell(3).setCellValue("address");
//        titlerRow.createCell(4).setCellValue("classId");

        //4.遍历数据,创建数据行
        for (Student student : stus) {
            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(student.getSname());
            dataRow.createCell(1).setCellValue(student.getAge());
            dataRow.createCell(2).setCellValue(student.getGender());
            dataRow.createCell(3).setCellValue(student.getAddress());
//          dataRow.createCell(4).setCellValue(student.getClassId());
        }
        //5.创建文件名
        String fileName = "student.xls";
        //6.获取输出流对象

        try {
            ServletOutputStream   outputStream = response.getOutputStream();
            ServletContext servletContext = request.getServletContext();
            String mimeType = request.getServletContext().getMimeType(fileName);
            //8.获取浏览器信息,对文件名进行重新编码
            //9.设置信息头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //10.写出文件,关闭流
            hssfWorkbook.write(outputStream);
            hssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //7.获取mimeType

    }




}
