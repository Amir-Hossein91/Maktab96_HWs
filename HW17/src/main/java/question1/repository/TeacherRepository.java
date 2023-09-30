package question1.repository;

import question1.base.baseReopsitory.BaseRepository;
import question1.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends BaseRepository<Teacher> {

    List<Teacher> loadAll();
}
