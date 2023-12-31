package utility;

public class Constants {

    public static final int CURRENT_SEMESTER_NUMBER = 5;

    public static final String SCORE_SAVE_EXCEPTION = "Could not save the score!";
    public static final String COURSE_SAVE_EXCEPTION = "Could not save the course!";
    public static final String COURSE_NOT_PRESENTED = "Course is not presented in this semester!";
    public static final String DID_NOT_PRESENT_COURSE = "You have not presented this course in current semester!";
    public static final String COURSE_TAKEN = "You have already taken this course in current semester!";
    public static final String COURSE_PASSED = "You have already passed the course!";
    public static final String COURSE_WITHOUT_STUDENT = "No students have taken this course!";
    public static final String NO_TEACHER_PRESENTED_COURSE = "No teacher has presented this course!";
    public static final String NOT_CURRENT_SEMESTER_COURSE = "This course id is not valid in current semester!";
    public static final String NO_COURSE_PREVIOUS_SEMESTER = "student had no courses in previous semester! (Credit limit = 20)";
    public static final String EMPLOYEE_SAVE_EXCEPTION = "Could not save the employee!";
    public static final String SALARY_REPORT_SAVE_EXCEPTION = "Could not save the salary report!";
    public static final String PERSON_SAVE_EXCEPTION = "Could not save user!";
    public static final String STUDENT_SAVE_EXCEPTION = "Could not save the student!";
    public static final String TEACHER_SAVE_EXCEPTION = "Could not save the teacher!";

    public static final String[] LOGIN_MENU = {"WELCOME!","Login","Exit"};

    public static final String[] EMPLOYEE_MAIN_MENU = {"EMPLOYEE MENU","Students","Teachers","Employees","Courses", "Get salary report", "Logout"};
    public static final String[] EMPLOYEE_STUDENT_MENU = {"STUDENTS MANAGEMENT","Register student", "Update student information","Remove student","See all students", "Back"};
    public static final String[] EMPLOYEE_TEACHER_MENU = {"TEACHERS MANAGEMENT","Add teacher", "Update teacher information", "Remove teacher", "See all teachers", "Back"};
    public static final String[] EMPLOYEE_EMPLOYEE_MENU = {"EMPLOYEES MANAGEMENT", "Add employee", "Update employee information", "Remove employee", "See all employees", "Back"};
    public static final String[] EMPLOYEE_COURSE_MENU = {"COURSES MANAGEMENT","Add course","Update course information", "Remove course", "See all courses", "Back"};

    public static final String[] TEACHER_MAIN_MENU = {"TEACHER MENU", "Account info", "Presenting courses", "Set scores","Get salary report", "Logout"};
    public static final String[] TEACHER_ACCOUNT_MENU = {"ACCOUNT INFO", "See info", "Update info","Back"};
    public static final String[] TEACHER_COURSE_MENU = {"PRESENTING COURSES", "See the list", "Add course","Remove course","Back"};

    public static final String[] STUDENT_MAIN_MENU = {"STUDENT MENU", "Account info", "Get a course","List of current semester courses", "Course history", "Logout"};
    public static final String[] STUDENT_ACCOUNT_MENU = {"ACCOUNT INFO", "See info", "Update info","Back"};
}
