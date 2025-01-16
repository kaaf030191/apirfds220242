package com.codideep.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codideep.main.Entity.TPerson;

@Repository
public interface RepoPerson extends JpaRepository<TPerson, String> {
	@Query(value = "select * from tperson where dni = :dni", nativeQuery = true)
	Optional<TPerson> getByDni(String dni);
}