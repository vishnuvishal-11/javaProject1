package com.example.dbproject.repository;

import com.example.dbproject.model.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity,Long>, JpaSpecificationExecutor<Celebrity> {
//    @Query(value = "SELECT CAST (celebrity_id as varchar),Optional[celebrity_name], about, birth_place, debut, dob,fb_id,gender,insta_id,languages_known,marital_status, qualification,tags FROM celebrity WHERE celebrity_id :=celebrity_id OR Optional[celebrity_name] :=celebrity_name OR tags LIKE %:tags% ;",nativeQuery = true)
//    @Modifying
//    List<Celebrity> getAllList(@Param(value = "celebrity_name") String celebrity_name,@Param(value = "celebrity_id") String celebrity_id,@Param(value = "tags") String tags);
    @Query(value = "SELECT CAST(celebrity_id as varchar), about, birth_place, debut, dob, fb_id, gender, insta_id, languages_known, marital_status, celebrity_name, qualification, tags from celebrity where celebrity.celebrity_name =:celebrity_name",nativeQuery = true)
    List<Celebrity> findByCelebrity_name(@Param(value = "celebrity_name") String celebrity_name);
//    @Query(value = "SELECT CAST(celebrity_id as varchar), about, birth_place, debut, dob, fb_id, gender, insta_id, languages_known, marital_status, celebrity_name, qualification, tags from celebrity where celebrity.tags::text LIKE'[%:tags%"]'",nativeQuery = true)
//    List<Celebrity> getReferencesByTags(@Param(value = "tags")String tags);

}
