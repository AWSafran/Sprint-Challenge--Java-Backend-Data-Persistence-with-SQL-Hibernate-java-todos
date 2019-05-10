package com.sprint.data.repository;

import com.sprint.data.model.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
    @Modifying
    @Query(value = "UPDATE todo SET description = :newdescription WHERE todoid = :todoid", nativeQuery = true)
    void updateTodo(String newdescription, long todoid);
}
