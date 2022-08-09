package com.example.duanlon.service.impl;

import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.model.Storage;
import com.example.duanlon.repository.IStorageRepository;
import com.example.duanlon.service.IStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StorageServiceImpl implements IStorageService {
    private IStorageRepository iStorageRepository;

    @Autowired
    public StorageServiceImpl(IStorageRepository iStorageRepository) {
        this.iStorageRepository = iStorageRepository;
    }

    @Override
    public Storage save(Storage entity) {
iStorageRepository.save(entity);
        log.info("Save Storage : {}", entity);
        return entity;
    }

    @Override
    public Storage findById(Long entityId) {
        return iStorageRepository.findById(entityId).get();
    }

    @Override
    public void delete(Storage entity) {
iStorageRepository.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        boolean exist = iStorageRepository.existsById(entityId);
        if (exist) {
            iStorageRepository.deleteById(entityId);
        }
    }

    @Override
    public Iterable<Storage> findAll() {
        return iStorageRepository.findAll();
    }

    @Override
    public Storage createStorage(Storage storage) {
        Storage storage1 = new Storage();
        storage1.setName(storage.getName());
        storage1.setEvidenceSet(storage.getEvidenceSet());
        storage1.setLocation(storage.getLocation());
        iStorageRepository.save(storage1);
        return storage1;
    }

    @Override
    public Optional<Storage> findByName(String name) {
        return iStorageRepository.findByName(name);
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        return iStorageRepository.findByLocation(location);
    }
}
