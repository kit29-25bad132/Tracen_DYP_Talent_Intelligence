package com.tracen.dyp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracen.dyp.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}