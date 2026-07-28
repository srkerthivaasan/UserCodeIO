package com.rkworld.HelloWorld.Repository;

import com.rkworld.HelloWorld.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <Todo,Long> {

}

