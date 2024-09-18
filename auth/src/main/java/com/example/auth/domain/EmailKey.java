package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class EmailKey implements Serializable {
    private String serviceId;
    private String emailAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailKey emailKey = (EmailKey) o;
        return serviceId.equals(emailKey.serviceId) &&
                emailAddress.equals(emailKey.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, emailAddress);
    }
}
