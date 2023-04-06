package com.inditex.microservice.specification;

import com.inditex.microservice.model.PriceZone;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

@AllArgsConstructor
@Slf4j
public class PriceZoneSpecification implements Specification<PriceZone> {

    private final PriceZoneCriteria criteria;

    private static final String START_DATE_FIELD_NAME = "startDate";
    private static final String END_DATE_FIELD_NAME = "endDate";
    private static final String PRODUCT_ID_FIELD_NAME = "productId";
    private static final String BRAND_ID_FIELD_NAME = "brandId";

    @Override
    public Predicate toPredicate(Root<PriceZone> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        addIntegerPredicate(predicates, root, criteria.getProductId(), PRODUCT_ID_FIELD_NAME);
        addIntegerPredicate(predicates, root, criteria.getBrandId(), BRAND_ID_FIELD_NAME);
        addDatePredicate(predicates, root, criteriaBuilder, criteria.getExecutionDate());

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private static void addIntegerPredicate(final List<Predicate> predicates, final Root<PriceZone> root, final Integer field,
        final String fieldName) {
        if (field != null) {
            final Expression<String> integerExpression = root.get(fieldName);
            predicates.add(integerExpression.in(field));
        }
    }

    private static void addDatePredicate(final List<Predicate> predicates, final Root<PriceZone> root,
        final CriteriaBuilder criteriaBuilder, final LocalDateTime executionDate) {

        if (!ObjectUtils.isEmpty(executionDate)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(END_DATE_FIELD_NAME), executionDate));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(START_DATE_FIELD_NAME), executionDate));
        }
    }
}
