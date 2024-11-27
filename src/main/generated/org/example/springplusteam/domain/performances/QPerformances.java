package org.example.springplusteam.domain.performances;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerformances is a Querydsl query type for Performances
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformances extends EntityPathBase<Performances> {

    private static final long serialVersionUID = -1832264032L;

    public static final QPerformances performances = new QPerformances("performances");

    public final StringPath endData = createString("endData");

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mainAddress = createString("mainAddress");

    public final StringPath name = createString("name");

    public final StringPath place = createString("place");

    public final StringPath startData = createString("startData");

    public final StringPath subAddress = createString("subAddress");

    public QPerformances(String variable) {
        super(Performances.class, forVariable(variable));
    }

    public QPerformances(Path<? extends Performances> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerformances(PathMetadata metadata) {
        super(Performances.class, metadata);
    }

}

