package question2.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull(message = "Firstname can't be null")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String firstname;
    @NotNull(message = "Lastname can't be null")
    @Size(min = 3, message = "Lastname must be al least 3 characters")
    private String lastname;
    @Temporal(value = TemporalType.DATE)
    @Past
    private Date birthDate;

    public Person (){
    }

    public Person(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
        birthDate = null;
    }
    public Person(String firstname, String lastname, Date birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public Person(long id, String firstname, String lastname, Date birthDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && firstname.equals(person.firstname) && lastname.equals(person.lastname) && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, birthDate);
    }

}
