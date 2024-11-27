package org.example.springplusteam.domain.myCoupon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyCoupon is a Querydsl query type for MyCoupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyCoupon extends EntityPathBase<MyCoupon> {

    private static final long serialVersionUID = 21607168L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyCoupon myCoupon = new QMyCoupon("myCoupon");

    public final org.example.springplusteam.common.QBaseEntity _super = new org.example.springplusteam.common.QBaseEntity(this);

    public final org.example.springplusteam.domain.coupon.QCoupon coupon;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final org.example.springplusteam.domain.user.QUser user;

    public QMyCoupon(String variable) {
        this(MyCoupon.class, forVariable(variable), INITS);
    }

    public QMyCoupon(Path<? extends MyCoupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyCoupon(PathMetadata metadata, PathInits inits) {
        this(MyCoupon.class, metadata, inits);
    }

    public QMyCoupon(Class<? extends MyCoupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new org.example.springplusteam.domain.coupon.QCoupon(forProperty("coupon")) : null;
        this.user = inits.isInitialized("user") ? new org.example.springplusteam.domain.user.QUser(forProperty("user")) : null;
    }

}

