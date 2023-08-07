package qustion2.repository;

import qustion2.base.baseReopsitory.BaseRepository;
import qustion2.entity.Student;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student> {

    List<Student> loadAll();
}
