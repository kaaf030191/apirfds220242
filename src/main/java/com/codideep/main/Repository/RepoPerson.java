package com.codideep.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codideep.main.Entity.TPerson;

@Repository
public interface RepoPerson extends JpaRepository<TPerson, String> {}