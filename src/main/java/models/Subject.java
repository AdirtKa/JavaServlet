package models;

public class Subject {
    // Атрибуты класса
    private int id;
    private String name;
    private String teacher;
    private String faculty;

    // Конструктор класса
    public Subject(int id, String name, String teacher, String faculty) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.faculty = faculty;
    }

    // Геттер для id
    public int getId() {
        return id;
    }

    // Сеттер для id
    public void setId(int id) {
        this.id = id;
    }

    // Геттер для name
    public String getName() {
        return name;
    }

    // Сеттер для name
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для teacher
    public String getTeacher() {
        return teacher;
    }

    // Сеттер для teacher
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    // Геттер для faculty
    public String getFaculty() {
        return faculty;
    }

    // Сеттер для faculty
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    // Метод для вывода информации о предмете
    public void displayInfo() {
        System.out.println("Subject ID: " + id);
        System.out.println("Subject Name: " + name);
        System.out.println("Teacher: " + teacher);
        System.out.println("Faculty: " + faculty);
    }
}
