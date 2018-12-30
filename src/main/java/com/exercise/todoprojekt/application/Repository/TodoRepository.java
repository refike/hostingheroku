package com.exercise.todoprojekt.application.Repository;

import com.exercise.todoprojekt.application.Repository.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TodoRepository extends CrudRepository<Todo,Long> {
    List<Todo> findAllByOrderByDateDesc();
    List<Todo> findByDone(Boolean b);
    List<Todo> findAllByTitleContainsOrDescriptionContains(String title, String description);


}
