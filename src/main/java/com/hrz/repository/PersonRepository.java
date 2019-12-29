package com.hrz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.hrz.entity.Person;

@Component
public interface PersonRepository extends JpaRepository<Person, Long> {}
