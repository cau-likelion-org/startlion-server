package com.startlion.startlionserver.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.startlion.startlionserver.domain.entity.GraduateInterview;
import com.startlion.startlionserver.domain.enums.IntervieweePart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.startlion.startlionserver.domain.entity.QGraduateInterview.graduateInterview;

@Repository
@RequiredArgsConstructor
public class InterviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<GraduateInterview> findAllByPart(String part) {
        return queryFactory.selectFrom(graduateInterview)
                .where(eqPart(part))
                .fetch();
    }

    BooleanExpression eqPart(String part) {
        if (part == null) {
            return null;
        }

        return graduateInterview.part.eq(IntervieweePart.valueOf(part));
    }
}
