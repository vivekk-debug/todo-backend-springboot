package com.TODO.service;

import com.TODO.DTO.TodoDto;
import com.TODO.entity.Todo;
import com.TODO.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public TodoDto addToDo(TodoDto todo) {
        Todo res = convertToEntity(todo);
        repo.save(res);
        TodoDto resDto = convertToDto(res);
        return  resDto;
    }

    public Optional<TodoDto> getTodo(int id) {
        Todo todo = repo.findById(id).orElseThrow(null);

        TodoDto dto= convertToDto(todo);
        return Optional.ofNullable(dto);
    }

    public List<Todo> getAllTodos() {
        return repo.findAll();
    }

    public TodoDto updateTodo(int id,TodoDto todo) {
        Todo t = repo.findById(id).orElseThrow(null);
        t.setCompleted(todo.isCompleted());
        repo.save(t);
        return convertToDto(t);
    }

    public TodoDto completeUpdateTodo(int id, TodoDto todoDto) {
//        Todo t = repo.findById(id).orElseThrow(null);
//        todoDto.setId(id);
//        modelMapper.map(todoDto,t);
//        repo.save(t);
//        TodoDto eT = convertToDto(t);
//        return eT;
        Todo existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        // 🔥 Safe mapping (ID will NOT be touched)
        modelMapper.map(todoDto, existing);

        existing.setCreatedAt(LocalDateTime.now());

        Todo saved = repo.save(existing);

        return convertToDto(saved);
    }

    public String deleteTodo(int id) {
        repo.deleteById(id);
        return "Todo of "+id+" has been deleted";
    }

    public TodoDto convertToDto(Todo todo){
        return modelMapper.map(todo, TodoDto.class);
    }

    public Todo convertToEntity(TodoDto todoDto){
        return modelMapper.map(todoDto , Todo.class);
    }
}