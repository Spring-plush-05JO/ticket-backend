package org.example.springplusteam.domain.onlinestore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "online_stores")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OnlineStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "dns_name", length = 1000)
    private String dnsName;
    private String address;
    private String phoneNumber;
    private String adminEmail;

    @Builder
    public OnlineStore(Long id, String name, String dnsName, String address, String phoneNumber, String adminEmail) {
        this.id = id;
        this.name = name;
        this.dnsName = dnsName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.adminEmail = adminEmail;
    }
}
