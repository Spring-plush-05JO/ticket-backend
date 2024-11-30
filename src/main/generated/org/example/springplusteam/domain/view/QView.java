package org.example.springplusteam.domain.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QView is a Querydsl query type for View
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QView extends EntityPathBase<View> {

    private static final long serialVersionUID = -444822816L;

    public static final QView view = new QView("view");

    public final org.example.springplusteam.common.QBaseEntity _super = new org.example.springplusteam.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QView(String variable) {
        super(View.class, forVariable(variable));
    }

    public QView(Path<? extends View> path) {
        super(path.getType(), path.getMetadata());
    }

    public QView(PathMetadata metadata) {
        super(View.class, metadata);
    }

}

