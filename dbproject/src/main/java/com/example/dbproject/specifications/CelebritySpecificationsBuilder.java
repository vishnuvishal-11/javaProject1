package com.example.dbproject.specifications;
import com.example.dbproject.model.Celebrity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class CelebritySpecificationsBuilder {

    private final List<SearchCriteria> params;

    public CelebritySpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
    public final CelebritySpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public CelebritySpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }
        public Specification<Celebrity> build() {
            if (params.size() == 0) {
                return null;
            }

            Specification<Celebrity> result = new CelebritySpecifications(params.get(0));
            log.info("in builder op4 " +params.get(0));
            for (int i = 1; i < params.size(); i++) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(new CelebritySpecifications(params.get(i)))
                        : Specification.where(result).and(new CelebritySpecifications(params.get(i)));
            }
            log.info("in builder result 5" +result);
            return  result;
    }
    public final CelebritySpecificationsBuilder with(CelebritySpecifications spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final CelebritySpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}