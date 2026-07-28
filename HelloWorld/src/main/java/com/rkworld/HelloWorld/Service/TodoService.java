package com.rkworld.HelloWorld.Service;

import com.rkworld.HelloWorld.Entity.Todo;
import com.rkworld.HelloWorld.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));
    }

    public Page<Todo> getAllTodosPages(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return todoRepository.findAll(pageable);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo UpdateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void DeleteTodoById(Long id) {
        todoRepository.delete(getTodoById(id));
    }

    public void DeleteTodo(Todo todo) {
        todoRepository.delete(todo);
    }
}
