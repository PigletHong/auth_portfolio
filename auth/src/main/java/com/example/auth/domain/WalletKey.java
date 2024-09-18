package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class WalletKey implements Serializable {
    private String serviceId;
    private String network;
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletKey walletKey = (WalletKey) o;
        return serviceId.equals(walletKey.serviceId) && network.equals(walletKey.network) && address.equals(walletKey.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, network, address);
    }
}
