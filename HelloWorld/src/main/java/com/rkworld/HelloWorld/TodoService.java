package com.rkworld.HelloWorld;

import com.rkworld.HelloWorld.Entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
