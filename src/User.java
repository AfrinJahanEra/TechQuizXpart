package src;

import java.util.Objects;

public class User {
    private String name;
    private String university;
    private String department;
    private Subject subject;

    public User(String name, String university, String department, Subject subject) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }

    public String getDepartment() {
        return department;
    }

    public Subject getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(university, user.university) &&
                Objects.equals(department, user.department) &&
                subject == user.subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, university, department, subject);
    }
}