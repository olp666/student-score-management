package com.example.demo.exception;

public class StudentNotFoundException extends BusinessException {

    public StudentNotFoundException(long id) {
        super(404, "学生不存在: id=" + id);
    }
}
