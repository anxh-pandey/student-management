package com.example.student.controller;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // POST /api/students -> 201 Created
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(student));
    }

    // GET /api/students -> 200 OK
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // GET /api/students/{id} -> 200 OK or 404
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    // PUT /api/students/{id} -> 200 OK or 404
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        student.setCourse(updated.getCourse());
        student.setAge(updated.getAge());
        return repository.save(student);
    }

    // DELETE /api/students/{id} -> 204 No Content or 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/students/course/{course} -> 200 OK
    @GetMapping("/course/{course}")
    public List<Student> getByCourse(@PathVariable String course) {
        return repository.findByCourse(course);
    }
}
