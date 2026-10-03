package com.example.demo;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("""
            SELECT id, name, score
            FROM student
            WHERE name LIKE CONCAT('%', #{name}, '%')
            ORDER BY id DESC
            LIMIT #{size} OFFSET #{offset}
            """)
    List<Student> selectPage(
            @Param("name") String name,
            @Param("offset") int offset,
            @Param("size") int size
    );

    @Select("""
            SELECT COUNT(*)
            FROM student
            WHERE name LIKE CONCAT('%', #{name}, '%')
            """)
    long countByName(@Param("name") String name);


    @Select("SELECT * FROM student")
    List<Student> findAll();

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findById(int id);

    @Insert("INSERT INTO student (name, score) VALUES (#{name}, #{score})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Student student);

    @Update("UPDATE student SET name = #{name}, score = #{score} WHERE id = #{id}")
    int update(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    int delete(int id);
}
