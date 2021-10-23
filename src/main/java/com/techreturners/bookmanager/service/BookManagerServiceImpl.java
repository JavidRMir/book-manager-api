package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookManagerServiceImpl implements BookManagerService {

    BookManagerRepository bookManagerRepository;

    public BookManagerServiceImpl(BookManagerRepository bookManagerRepository) {
        this.bookManagerRepository = bookManagerRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookManagerRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book insertBook(Book book) {
        return bookManagerRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        if (bookManagerRepository.findById(id).isPresent()) {
            return bookManagerRepository.findById(id).get();
        } else {
            throw new IllegalArgumentException("Book Id " + id + ", doesn't exist");
        }
    }

    //User Story 4 - Update Book By Id Solution
    @Override
    public void updateBookById(Long id, Book book) {

        if (bookManagerRepository.findById(id).isPresent()) {

            Book retrievedBook = bookManagerRepository.findById(id).get();

            retrievedBook.setTitle(book.getTitle());
            retrievedBook.setDescription(book.getDescription());
            retrievedBook.setAuthor(book.getAuthor());
            retrievedBook.setGenre(book.getGenre());

            bookManagerRepository.save(retrievedBook);

        } else {
            throw new IllegalArgumentException("Book Id " + id + ", doesn't exist");
        }
    }

    @Override
    public void deleteBookById(Long id) {

        if (bookManagerRepository.findById(id).isPresent()) {
            bookManagerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Book Id " + id + ", doesn't exist");
        }
    }

}
