package com.enterprise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.model.Coutry;
import com.enterprise.model.State;

public interface StateRepo extends JpaRepository<State, Integer>{

	Optional<State> findByName(String username);

}
