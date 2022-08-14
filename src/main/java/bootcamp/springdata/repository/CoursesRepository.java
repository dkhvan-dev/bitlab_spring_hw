package bootcamp.springdata.repository;

import bootcamp.springdata.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    List<Courses> findAllByOrderByIdAsc();
}
