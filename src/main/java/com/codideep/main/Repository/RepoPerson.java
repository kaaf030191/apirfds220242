package com.codideep.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codideep.main.Entity.TPerson;

public interface RepoPerson extends JpaRepository<TPerson, String> {}