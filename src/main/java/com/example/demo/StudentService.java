package com.example.demo;

import com.example.demo.dto.StudentRequest;
import com.example.demo.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.dto.PageResult;
import com.example.demo.exception.InvalidRequestException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentMapper mapper;

    public StudentService(StudentMapper mapper) {
        this.mapper = mapper;
    }

    public List<Student> findAll() {
        return mapper.findAll();
    }

    public Student findById(int id) {
        Student student = mapper.findById(id);

        if (student == null) {
            throw new StudentNotFoundException(id);
        }

        return student;
    }

    @Transactional(readOnly = true)
    public PageResult<Student> search(String name,
                                      int page,
                                      int size) {

        validatePage(page, size);

        String keyword = name == null ? "" : name.trim();

        long total = mapper.countByName(keyword);

        int totalPages = total == 0
                ? 0
                : (int) ((total + size - 1) / size);

        if (total == 0) {
            return new PageResult<>(
                    List.of(),
                    0,
                    page,
                    size,
                    0
            );
        }

        int offset = (page - 1) * size;

        List<Student> records = mapper.selectPage(
                keyword,
                offset,
                size
        );

        return new PageResult<>(
                records,
                total,
                page,
                size,
                totalPages
        );
    }

    private void validatePage(int page, int size) {
        if (page < 1) {
            throw new InvalidRequestException(
                    "page 必须大于等于 1"
            );
        }

        if (size < 1 || size > 100) {
            throw new InvalidRequestException(
                    "size 必须在 1 到 100 之间"
            );
        }
    }

    public Student add(StudentRequest request) {
        Student student = toStudent(request);
        mapper.insert(student);
        return student;
    }

    public Student update(int id, StudentRequest request) {
        Student student = toStudent(request);
        student.setId(id);

        int rows = mapper.update(student);

        if (rows == 0) {
            throw new StudentNotFoundException(id);
        }

        return student;
    }

    public boolean delete(int id) {
        int rows = mapper.delete(id);

        if (rows == 0) {
            throw new StudentNotFoundException(id);
        }

        return true;
    }

    private Student toStudent(StudentRequest request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setScore(request.getScore());

        return student;
    }
}