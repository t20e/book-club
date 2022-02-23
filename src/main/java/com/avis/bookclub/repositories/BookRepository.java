package com.avis.bookclub.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avis.bookclub.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{


}
