package repository;

import basics.repository.BaseRepository;
import entity.BankAccount;
import entity.Student;
import entity.enums.AcademicGrade;
import exceptions.NotFoundException;

public interface StudentRepository extends BaseRepository<Student> {
    Student checkUsernameAndPassword(String username, String password) throws NotFoundException;
    Student findByNationalCode (String nationalCode);
    BankAccount findBankAccount (Student student);
}
