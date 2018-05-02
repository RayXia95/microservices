package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Hero;

@Transactional
public interface HeroRepository extends JpaRepository<Hero, Long> {
	Hero findByNameIgnoreCase(String name);
}
