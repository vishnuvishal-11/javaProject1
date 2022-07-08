package com.example.dbproject.specifications;

import com.example.dbproject.model.Celebrity;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class CelebritySpecifications implements Specification<Celebrity> {
    public CelebritySpecifications(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Celebrity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.<String>get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.<String>get(
                        criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }
}