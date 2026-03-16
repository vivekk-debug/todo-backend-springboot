package com.TODO.controller;

import com.TODO.entity.Todo;
import com.TODO.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
