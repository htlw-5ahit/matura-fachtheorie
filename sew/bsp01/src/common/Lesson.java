package common;

import java.io.Serializable;

public class Lesson implements Serializable {

    private String id, teacher, classroom, subject;
    private int day, lesson;

    public Lesson() {
    }

    public Lesson(String id, String teacher, String classroom, String subject, int day, int lesson) {
        this.id = id;
        this.teacher = teacher;
        this.classroom = classroom;
        this.subject = subject;
        this.day = day;
        this.lesson = lesson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classroom='" + classroom + '\'' +
                ", subject='" + subject + '\'' +
                ", day=" + day +
                ", lesson=" + lesson +
                '}';
    }
}
