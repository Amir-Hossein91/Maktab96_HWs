package qustion2.repository;

import qustion2.base.baseReopsitory.BaseRepository;
import qustion2.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends BaseRepository<Teacher> {

    List<Teacher> loadAll();
}
