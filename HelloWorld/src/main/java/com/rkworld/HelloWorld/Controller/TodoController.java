package com.rkworld.HelloWorld.Controller;

import com.rkworld.HelloWorld.Entity.Todo;
import com.rkworld.HelloWorld.Service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200",description = "Todo Retrieved Successfully"),
            @ApiResponse(responseCode ="404" ,description="Todo was not Found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id){
        try{
            Todo createTodo=todoService.getTodoById(id);
            return new ResponseEntity<>(createTodo,HttpStatus.OK);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/page")
    ResponseEntity<Page<Todo>>getTodosPaged(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoService.getAllTodosPages(page,size),HttpStatus.OK);
    }

    @GetMapping
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
