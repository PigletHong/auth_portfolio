package com.example.auth.repository;

import com.example.auth.domain.Guest;

import java.util.Optional;

public interface GuestRepository {
    Optional<Guest> getGuest(String deviceId);
    void saveGuest(Guest guest);
}
