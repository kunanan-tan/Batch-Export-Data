package com.example.batch.data.repository;

import com.example.batch.data.entity.TestDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kunanan.t
 */
@Repository
public interface TestDbRepository extends JpaRepository<TestDb,Integer> {

    @Query("SELECT t FROM TestDb t ")
    List<TestDb> findTest();
}
