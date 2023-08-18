package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.SalaryReport;
import entity.Teacher;
import exceptions.NotSavedException;
import repository.TeacherRepositoryImpl;
import service.SalaryReportService;
import service.TeacherService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher, TeacherRepositoryImpl> implements TeacherService {
    public TeacherServiceImpl(TeacherRepositoryImpl repository) {
        super(repository);
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
        SalaryReportService salaryReportService = ApplicationContext.salaryReportService;
        CourseServiceImpl courseService = ApplicationContext.courseService;
        try{
            transaction.begin();
            salaryReport = salaryReportService.saveOrUpdate(salaryReport);
            if(salaryReport == null)
                throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
            for(Course c : presentedCourses){
                c = courseService.saveOrUpdate(c);
                if (c == null)
                    throw new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION);
                teacher.getPresentedCourses().add(c);
            }
            teacher = repository.saveOrUpdate(teacher).orElseThrow(() -> new NotSavedException(Constants.TEACHER_SAVE_EXCEPTION));
            transaction.commit();
            return teacher;
        }catch(Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
