package com.restapi.restcontroller;

import com.restapi.entity.Student;
import com.restapi.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudent;
    //Define @Post Construct to load the student data ...... only once!

    @PostConstruct
    public void loadData(){
        theStudent = new ArrayList<>();
        theStudent.add(new Student("Poornima","patel"));
        theStudent.add(new Student("ankit","anand"));
        theStudent.add(new Student("rajesh","kumar"));

    }

    //define endPoint for "/students" --return list of students

    @GetMapping("/students")
    List<Student> getStudent(){
        return theStudent;
    }

    //define endPoint or    "/student/{studentId}" ---return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //check the studentId again the list size
        if(studentId >= theStudent.size() || (studentId < 0)){
            throw new StudentNotFoundException("Student is not found - "+studentId);
        }


        //just index into the list ..... keep it simple for now
        return theStudent.get(studentId);
    }



    //Add exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMesssage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //Return Response Entity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //Add another exception handler .. to catch any type of exception(catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMesssage(exc.getMessage());
        error.setMesssage("Invalid input");
        error.setTimeStamp(System.currentTimeMillis());

        //Return Response Entity
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

}
