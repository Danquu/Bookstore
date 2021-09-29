package fi.haagahelia.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.demo.domain.Student;
import fi.haagahelia.demo.domain.StudentDAOImpl;

@Controller
public class StudentController {
    @Autowired
    private StudentDAOImpl studentDAO;
    
    @RequestMapping(value="/studentlist")
    public String studentList(Model model) {	
        // Fetch all students
        List<Student> students = studentDAO.findAll();
        // Add studentlist to model and return view name
        model.addAttribute("students", students);
        return "studentlist";
    }	
    
    // uuden opiskelijan lomake
    @RequestMapping(value = "/add")
    public String addStudent(Model model){
    	model.addAttribute("student", new Student());
        return "addstudent";
    }     
    
    // opiskelijan lomaketietojen tallennus
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Student student){
        studentDAO.save(student);
        return "redirect:studentlist";
    }    
	
}
