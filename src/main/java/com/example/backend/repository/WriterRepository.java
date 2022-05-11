package com.example.backend.repository;

import com.example.backend.entity.Writer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WriterRepository extends CrudRepository<Writer, Integer> {
}