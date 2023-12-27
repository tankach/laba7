/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.webstudent;

/*import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/
import sumdu.edu.ua.webstudent.res.Student;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sumdu.edu.ua.webstudent.res.Student;
import sumdu.edu.ua.webstudent.res.Content;


/**
 *
 * @author Oksana
 */
@Controller
public class ContentController {
        @RequestMapping(value = "UserContent", method=RequestMethod.GET)
        public ModelAndView formContent(@RequestParam("id2") String id2,Model m,HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{ 
            ModelAndView modelNview = new ModelAndView();
            modelNview.setViewName("score");
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:/spring.xml");
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
            conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");

            PreparedStatement ps= (PreparedStatement) conn.prepareStatement("SELECT * FROM `students` where id=?;");
                ps.setString(1, id2);
            ResultSet rs=ps.executeQuery();
            Student student =null;
            {
            if(rs.next()){
            student = new Student(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7));
            }
            modelNview.addObject("user",student);
            }

            ps= (PreparedStatement) conn.prepareStatement("SELECT * FROM `dis` where stud_id=?;");
                ps.setString(1, id2);
            rs=ps.executeQuery();
            List<Content> cont = new LinkedList<Content>();
            while(rs.next()){
                Content ctn = (Content)factory.getBean("Content");
                ctn.setId(Integer.parseInt(rs.getString(1)));
                ctn.setStudentId(Integer.parseInt(rs.getString(2)));
                ctn.setTitle(rs.getString(3));
                ctn.setMakr(Integer.parseInt(rs.getString(5)));
                ctn.setMakr_let(rs.getString(4));

                cont.add(ctn);
                modelNview.addObject("scores",cont);

            }
            rs.close();
            ps.close();
            return modelNview;
        }
    
}
