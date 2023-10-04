package utility;

import repository.impl.CourseRepositoryImpl;
import repository.impl.StudentCourseRatingRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import service.impl.CourseServiceImpl;
import service.impl.StudentCourseRatingServiceImpl;
import service.impl.StudentServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ApplicationContext {
    private static final Path path;
    public static final File file;
    public static Scanner scanner;
    private static final CourseRepositoryImpl courseRepository;
    public static final CourseServiceImpl courseService;
    private static final StudentRepositoryImpl studentRepository;
    public static final StudentServiceImpl studentService;
    private static final StudentCourseRatingRepositoryImpl studentCourseRepository;
    public static final StudentCourseRatingServiceImpl studentCourseService;

    static {
        path = Paths.get("HW17-Q2/src/main/files/data.txt");
        file = new File(path.toUri());
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        courseRepository = new CourseRepositoryImpl();
        courseService = new CourseServiceImpl(courseRepository);
        studentRepository = new StudentRepositoryImpl();
        studentService = new StudentServiceImpl(studentRepository);
        studentCourseRepository = new StudentCourseRatingRepositoryImpl();
        studentCourseService = new StudentCourseRatingServiceImpl(studentCourseRepository);
    }

}
