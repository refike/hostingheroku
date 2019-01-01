package com.exercise.todoprojekt.application;


import com.exercise.todoprojekt.application.Repository.AssigneeRepository;
import com.exercise.todoprojekt.application.Repository.TodoRepository;
import com.exercise.todoprojekt.application.Repository.model.Assignee;
import com.exercise.todoprojekt.application.Repository.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationTodo implements CommandLineRunner{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AssigneeRepository assigneeRepository;


    public static void main(String[] args) {
        SpringApplication.run(ApplicationTodo.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        todoRepository.save(new Todo("Feed the dog",false,true,"Nagyon éhes a blöki!"));
        todoRepository.save(new Todo("Save the World",true,false,"With *TECHNO*"));
        todoRepository.save(new Todo("Do the shopping",true,true,"Irány a bolt"));
        todoRepository.save(new Todo("Watch TV",false,false,"New England Patriots in superbowl"));
        todoRepository.save(new Todo("Buy some weed",true,true," 420 "));
        assigneeRepository.save(new Assignee("john", "john@valami.com"));
        assigneeRepository.save(new Assignee("Dani", "dani@valami.com"));
        assigneeRepository.save(new Assignee("Vica", "vicus@valami.com"));
    }

}

