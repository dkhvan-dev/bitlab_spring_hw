package BitlabBootcampCourse.bootcampspring.controllers;

import BitlabBootcampCourse.bootcampspring.db.DBManager;
import BitlabBootcampCourse.bootcampspring.model.Tasks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        ArrayList<Tasks> tasks = DBManager.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping(value = "/addTask")
    public String addTask(@RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "deadlineDate") String deadlineDate,
            @RequestParam(name = "isCompleted") boolean isCompleted) {
        Tasks task = new Tasks();
        task.setName(name);
        task.setDescription(description);
        task.setDeadlineDate(deadlineDate);
        task.setCompleted(isCompleted);
        DBManager.addTask(task);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{taskId}")
    public String details(@PathVariable(name = "taskId") Long id,
                          Model model) {
        Tasks task = DBManager.getTask(id);
        model.addAttribute("task", task);
        return "details";
    }

    @PostMapping(value = "/details")
    public String updateTask(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "deadlineDate") String deadlineDate,
                             @RequestParam(name = "isCompleted") boolean isCompleted) {
        Tasks task = DBManager.getTask(id);
        task.setName(name);
        task.setDescription(description);
        task.setDeadlineDate(deadlineDate);
        task.setCompleted(isCompleted);
        DBManager.updateTask(task);
        return "redirect:/details/" + id;
    }

    @PostMapping(value = "/deleteTask")
    public String deleteTask(@RequestParam(name = "id") Long id) {
        DBManager.deleteTask(id);
        return "redirect:/";
    }
}
