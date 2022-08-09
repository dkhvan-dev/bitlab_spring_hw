package BitlabBootcampCourse.bootcampspring.controllers;

import BitlabBootcampCourse.bootcampspring.db.DBManager;
import BitlabBootcampCourse.bootcampspring.db.Students;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        ArrayList<Students> students = DBManager.getAllStudents();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping(value = "/addStudent")
    public String addStudentPage(Model model) {
        return "addStudent";
    }

    @PostMapping(value = "/addStudent")
    public String addStudent(Students student,
                             Model model) {
        if (student != null) {
            DBManager.addStudent(student);
            model.addAttribute("student", student);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/details/{studentId}/{studentName}")
    public String details(@PathVariable(name = "studentId") Long student_id,
                          @PathVariable(name = "studentName") String student_name,
                          Model model) {
        Students student = DBManager.getStudent(student_id);
        if (student != null && !student.getStudent_name().equals(student_name)) {
            return "redirect:/details/" + student.getStudent_id() + "/" + student.getStudent_name() + ".html";
        }
        model.addAttribute("student", student);
        return "details";
    }
}
