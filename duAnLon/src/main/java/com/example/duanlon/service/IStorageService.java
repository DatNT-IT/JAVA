package com.example.duanlon.service;

import com.example.duanlon.model.Storage;

import java.util.Optional;

public interface IStorageService extends IAbstractService<Storage>{
    Storage createStorage(Storage storage);
    Optional<Storage> findByName(String name);
    Optional<Storage> findByLocation(String location);
}
