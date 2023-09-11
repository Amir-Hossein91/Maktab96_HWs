package service.impl;

import entity.Student;
import exceptions.InvalidDateException;
import exceptions.NotProperTimeException;
import org.junit.jupiter.api.Test;
import utility.ApplicationContext;
import utility.Constants;

import javax.persistence.NoResultException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    StudentServiceImpl studentService = ApplicationContext.studentService;

    @Test
    void passwordContainsAtLeastOneUpperCaseLetter(){
        Student student = Student.builder().nationalCode("1234567890").build();
        studentService.setUsernameAndPassword(student);
        char[] passwordChars = student.getPassword().toCharArray();
        boolean doesExist = false;
        for(char c : passwordChars){
            if(Arrays.stream(Constants.UPPER_CASE_CHARACTERS).toList().contains(c)){
                doesExist = true;
                break;
            }
        }
        assertTrue(doesExist);
    }

    @Test
    void passwordContainsAtLeastOneLowerCaseLetter(){
        Student student = Student.builder().nationalCode("1234567890").build();
        studentService.setUsernameAndPassword(student);
        char[] passwordChars = student.getPassword().toCharArray();
        boolean doesExist = false;
        for(char c : passwordChars){
            if(Arrays.stream(Constants.LOWER_CASE_CHARACTERS).toList().contains(c)){
                doesExist = true;
                break;
            }
        }
        assertTrue(doesExist);
    }

    @Test
    void passwordContainsAtLeastOneDigitCharacter(){
        Student student = Student.builder().nationalCode("1234567890").build();
        studentService.setUsernameAndPassword(student);
        char[] passwordChars = student.getPassword().toCharArray();
        boolean doesExist = false;
        for(char c : passwordChars){
            if(Arrays.stream(Constants.DIGIT_CHARACTERS).toList().contains(c)){
                doesExist = true;
                break;
            }
        }
        assertTrue(doesExist);
    }

    @Test
    void passwordContainsAtLeastOneSpecialCharacter(){
        Student student = Student.builder().nationalCode("1234567890").build();
        studentService.setUsernameAndPassword(student);
        char[] passwordChars = student.getPassword().toCharArray();
        boolean doesExist = false;
        for(char c : passwordChars){
            if(Arrays.stream(Constants.SPECIAL_CHARACTERS).toList().contains(c)){
                doesExist = true;
                break;
            }
        }
        assertTrue(doesExist);
    }

    @Test
    void userNameEqualsToStudentNationalCode(){
        Student student = Student.builder().nationalCode("1234567890").build();
        studentService.setUsernameAndPassword(student);
        assertEquals("1234567890",student.getUsername());
    }

    @Test
    void unRegisteredStudentHasNoUserNameOrPassword(){
        Student student = Student.builder().nationalCode("1234567890").build();
        studentService.setUsernameAndPassword(student);
        assertNull(studentService.checkUsernameAndPassword(student.getUsername(),student.getPassword()));

    }

    @Test
    void unRegisteredStudentCanNotBeFoundByNationalCode(){
        Student student = Student.builder().nationalCode("1234567890").build();
        assertThrows(NoResultException.class,() -> studentService.findByNationalCode(student.getNationalCode()));
    }

    @Test
    void registrationIsOpenIfCurrentDateSetProperly() throws InvalidDateException {
        // current date = 1398/08/03 ---> proper
        assertTrue(studentService.isRegistrationOpen());
    }

    @Test
    void registrationIsClosedIfCurrentDateNotSetProperly() throws InvalidDateException {
        // current date = 1398/07/03 ---> not proper
        assertThrows(InvalidDateException.class,() -> studentService.isRegistrationOpen());
    }

    @Test
    void canNotRepayDebtsIfNotGraduated() throws NotProperTimeException {
        // current year = 1395
        Student student = Student.builder()
                .graduateYear(1396).build();
        assertThrows(NotProperTimeException.class,()->studentService.canRepay(student));
        assertFalse(studentService.isGraduated(student));
    }

}