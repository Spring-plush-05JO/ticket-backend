package org.example.springplusteam.dto.onlinestore;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.example.springplusteam.domain.onlinestore.OnlineStore;

@Getter
@Setter
public class ShopInfoDto {

    @CsvBindByName(column = "쇼핑몰명")
    private String name;

    @CsvBindByName(column = "도메인명")
    private String dnsName;

    @CsvBindByName(column = "회사주소")
    private String address;

    @CsvBindByName(column = "전화번호")
    private String phoneNumber;

    @CsvBindByName(column = "운영자이메일")
    private String adminEmail;

    public static OnlineStore from(ShopInfoDto dto) {
        return OnlineStore.builder()
                .name(dto.getName())
                .dnsName(dto.getDnsName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .adminEmail(dto.getAdminEmail())
                .build();
    }
}
