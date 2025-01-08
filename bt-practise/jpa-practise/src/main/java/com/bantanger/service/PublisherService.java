package com.bantanger.service;

import com.bantanger.entity.Publishers;
import com.bantanger.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@Service
public class PublisherService {

    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public Publishers save(Publishers publishers) {
        return repository.save(publishers);
    }

    public List<Publishers> findAll() {
        return repository.findAll();
    }

    public Publishers findById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public List<Publishers> findPublishersWithMinJournalsInLocation(int minJournals, String location) {
        return repository.findPublishersWithMinJournalsInLocation(minJournals, location);
    }

}
