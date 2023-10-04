import entity.Course;
import entity.Student;
import entity.StudentCourseRating;
import service.impl.CourseServiceImpl;
import service.impl.StudentCourseRatingServiceImpl;
import service.impl.StudentServiceImpl;
import utility.ApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentCourseRatingServiceImpl studentCourseService = ApplicationContext.studentCourseService;
    private static final StudentServiceImpl studentService = ApplicationContext.studentService;
    private static final CourseServiceImpl courseService = ApplicationContext.courseService;

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        saveLines();
    }

    public static void saveLines() throws ParseException {
        Scanner scanner = ApplicationContext.scanner;

        while (scanner.hasNextLine()){
            List<String> line = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            if (line.size()>5){
                for(int i = 5 ; i<line.size(); i++){
                    line.set(4,line.get(4) +"," + line.get(5));
                    line.remove(5);
                }
            }
            if(line.get(0).startsWith("\"")){
                courseService.save(new Course(line.get(0).replace('\"',' ')));
                studentService.save(new Student(line.get(1)));
                studentCourseService.save(new StudentCourseRating(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(line.get(2)),Float.parseFloat(line.get(3)), line.get(4).replace('\"',' ')));
            }
        }
    }

}
