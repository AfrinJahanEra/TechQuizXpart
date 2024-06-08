package ProjectCode;

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
}

