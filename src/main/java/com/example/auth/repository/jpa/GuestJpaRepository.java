package com.example.auth.repository.jpa;

import com.example.auth.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestJpaRepository extends JpaRepository<Guest, String> {
}
