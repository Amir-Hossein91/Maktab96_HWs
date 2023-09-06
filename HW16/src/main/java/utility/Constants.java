package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final String INVALID_DAY_OF_MONTH_EXCEPTION = "Not a valid day number in the specified month!";
    public static final String INVALID_AGE_EXCEPTION = "Age can not be less than 15!";
    public static final String INVALID_DATE_ENTRY_EXCEPTION = "Invalid date entry!";
    public static final String INVALID_CARD_NUMBER_EXCEPTION = "Invalid bank account card number!";
    public static final String INVALID_CVV2_NUMBER_EXCEPTION = "Invalid cvv2 number!";
    public static final String INVALID_EXPIRATION_MONTH_EXCEPTION = "Invalid card expiration month format!";
    public static final String INVALID_EXPIRATION_YEAR_EXCEPTION = "Invalid card expiration year format!";
    public static final String NULL_STUDENT_EXCEPTION = "You need to submit valid information first!";
    public static final String INVALID_NAME_FORMAT = "Invalid name format!";
    public static final String INVALID_LASTNAME_FORMAT = "Invalid last name format!";
    public static final String INVALID_IDENTITY_CODE_FORMAT = "Identity code has at most 10 digits!";
    public static final String INVALID_NATIONAL_CODE_FORMAT = "National code must have 10 digits!";
    public static final String INVALID_STUDENT_CODE_FORMAT = "Student code must have 8 digits!";
    public static final String INVALID_ENTRANCE_YEAR_FORMAT = "Invalid entrance year format!";
    public static final String INVALID_HOUSE_RENT_CONTRACT_NUMBER_FORMAT = "House rent contract number must have 6 to 9 digits!";
    public static final String INVALID_ADDRESS_FORMAT = "Invalid address format!";
    public static final String INCORRECT_USERNAME_OR_PASSWORD = "Username or password is incorrect!";
    public static final String LOAN_REGISTER_TIME_OUT = "Can't register for a loan after graduation year!";
    public static final String REPAY_TIME_OUT = "Can't repay a loan until after graduation!";
    public static final String NOT_IN_REGITRATION_PERIOD = "Registration is not active now!";

    public static final Character[] UPPER_CASE_CHARACTERS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static final Character[] LOWER_CASE_CHARACTERS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final Character[] SPECIAL_CHARACTERS = {'&','%','$','#','@'};
    public static final Character[] DIGIT_CHARACTERS = {'0','1','2','3','4','5','6','7','8','9'};

    public static final int oddCoursesRegisterMonth = 9;
    public static final List<Integer> oddCourseRegisterDays = new ArrayList<>(Arrays.asList(23,24,25,26,27,28,29));
    public static final int evenCourseRegisterMonth = 1;
    public static final List<Integer> evenCourseRegisterDays = new ArrayList<>(Arrays.asList(14,15,16,17,18,19,20));



    public static final String[] REGISTRY_MENU = {"LOGIN/REGISTER MENU","Register","Login","Exit"};
    public static final String[] STUDENT_LOAN_MENU = {"STUDENT LOAN MENU","Loan Register","loan Repay","Logout"};
    public static final String[] LOAN_REGISTER_MENU = {"LOAN REGISTER MENU","See Available loans","Register for a loan","Back"};
}
