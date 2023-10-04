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
import java.util.*;

public class Main {
    private static final StudentCourseRatingServiceImpl studentCourseService = ApplicationContext.studentCourseService;
    private static final StudentServiceImpl studentService = ApplicationContext.studentService;
    private static final CourseServiceImpl courseService = ApplicationContext.courseService;
    private static List<String> headings;

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        saveLines();
    }

    public static void saveLines() throws ParseException {
        Scanner scanner = ApplicationContext.scanner;
        headings = new ArrayList<>(List.of(scanner.nextLine().split(",")));
        Map<String,String> mapHeadings = new HashMap<>();
        int columnsCount = headings.size();
        while (scanner.hasNextLine()){
            List<String> line = new ArrayList<>(List.of(scanner.nextLine().split(",")));

            if (line.size()> columnsCount){
                for (int i =0; i< headings.size(); i++){
                    if(headings.get(i).equals("Comment")){
                        for(int j = 0 ; j<line.size() - columnsCount; j++){
                            line.set(i,line.get(i) +"," + line.get(i+1));
                            line.remove(i+1);
                        }
                    }
                }

            }
            for(int i =0; i<line.size(); i++){
                mapHeadings.put(headings.get(i),line.get(i).replace('\"', ' '));
            }
            Student student = new Student();
            Course course = new Course();
            StudentCourseRating rating = new StudentCourseRating();
            for(Map.Entry<String, String> entry : mapHeadings.entrySet()){
                switch (entry.getKey()){
                    case "Course Name" -> course.setName(entry.getValue());
                    case "Student Name" -> student.setName(entry.getValue());
                    case "Timestamp" -> rating.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(entry.getValue()));
                    case "Rating" -> rating.setRate(Float.parseFloat(entry.getValue()));
                    case "Comment" -> rating.setComment(entry.getValue());
                }
            }
            courseService.save(course);
            studentService.save(student);
            studentCourseService.save(rating);
        }
    }

}
