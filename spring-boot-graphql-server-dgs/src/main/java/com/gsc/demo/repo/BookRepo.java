package com.gsc.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsc.demo.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
	@Query(nativeQuery = true,value = "select * from book where author_id IN (:authorIds)")
	List<Book> findBookByAuthorIds(List<Long> authorIds);
	
	@Query(nativeQuery = true,value = "select * from book where author_id = ?1")
	List<Book> findBookByAuthorId(Long authorId);

}
