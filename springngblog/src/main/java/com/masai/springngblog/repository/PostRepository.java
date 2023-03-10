package com.masai.springngblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.springngblog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
