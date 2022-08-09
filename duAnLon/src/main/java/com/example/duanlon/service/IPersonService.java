package com.example.duanlon.service;

import com.example.duanlon.model.Person;

public interface IPersonService extends IAbstractService<Person>{
    Person createPerson(Person person);
}
