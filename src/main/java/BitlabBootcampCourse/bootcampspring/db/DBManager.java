package BitlabBootcampCourse.bootcampspring.db;

import java.util.ArrayList;

public class DBManager {

    private static ArrayList<Students> students = new ArrayList<>();
    private static Long id = 8L;

    static {
        students.add(new Students(1L, "Ilyas", "Zhuanyshev", 88, ' '));
        students.add(new Students(2L, "Serik", "Erikov", 91, ' '));
        students.add(new Students(3L, "Erik", "Serikov", 65, ' '));
        students.add(new Students(4L, "Nurzhan", "Bolatov", 48, ' '));
        students.add(new Students(5L, "Patrick", "Zuckerberg", 100, ' '));
        students.add(new Students(6L, "Sabina", "Assetova", 33, ' '));
        students.add(new Students(7L, "Madina", "Adletova", 77, ' '));
    }

    public static ArrayList<Students> getAllStudents() {
        for (Students student : students) {
            if (student != null) {
                if (student.getStudent_exam() >= 90) student.setStudent_mark('A'); else
                    if (student.getStudent_exam() >= 75 && student.getStudent_exam() <= 89) student.setStudent_mark('B'); else
                        if (student.getStudent_exam() >= 60 && student.getStudent_exam() <= 74) student.setStudent_mark('C'); else
                            if (student.getStudent_exam() >= 50 && student.getStudent_exam() <= 59) student.setStudent_mark('D'); else student.setStudent_mark('F');
            }
        }
        return students;
    }

    public static Students getStudent(Long id) {
        return students.stream().filter(student -> student.getStudent_id().equals(id)).findFirst().orElse(null);
    }

    public static Students addStudent(Students student) {
        student.setStudent_id(id);
        students.add(student);
        id++;
        return student;
    }
}
