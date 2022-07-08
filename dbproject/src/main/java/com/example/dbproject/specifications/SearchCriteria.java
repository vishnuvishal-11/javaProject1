package com.example.dbproject.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria( String orPredicate,String key, SearchOperation operation, Object value) {
        super();

        this.orPredicate
                = orPredicate != null
                && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);

        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    private boolean orPredicate;

    public boolean isOrPredicate() {
        return orPredicate;
    }

}
