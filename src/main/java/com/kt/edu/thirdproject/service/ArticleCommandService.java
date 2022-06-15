package com.kt.edu.thirdproject.service;

import com.kt.edu.thirdproject.domain.ArticleEntity;
import com.kt.edu.thirdproject.exception.ResourceNotFoundException;
import com.kt.edu.thirdproject.repository.ArticleCommandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@Transactional
@Slf4j
public class ArticleCommandService {

    @Autowired
    private ArticleCommandRepository articleCommandRepository;

    public ResponseEntity<ArticleEntity> create(ArticleEntity articleEntity) {
        log.info("Request to create ArticleEntity : " +  articleEntity);
        articleEntity.setId(articleCommandRepository.retvNextVal());
        articleEntity.setNew(true);
        ArticleEntity createdEntity = articleCommandRepository.save(articleEntity);
        return ResponseEntity.ok(createdEntity);
    }

    public ResponseEntity<ArticleEntity> update(ArticleEntity articleEntity) {
        log.info("Request to update ArticleEntity : " +  articleEntity);
        articleEntity.setNew(false);
        ArticleEntity updatedEntity = articleCommandRepository.save(articleEntity);
        return ResponseEntity.ok(updatedEntity);
    }

    public ResponseEntity<ArticleEntity> delete(Long id) {
        log.info("Request to delete ArticleEntity id : " +  id);
        ArticleEntity articleEntity = articleCommandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not exist with id" +id));
        articleCommandRepository.delete(articleEntity);
        return ResponseEntity.ok(articleEntity);
    }
}
