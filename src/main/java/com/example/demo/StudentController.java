package com.example.demo;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<PageResult<Student>> search(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.success(
                service.search(name, page, size)
        );
    }
    @GetMapping("/{id}")
    public ApiResponse<Student> findById(@PathVariable int id) {
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> add(
            @Valid @RequestBody StudentRequest request) {

        Student savedStudent = service.add(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created("创建成功", savedStudent));
    }

    @PutMapping("/{id}")
    public ApiResponse<Student> update(
            @PathVariable int id,
            @Valid @RequestBody StudentRequest request) {

        Student updatedStudent = service.update(id, request);

        return ApiResponse.success("修改成功", updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        service.delete(id);

        return ApiResponse.<Void>success("删除成功", null);
    }
}