package com.sofka.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sofka.crud.models.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}