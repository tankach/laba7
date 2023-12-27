/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.webstudent;

import com.mysql.jdbc.Statement;
/*import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sumdu.edu.ua.webstudent.res.Student;

/**
 *
 * @author Oksana
 */
@Controller
public class StudentController {
    List<Student> students;
    ApplicationContext factory;
    Student student;
        @RequestMapping(value = "/")
	public String home() {
		return "student";
	}
        
        @RequestMapping("StudentAdd")
        public String addStudent(HttpServletRequest request,HttpServletResponse response,Model m) throws IOException, SQLException{
            ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
            List<Student> students;
            PrintWriter pw=null;
            try{
                pw=response.getWriter();
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch(ClassNotFoundException ex){
                ex.printStackTrace(pw);
                pw.print(ex.getMessage());
            }

            Connection conn=null;
            String sqlQuery = "INSERT INTO `students`(`name`, `surname`, `email`, `group_name`, `faculty`) VALUES (?, ?, ?, ?, ?)";
            conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");

            if(request.getParameter("name")!=null && request.getParameter("surname")!=null){
                PreparedStatement ps = conn.prepareStatement(sqlQuery);
                ps.setString(1,request.getParameter("name"));
                ps.setString(2,request.getParameter("surname"));
                ps.setString(3,request.getParameter("email"));
                ps.setString(4,request.getParameter("group"));
                ps.setString(5,request.getParameter("faculty"));
                ps.executeUpdate();
            }


            Statement s= (Statement) conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT * FROM `students`");
            students =new LinkedList<Student>();
            while(rs.next()){
                Student student = (Student)factory.getBean("Student");
                student.setId(Integer.parseInt(rs.getString(1))); 
                student.setName(rs.getString(2));
                student.setSurname(rs.getString(3));
                student.setEmail(rs.getString(4));
                student.setFaculty(rs.getString(5));
                student.setGroup(rs.getString(6));
                students.add(student);
            }
            for(Student st:students){
            System.out.println(st.getName());
            }
            m.addAttribute("students", students);

            return "student";}
        
        }
    
