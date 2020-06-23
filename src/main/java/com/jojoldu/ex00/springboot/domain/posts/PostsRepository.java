package com.jojoldu.ex00.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository<Entity, PK>
    // Entity 클래스와 기본 Entity Repository는 함께 위치해야함

    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();
}
