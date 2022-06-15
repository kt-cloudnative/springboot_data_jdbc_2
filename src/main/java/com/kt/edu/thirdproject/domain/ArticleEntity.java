package com.kt.edu.thirdproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.concurrent.atomic.LongAdder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("ARTICLE")
@Schema(description = "게시물 Entity")
//json 에서 해당 값 제외
@JsonIgnoreProperties({"new"})
public class ArticleEntity implements Persistable<Long> {
    @Id
    @Schema(description = "순서")
    private Long id;
    @Schema(description = "게시물 제목")
    private String title;
    @Schema(description = "게시물 내용")
    private String content;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() { return isNew; }

    @Override
    public Long getId(){
        return id;
    }

    public static ArticleEntity of(Article article) throws Exception {
        return ArticleEntity.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
