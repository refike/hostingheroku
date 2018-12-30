package com.exercise.todoprojekt.application;


import com.exercise.todoprojekt.application.Repository.TodoRepository;
import com.exercise.todoprojekt.application.Repository.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationTodo implements CommandLineRunner{

    private TodoRepository todoRepository;

    @Autowired
    public ApplicationTodo(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTodo.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        todoRepository.save(new Todo("Feed the dog",false,true,"Nagyon éhes a blöki!"));
        todoRepository.save(new Todo("Make the bed",false,false,""));
        todoRepository.save(new Todo("Do the shopping",true,true,"Menj le a boltba"));
        todoRepository.save(new Todo("Watch TV",false,false,"NFL"));
        todoRepository.save(new Todo("Buy some weed",true,true," 420 "));

    }

}

