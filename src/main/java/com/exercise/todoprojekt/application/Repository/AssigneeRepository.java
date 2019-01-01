package com.exercise.todoprojekt.application.Repository;

import com.exercise.todoprojekt.application.Repository.model.Assignee;
import org.springframework.data.repository.CrudRepository;

public interface AssigneeRepository extends CrudRepository<Assignee,Long> {

    Assignee findByName(String name);

}
