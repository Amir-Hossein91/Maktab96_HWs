package repository;

import basics.repository.BaseRepository;
import entity.Student;
import exceptions.NotFoundException;

public interface StudentRepository extends BaseRepository<Student> {
    Student checkUsernameAndPassword(String username, String password) throws NotFoundException;
}
