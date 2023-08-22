package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Employee;
import entity.SalaryReport;
import entity.Teacher;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.TeacherRepositoryImpl;
import service.SalaryReportService;
import service.TeacherService;
import utility.ApplicationContext;
import utility.Constants;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher, TeacherRepositoryImpl> implements TeacherService {

    private final SalaryReportServiceImpl salaryReportService;
    private final CourseServiceImpl courseService;

    public TeacherServiceImpl(TeacherRepositoryImpl repository) {
        super(repository);
        salaryReportService = ApplicationContext.salaryReportService;
        courseService = ApplicationContext.courseService;
    }

    @Override
    public void delete(Teacher teacher) {
        try{
            transaction.begin();
            repository.delete(teacher);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Teacher saveOrUpdate(Teacher teacher, List<Course> presentedCourses, SalaryReport salaryReport) {
        try{
            if(!isValid(teacher)){
                throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
            }
            if(!transaction.isActive()){
                transaction.begin();
                salaryReport = salaryReportService.saveOrUpdate(salaryReport);
                if(salaryReport == null)
                    throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
                for(Course c : presentedCourses){
                    c = courseService.saveOrUpdate(c);
                    if (c == null)
                        throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
                }
                teacher = repository.saveOrUpdate(teacher).orElseThrow(() -> new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION));
                transaction.commit();
            }
            else {
                salaryReport = salaryReportService.saveOrUpdate(salaryReport);
                if(salaryReport == null)
                    throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
                for(Course c : presentedCourses){
                    c = courseService.saveOrUpdate(c);
                    if (c == null)
                        throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
                }
                teacher = repository.saveOrUpdate(teacher).orElseThrow(() -> new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION));

            }

            return teacher;

        }catch(Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getSalaryReport(Teacher teacher){
        String name = teacher.getFirstname() + " " + teacher.getLastname();
        String creditsCount = String.valueOf(teacher.getPresentedCourses().stream()
                .map(Course::getCredits)
                .reduce(0,Integer::sum));
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new GregorianCalendar().getTime());
        try {
            return "Report date:\n\t" + date + "\nTeacher name:\n\t" + name
                    + "\nTotal number of course credits:\n\t" + creditsCount
                    + "\nTotal salary:\n\t" + salaryReportService.getSalaryReport(teacher).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
