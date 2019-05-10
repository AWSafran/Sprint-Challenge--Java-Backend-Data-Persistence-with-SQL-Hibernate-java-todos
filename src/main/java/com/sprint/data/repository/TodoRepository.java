package com.sprint.data.repository;

import com.sprint.data.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
}
