package com.rkworld.HelloWorld.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    String Title;
    String Description;
    Boolean IsComplete;
}
