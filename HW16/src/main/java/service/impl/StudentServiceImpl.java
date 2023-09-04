package service.impl;

import basics.service.impl.BaseServiceImpl;
import entity.BankAccount;
import entity.Student;
import entity.enums.*;
import exceptions.InvalidDateException;
import repository.StudentRepositoryImpl;
import service.StudentService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.*;


public class StudentServiceImpl extends BaseServiceImpl<StudentRepositoryImpl, Student> implements StudentService {

    private final BankAccountServiceImpl bankAccountService;
    private final LoanServiceImpl loanService;
    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
        bankAccountService = ApplicationContext.bankAccountService;
        loanService = ApplicationContext.loanService;
    }

    @Override
    public Student validateAndSetInformation(Student student) throws InvalidDateException {
        try{
            printer.printMessage("Pleas fill the required fields:");
            printer.getInput("First name");
            student.setFirstname(input.nextLine());
            printer.getInput("Last name");
            student.setLastname(input.nextLine());
            printer.getInput("Father name");
            student.setFathername(input.nextLine());
            printer.getInput("Mother name");
            student.setMothername(input.nextLine());
            printer.getInput("Identity serial number");
            student.setIdentityCode(input.nextLine());
            printer.getInput("National code");
            student.setNationalCode(input.nextLine());
            do{
                student.setBirthDate(setDate());
            } while (student.getBirthDate()==null);
            printer.printMessage("Select your province from list bellow");
            printer.printListWithSelect(Arrays.stream(Province.values()).map(Object::toString).toList());
            student.setProvince(Province.values()[input.nextInt()-1]);
            printer.printMessage("Marital Status");
            printer.printListWithSelect(Arrays.stream(MaritalStatus.values()).map(Objects::toString).toList());
            int choice = input.nextInt();
            input.nextLine();
            student.setMarried(MaritalStatus.values()[choice - 1].name().equals("MARRIED"));
            if(student.isMarried()){
                printer.getInput("Spouse's first name");
                student.setSpouseFirstName(input.nextLine());
                printer.getInput("Spouse's last name");
                student.setSpouseLastName(input.nextLine());
                printer.getInput("Spouse's national code");
                student.setSpouseNationalCode(input.next());
            }
            printer.printMessage("University type");
            printer.printListWithSelect(Arrays.stream(UniversityType.values()).map(Objects::toString).toList());
            student.setUniversityType(UniversityType.values()[input.nextInt()-1]);
            printer.getInput("Entrance year");
            int entranceYear = input.nextInt();
            input.nextLine();
            if(entranceYear >= (student.getBirthYear() + 15))
                student.setEntranceYear(entranceYear);
            else
                throw new InvalidDateException(Constants.INVALID_AGE_EXCEPTION);
            printer.getInput("Student code");
            student.setStudentNumber(input.next());
            input.nextLine();
            printer.printMessage("Acceptance type");
            printer.printListWithSelect(Arrays.stream(AcceptanceType.values()).map(Objects::toString).toList());
            student.setAcceptanceType(AcceptanceType.values()[input.nextInt()-1]);
            input.nextLine();
            printer.printMessage("Academic degree");
            printer.printListWithSelect(Arrays.stream(AcademicGrade.values()).map(Objects::toString).toList());
            student.setAcademicGrade(AcademicGrade.values()[input.nextInt()-1]);
            input.nextLine();
            printer.printMessage("Residence status");
            printer.printListWithSelect(Arrays.stream(ResidenceStatus.values()).map(Object::toString).toList());
            student.setInDorm(input.nextInt() == ResidenceStatus.DORM.ordinal()+1);
            input.nextLine();
            if(!student.isInDorm()){
                printer.getInput("House rent contract number");
                student.setHouseContractNumber(input.next());
                input.nextLine();
                printer.getInput("House address");
                student.setHouseAddress(input.nextLine());
            }
            do {
                student.setBankAccount(bankAccountService.validateAndSetInformation(new BankAccount()));
            } while (student.getBankAccount() == null);

            student.setGraduateYear(student.getEntranceYear()+student.getAcademicGrade().getGraduateDuration());
            if(isValid(student))
                return student;
            else
                return null;
        }catch (Exception e){
            student = null;
            throw e;
        }
    }

    @Override
    public Student setUsernameAndPassword(Student student) {
        if(student != null){
            Random random = new Random();
            Character[] password = new Character[8];
            List<Integer> indexes = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7));
            int counter = 0;
            while(!indexes.isEmpty()){
                int i = random.nextInt(0,indexes.size());
                int passwordIndex = indexes.get(i);
                indexes.remove(i);
                switch (counter ++) {
                    case 0 -> password[passwordIndex] = Constants.UPPER_CASE_CHARACTERS[random.nextInt(0,Constants.UPPER_CASE_CHARACTERS.length)];
                    case 1 -> password[passwordIndex] = Constants.LOWER_CASE_CHARACTERS[random.nextInt(0,Constants.LOWER_CASE_CHARACTERS.length)];
                    case 2 -> password[passwordIndex] = Constants.DIGIT_CHARACTERS[random.nextInt(0,Constants.DIGIT_CHARACTERS.length)];
                    case 3 -> password[passwordIndex] = Constants.SPECIAL_CHARACTERS[random.nextInt(0,Constants.SPECIAL_CHARACTERS.length)];
                    default -> {
                        Character[] nextArray = ApplicationContext.passwordCharactersMap.get(random.nextInt(1,5));
                        password[passwordIndex] = nextArray[random.nextInt(0,nextArray.length)];
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(Character c : password){
                stringBuilder.append(c);
            }
            student.setUsername(student.getNationalCode());
            student.setPassword(stringBuilder.toString());
            return student;
        }
        else
            throw new NullPointerException(Constants.NULL_STUDENT_EXCEPTION);
    }

    @Override
    public void showUsernameAndPassword(Student student) {
        if(student != null){
            printer.printResult("USERNAME", student.getUsername());
            printer.printResult("PASSWORD", student.getPassword());
        }
    }

    @Override
    public void checkUsernameAndPassword() {

    }

    public Date setDate() {
        try{
            printer.getInput("Year of birth");
            int year = input.nextInt();
            input.nextLine();
            printer.getInput("Month of birth");
            int month = input.nextInt();
            input.nextLine();
            printer.getInput("Day of birth");
            int day = input.nextInt();
            input.nextLine();
            int diff = ApplicationContext.gregorianCalendar.get(Calendar.YEAR) - year;
            if(diff < 15)
                throw new InvalidDateException(Constants.INVALID_AGE_EXCEPTION);
            if(year<0 || month<0 || day<0 || month>12 || day>31)
                throw new InvalidDateException(Constants.INVALID_DATE_ENTRY_EXCEPTION);
            if(month == 2 && day > 28)
                throw new InvalidDateException(Constants.INVALID_DAY_OF_MONTH_EXCEPTION);
            if((month==4 || month ==6 || month == 9 || month == 11) && day>30)
                throw new InvalidDateException(Constants.INVALID_DAY_OF_MONTH_EXCEPTION);
            return new GregorianCalendar(year,month-1,day).getTime();
        }catch (Exception e){
            printer.printError(e.getMessage());
            input.nextLine();
            return null;
        }
    }



}
