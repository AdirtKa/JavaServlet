package models;

public class Course {
    private int id;
    private String dayOfWeek;
    private String time; // Формат hh:mm
    private String name;
    private String classroom;

    // Конструктор по умолчанию
    public Course() {
    }

    // Конструктор с параметрами
    public Course(int id, String dayOfWeek, String time, String name, String classroom) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.name = name;
        this.classroom = classroom;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                '}';
    }
}
