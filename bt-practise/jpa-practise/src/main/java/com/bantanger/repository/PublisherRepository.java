package com.bantanger.repository;

import com.bantanger.entity.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
public interface PublisherRepository extends JpaRepository<Publishers, Long> {

    List<Publishers> findAllByLocation(String location);

    @Query("select p from Publishers p where p.journals > :minJournals and p.location = :location")
    List<Publishers> findPublishersWithMinJournalsInLocation(Integer minJournals, String location);
}
