package com.gsc.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsc.demo.domain.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

}
