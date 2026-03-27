package com.TODO.controller;

import com.TODO.DTO.TodoDto;
import com.TODO.entity.Todo;
import com.TODO.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ToDo")
public class TodoController {

    private TodoService service;
    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping("/addToDo")
    public ResponseEntity<TodoDto> addToDo(@RequestBody TodoDto todo){
        TodoDto res = service.addToDo(todo);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @GetMapping("/getTodo/{id}")
    public ResponseEntity<Optional<TodoDto>> getTodo(@PathVariable int id){
        Optional<TodoDto> res = service.getTodo(id);
        return new ResponseEntity<>(res,HttpStatus.FOUND);
    }

    @GetMapping("/getAllTodos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> res= service.getAllTodos();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PatchMapping("/updateTodo/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable int id,@RequestBody TodoDto todoDto){
        TodoDto res = service.updateTodo(id,todoDto);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/completeUpdateTodo/{id}")
    public ResponseEntity<TodoDto> completeUpdateTodo(@PathVariable int id,@RequestBody TodoDto todoDto){
        TodoDto res = service.completeUpdateTodo(id,todoDto);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id){
        String res = service.deleteTodo(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
