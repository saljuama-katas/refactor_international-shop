package com.saljuama.katas.refactoring.internationalshop.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
