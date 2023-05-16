package org.example;

import org.example.domain.Student;
import org.example.repository.StudentMysqlRepository;
import org.example.repository.StudentRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentMysqlRepository();

        Student s1 = Student.builder()
                .name("Shanks")
                .age(34)
                .groupId(2)
                .build();
        studentRepository.save(s1);
        System.out.println("success");
        List<Student> students = studentRepository.findAll();
        students.forEach(student -> System.out.println(student));


    }
}