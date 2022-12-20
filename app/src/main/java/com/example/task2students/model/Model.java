package com.example.task2students.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();
    List<Student> data = new LinkedList<>();

    public static Model instance(){
        return _instance;
    }

    private Model(){
        data.add(new Student("Almog", "3244567", "0527738276", "Tel aviv", true));
        data.add(new Student("Yuval", "3233456", "0527348276", "Rishon", false));
        data.add(new Student("Dor", "23456782", "0527738276", "Haifa", false));
        data.add(new Student("Alon", "30988753", "0527738276", "Beersheba", true));
        data.add(new Student("Rina", "3244567", "0527738276", "Ashdod", false));
        data.add(new Student("Dani", "33456822", "0527738276", "Bat yam", true));
        data.add(new Student("Sun", "23647592", "0527738276", "Dimona", true));
    }

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student student){
        data.add(student);
    }

    public void deleteStudent(Student student){
        data.remove(student);
    }

    public void updateStudent(int oldStudentPosition, Student newStudent){
        Student student = data.get(oldStudentPosition);
        student.setName(newStudent.getName());
        student.setId(newStudent.getId());
        student.setAddress(newStudent.getAddress());
        student.setPhone(newStudent.getPhone());
        student.setChecked(newStudent.isChecked());
    }
}
