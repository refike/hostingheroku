package com.exercise.todoprojekt.Repository;

import com.exercise.todoprojekt.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo,Long> {
    public List<Todo> findByDone(Boolean b);

}
