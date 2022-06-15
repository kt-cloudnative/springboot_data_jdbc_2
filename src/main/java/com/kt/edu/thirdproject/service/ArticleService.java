package com.kt.edu.thirdproject.service;

import com.kt.edu.thirdproject.domain.Article;
import com.kt.edu.thirdproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> retvByPage(int pageSize , int pageNo) {
        PageRequest pageRequest = PageRequest.of((pageNo -1),pageSize);
        log.info("Request to get all Articles");
        return articleRepository.retvByPage(pageRequest.getPageSize(),pageRequest.getOffset()+1 );
    }

    // 플러시를 생략해서 dirty checking등을 하지 않으므로 약간의 성능 향상
    @Transactional(readOnly = true)
    public Article retvById(Long id) {
        log.debug("Request to get Article : {}", id);
        return articleRepository.retvById(id);
    }

}
