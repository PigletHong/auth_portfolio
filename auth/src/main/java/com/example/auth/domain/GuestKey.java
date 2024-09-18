package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class GuestKey implements Serializable {
    private String serviceId;
    private String deviceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestKey guestKey = (GuestKey) o;
        return serviceId.equals(guestKey.serviceId) && deviceId.equals(guestKey.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, deviceId);
    }
}
