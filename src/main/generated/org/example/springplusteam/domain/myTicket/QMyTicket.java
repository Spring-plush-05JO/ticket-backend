package org.example.springplusteam.domain.myTicket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyTicket is a Querydsl query type for MyTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyTicket extends EntityPathBase<MyTicket> {

    private static final long serialVersionUID = -949381696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyTicket myTicket = new QMyTicket("myTicket");

    public final org.example.springplusteam.common.QBaseEntity _super = new org.example.springplusteam.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final org.example.springplusteam.domain.ticket.QTicket ticket;

    public final org.example.springplusteam.domain.user.QUser user;

    public QMyTicket(String variable) {
        this(MyTicket.class, forVariable(variable), INITS);
    }

    public QMyTicket(Path<? extends MyTicket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyTicket(PathMetadata metadata, PathInits inits) {
        this(MyTicket.class, metadata, inits);
    }

    public QMyTicket(Class<? extends MyTicket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ticket = inits.isInitialized("ticket") ? new org.example.springplusteam.domain.ticket.QTicket(forProperty("ticket")) : null;
        this.user = inits.isInitialized("user") ? new org.example.springplusteam.domain.user.QUser(forProperty("user")) : null;
    }

}

