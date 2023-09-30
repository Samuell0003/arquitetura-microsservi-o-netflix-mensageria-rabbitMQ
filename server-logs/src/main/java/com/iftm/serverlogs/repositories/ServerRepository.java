package com.iftm.serverlogs.repositories;

import com.iftm.serverlogs.models.Logs;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends MongoRepository<Logs, ObjectId> {

}
