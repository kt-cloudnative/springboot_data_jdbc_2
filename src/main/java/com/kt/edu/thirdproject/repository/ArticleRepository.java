package com.kt.edu.thirdproject.repository;

import com.kt.edu.thirdproject.domain.Article;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.kt.edu.thirdproject.repository.sql.QueryArticleContSqls;

import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

    @Query(QueryArticleContSqls.RETV_ARTICLES_PAGE)
    List<Article> retvByPage(int pageSize, long offset);

    @Query(QueryArticleContSqls.RETV_ARTICLE_PAGE)
    Article  retvById(Long id);
}
