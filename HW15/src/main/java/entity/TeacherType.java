package entity;

import java.util.HashMap;
import java.util.Map;

public enum TeacherType {
    FACULTY_MEMBER(1),
    ADJUNCT_PROFESSOR(2);

    private int value;
    private static Map<Integer,TeacherType> map = new HashMap<>();

    TeacherType(int value){
        this.value = value;
    }

    static{
        for(TeacherType teacherType : TeacherType.values()){
            map.put(teacherType.value,teacherType);
        }
    }

    public static TeacherType valueOf(int teacherType){
        return map.get(teacherType);
    }

}
