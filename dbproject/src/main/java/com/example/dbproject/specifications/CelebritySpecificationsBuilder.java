package com.example.dbproject.specifications;
import com.example.dbproject.model.Celebrity;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class CelebritySpecificationsBuilder {

    private final List<SearchCriteria> params;

    public CelebritySpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public CelebritySpecificationsBuilder with(String key, String operation, Object value, String prefix, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(operation,key, op, value));

        }
        return this;
    }
//        public Specification<Celebrity> build () {
//            if (params.size() == 0) {
//                return null;
//            }
//
//            List<Specification> specs = params.stream().map(CelebritySpecifications::new)
//                    .collect(Collectors.toList());
//
//            Specification result = specs.get(0);
//
//            for (int i = 1; i < params.size(); i++) {
//                result = params.get(i).isOrPredicate() ? Specification.where(result).or(specs.get(i))
//                        : Specification.where(result).and(specs.get(i));
//            }
//            return result;
//        }

        public Specification<Celebrity> build() {
            if (params.size() == 0) {
                return null;
            }

            Specification<Celebrity> result = new CelebritySpecifications(params.get(0));

            for (int i = 1; i < params.size(); i++) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(new CelebritySpecifications(params.get(i)))
                        : Specification.where(result).and(new CelebritySpecifications(params.get(i)));
            }

            return  result;
    }
}