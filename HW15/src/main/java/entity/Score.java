package entity;

import jakarta.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    private int semesterNumber;
    private Float value;
    private boolean isPassed;
}
