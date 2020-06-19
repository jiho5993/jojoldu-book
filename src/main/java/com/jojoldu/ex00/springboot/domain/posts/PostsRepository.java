package com.jojoldu.ex00.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository<Entity, PK>
    // Entity 클래스와 기본 Entity Repository는 함께 위치해야함
}
