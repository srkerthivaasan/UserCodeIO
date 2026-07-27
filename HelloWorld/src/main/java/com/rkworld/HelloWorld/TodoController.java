package com.rkworld.HelloWorld;

import com.rkworld.HelloWorld.Entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/get")
    String getTodo(){
        return"ToDo";
    }

    //PathVaraiable
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id){
        try{
            Todo createTodo=todoService.getTodoById(id);
            return new ResponseEntity<>(createTodo,HttpStatus.OK);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<List<Todo>>(todoService.getTodos(),HttpStatus.OK);
    }



    //RequestBody
    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo){
           Todo createTodo=todoService.createTodo(todo);
           return new ResponseEntity<>(createTodo,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> updateTodoById(@PathVariable long id,@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.UpdateTodo(todo),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id){
        todoService.DeleteTodoById(id);
    }
}
