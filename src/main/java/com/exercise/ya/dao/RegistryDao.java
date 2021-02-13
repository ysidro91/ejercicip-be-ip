package com.exercise.ya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.ya.entity.Registry;

public interface RegistryDao extends JpaRepository<Registry, Long>{
	
	public Optional<Registry> findByCountryName(String countryName);

}
