package com.kchmielewski.sda.java.spring01java.data.repository;

import com.kchmielewski.sda.java.spring01java.data.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
        Optional<TeamEntity> findByName(String name);
}
