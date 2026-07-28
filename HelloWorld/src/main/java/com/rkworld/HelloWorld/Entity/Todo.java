package com.rkworld.HelloWorld.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @NotBlank
    @Schema(name="title",example ="Spring Boot")
    String Title;
    String Description;
    Boolean IsComplete;
}
