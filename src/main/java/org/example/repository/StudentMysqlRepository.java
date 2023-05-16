package org.example.repository;

import org.example.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMysqlRepository implements StudentRepository{
    private static final String DB_URL = "jdbc:mysql://robot-do-user-1968994-0.b.db.ondigitalocean.com:25060/tonyjdbc";
    private static final String DB_USER = "doadmin";
    private static final String DB_PASSWORD = "AVNS_I6wlDKjGszZn1wvLr9t";
    private static final String SELECT_FROM_STUDENTS = "select * from student;";
    private static final String STUDENT_UPDATE = "insert into student (name, age, group_id) values (?, ?, ?)";


    @Override
    public void save(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(STUDENT_UPDATE);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setInt(3, student.getGroupId());
            ps.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            try {
                assert conn != null;
                conn.rollback();
            }catch(SQLException ex){

            }
        }finally{
            try{
                assert conn != null;
                conn.close();
                ps.close();
            }catch (SQLException e){

            }
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_FROM_STUDENTS)){
            while(rs.next()){
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .groupId(rs.getInt("group_id"))
                        .build();
                result.add(student);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Student findById(int id) {
        return null;
    }
}
