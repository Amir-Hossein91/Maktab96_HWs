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
    private static final Map<String,String> mapHeadings = new HashMap<>();

    public static void main(String[] args) throws ParseException {
        saveLines();
    }

    public static void saveLines() throws ParseException {
        Scanner scanner = ApplicationContext.scanner;
        headings = new ArrayList<>(List.of(scanner.nextLine().split(",")));


        while (scanner.hasNextLine()){
            List<String> line = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            Student student = new Student();
            Course course = new Course();
            StudentCourseRating rating = new StudentCourseRating();

            alterData(headings,line);

            mapDataWithHeadings(line);

            setFieldsOf(student,course,rating);

            courseService.save(course);
            studentService.save(student);
            studentCourseService.save(rating);
        }
    }

    public static void alterData (List<String> headings , List<String> line){
        if (line.size()> headings.size()){
            for (int i =0; i< headings.size(); i++){
                if(headings.get(i).equals("Comment")){
                    for(int j = 0 ; j<line.size() - headings.size(); j++){
                        line.set(i,line.get(i) +"," + line.get(i+1));
                        line.remove(i+1);
                    }
                }
            }
        }
    }

    public static void mapDataWithHeadings (List<String> line){
        for(int i =0; i<line.size(); i++){
            mapHeadings.put(headings.get(i),line.get(i).replace('\"', ' '));
        }
    }

    public static void setFieldsOf(Student student, Course course, StudentCourseRating rating) throws ParseException {
        for(Map.Entry<String, String> entry : mapHeadings.entrySet()){
            switch (entry.getKey()){
                case "Course Name" -> course.setName(entry.getValue());
                case "Student Name" -> student.setName(entry.getValue());
                case "Timestamp" -> rating.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(entry.getValue()));
                case "Rating" -> rating.setRate(Float.parseFloat(entry.getValue()));
                case "Comment" -> rating.setComment(entry.getValue());
            }
        }
    }

}
