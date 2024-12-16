package com.mehedi.mongo_db_practice_01.repository;

import com.mehedi.mongo_db_practice_01.model.Tasks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Tasks, String> {
    List<Tasks> findBySeverity(int severity);

    @Query("{ assignee: ?0 }")
    List<Tasks> getTasksByAssignee(String assignee);
}
