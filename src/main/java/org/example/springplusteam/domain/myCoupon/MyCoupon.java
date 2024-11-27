package org.example.springplusteam.domain.myCoupon;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.springplusteam.common.BaseEntity;
import org.example.springplusteam.domain.coupon.Coupon;
import org.example.springplusteam.domain.user.User;

@Entity
@Table(name = "my_coupons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyCoupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public MyCoupon(User user, Coupon coupon) {
        this.user = user;
        this.coupon = coupon;
    }
}
