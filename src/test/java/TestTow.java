import com.mchange.io.FileUtils;
import net.wanho.pojo.Student;
import net.wanho.service.StudentServiceI;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/12/18.
 */
public class TestTow {
    @Autowired
    private StudentServiceI studentServiceI;
    @Test
    public void test()  {
        ArrayList<Student> list=new ArrayList<>();

        try {
            InputStream inputStream=new FileInputStream("D:/学士数据.xls");
            HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
            HSSFSheet sheet=workbook.getSheetAt(0);
            for(Row row:sheet){
                if(row.getRowNum()==0){
                    continue;
                }
                String sname=row.getCell(0).getStringCellValue();
                Cell ageCell=row.getCell(1);
                ageCell.setCellType(CellType.STRING);
                String age=ageCell.getStringCellValue();
                String gender=row.getCell(2).getStringCellValue();
                String address=row.getCell(3).getStringCellValue();
                Cell classCell=row.getCell(4);
                classCell.setCellType(CellType.STRING);
                String classId=classCell.getStringCellValue();
                Student student=new Student(null,sname,Integer.parseInt(age),gender,address,Integer.parseInt(classId));
                list.add(student);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }

    @Test
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
        titlerRow.createCell(3).setCellValue("classId");

        //4.遍历数据,创建数据行
        for (Student student : stus) {
            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(student.getSname());
            dataRow.createCell(1).setCellValue(student.getAge());
            dataRow.createCell(2).setCellValue(student.getGender());
            dataRow.createCell(3).setCellValue(student.getAddress());
            dataRow.createCell(4).setCellValue(student.getClassId());
        }
        //5.创建文件名
        String fileName = "用户统计.xls";
        //6.获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();

        //7.获取mimeType
        ServletContext servletContext = request.getServletContext();
        String mimeType = servletContext.getMimeType(fileName);
        //8.获取浏览器信息,对文件名进行重新编码
        fileName = FileUtils.filenameEncoding(fileName, request);

        //9.设置信息头
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        //10.写出文件,关闭流
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();
    }
}
