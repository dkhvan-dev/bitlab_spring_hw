package BitlabBootcampCourse.bootcampspring.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    private Long student_id;
    private String student_name;
    private String student_surname;
    private int student_exam;
    private char student_mark;
}
