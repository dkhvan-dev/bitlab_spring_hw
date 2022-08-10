package BitlabBootcampCourse.bootcampspring.db;

import BitlabBootcampCourse.bootcampspring.model.Tasks;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Tasks> tasks = new ArrayList<>();
    private static Long id = 6L;

    static {
        tasks.add(new Tasks(1L, "Complete Task 7 from Spring Boot till the end of lesson", "Complete Task 7 from Spring Boot till the end of lesson", "2020-10-23", true));
        tasks.add(new Tasks(2L, "Clear home and buy foods", "Clear home and buy foods", "2020-10-25", true));
        tasks.add(new Tasks(3L, "Complete all home tasks at the weekend", "Complete all home tasks at the weekend", "2020-10-20", false));
        tasks.add(new Tasks(4L, "Develop simple project in Spring Boot for the final", "Develop simple project in Spring Boot for the final", "2020-12-12", false));
        tasks.add(new Tasks(5L, "Learn Italian Language", "Learn Italian Language", "2021-05-01", false));
    }

    public static ArrayList<Tasks> getAllTasks() {
        return tasks;
    }

    public static Tasks getTask(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public static void addTask(Tasks task) {
        task.setId(id);
        tasks.add(task);
        id++;
    }

    public static void updateTask(Tasks task) {
        for (Tasks t : tasks) {
            if (t.getId().equals(task.getId())) {
                t.setName(task.getName());
                t.setDescription(task.getDescription());
                t.setDeadlineDate(task.getDeadlineDate());
                t.setCompleted(task.isCompleted());
            }
        }
    }

    public static void deleteTask(Long id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.remove(i);
                break;
            }
        }
    }
}
