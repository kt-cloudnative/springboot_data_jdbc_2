package com.kt.edu.thirdproject.controller;

import com.kt.edu.thirdproject.domain.Article;
import com.kt.edu.thirdproject.domain.ArticleEntity;
import com.kt.edu.thirdproject.service.ArticleCommandService;
import com.kt.edu.thirdproject.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "posts", description = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleCommandService articleCommandService;

    @Cacheable(cacheNames = "articles")
    @PostMapping
    @Operation(summary ="게시물 전체 조회",description="제목과 내용 전체를 조회 합니다.")
    public List<Article> retvByPage(@RequestBody Article article) {
        return this.articleService.retvByPage(article.getPageSize(),article.getPageNo());
    }

    @Operation(summary ="게시물 단건 조회",description="특정 게시물 단건에 대해서 제목과 내용을 조회 합니다.")
    @GetMapping("/{id}")
    public Article retvById(@PathVariable Long id) {
        return this.articleService.retvById(id);
    }

    @PostMapping("/create")
    @Operation(summary ="게시물 등록",description="게시물을 등록합니다.")
    public ResponseEntity<ArticleEntity> create(@RequestBody ArticleEntity articleEntity) {
        ArticleEntity createdEntity = articleCommandService.create(articleEntity);
        return ResponseEntity.ok(createdEntity);
    }
    /*public ResponseEntity<ArticleEntity> create(@RequestBody ArticleEntity articleEntity) {
        return this.articleCommandService.create(articleEntity);
    }*/

    @PostMapping("/update")
    @Operation(summary ="게시물 수정",description="게시물을 수정합니다.")
    public ResponseEntity<ArticleEntity> update(@RequestBody ArticleEntity articleEntity) {
        ArticleEntity updatedEntity = articleCommandService.update(articleEntity);
        return ResponseEntity.ok(updatedEntity);
    }
    /*public ResponseEntity<ArticleEntity> update(@RequestBody ArticleEntity articleEntity) {
        return this.articleCommandService.update(articleEntity);
    }*/

    @DeleteMapping("/delete/{id}")
    @Operation(summary ="게시물 삭제",description="게시물을 삭제합니다.")
    public ResponseEntity<ArticleEntity> delete(@PathVariable Long id) {
        ArticleEntity deletedEntity = articleCommandService.delete(id);
        return ResponseEntity.ok(deletedEntity);
    }
    /*
    public ResponseEntity<ArticleEntity> delete(@PathVariable Long id) {
        return this.articleCommandService.delete(id);
    }*/
}