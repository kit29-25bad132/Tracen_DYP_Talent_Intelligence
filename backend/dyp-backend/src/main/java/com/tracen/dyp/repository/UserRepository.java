package com.tracen.dyp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracen.dyp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}