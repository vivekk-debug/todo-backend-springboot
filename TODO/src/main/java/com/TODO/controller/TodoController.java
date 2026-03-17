package com.TODO.controller;

import com.TODO.entity.Todo;
import com.TODO.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ToDo")
public class TodoController {

    private TodoService service;
    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping("/addToDo")
    public ResponseEntity<Todo> addToDo(@RequestBody Todo todo){
        Todo res = service.addToDo(todo);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @GetMapping("/getTodo/{id}")
    public ResponseEntity<Optional<Todo>> getTodo(@PathVariable int id){
        Optional<Todo> res = service.getTodo(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
