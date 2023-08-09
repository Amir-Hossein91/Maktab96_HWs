package question2.repository;

import question2.base.baseReopsitory.BaseRepository;
import question2.entity.Student;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student> {

    List<Student> loadAll();
}
