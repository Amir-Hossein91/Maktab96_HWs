package question2.repository;

import question2.base.baseReopsitory.BaseRepository;
import question2.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends BaseRepository<Teacher> {

    List<Teacher> loadAll();
}
