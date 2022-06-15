package com.kt.edu.thirdproject.repository;

import com.kt.edu.thirdproject.domain.ArticleEntity;
import com.kt.edu.thirdproject.repository.sql.QueryArticleContSqls;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommandRepository extends CrudRepository<ArticleEntity, Long> {

    @Query(QueryArticleContSqls.RETV_NEXT_VAL)
    Long retvNextVal();

}
