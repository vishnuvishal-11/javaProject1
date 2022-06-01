package Redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import security.AccessList;


@Repository
public interface AccessListRepository extends CrudRepository<AccessList,String> {

}
