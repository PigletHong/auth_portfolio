package com.example.auth.repository.impl;

import com.example.auth.domain.Guest;
import com.example.auth.repository.GuestRepository;
import com.example.auth.repository.jpa.GuestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GuestRepositoryImpl implements GuestRepository {
    private final GuestJpaRepository guestJpaRepository;

    @Override
    public Optional<Guest> getGuest(String deviceId) {
        return guestJpaRepository.findById(deviceId);
    }

    @Override
    public void saveGuest(Guest guest) {
        guestJpaRepository.save(guest);
    }
}
