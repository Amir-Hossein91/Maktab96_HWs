package question1.repository;

import question1.base.baseReopsitory.BaseRepository;
import question1.entity.Student;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student> {

    List<Student> loadAll();
}
