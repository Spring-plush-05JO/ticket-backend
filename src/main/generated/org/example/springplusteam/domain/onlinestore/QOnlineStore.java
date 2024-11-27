package org.example.springplusteam.domain.onlinestore;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOnlineStore is a Querydsl query type for OnlineStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOnlineStore extends EntityPathBase<OnlineStore> {

    private static final long serialVersionUID = -677739104L;

    public static final QOnlineStore onlineStore = new QOnlineStore("onlineStore");

    public final StringPath address = createString("address");

    public final StringPath adminEmail = createString("adminEmail");

    public final StringPath dnsName = createString("dnsName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QOnlineStore(String variable) {
        super(OnlineStore.class, forVariable(variable));
    }

    public QOnlineStore(Path<? extends OnlineStore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOnlineStore(PathMetadata metadata) {
        super(OnlineStore.class, metadata);
    }

}

