package com.example.bookstore.service;

import com.example.bookstore.dto.request.CreateAuthorRequest;
import com.example.bookstore.entity.Author;
import com.example.bookstore.exception.AuthorNotFoundException;
import com.example.bookstore.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

// статические импорты для удобства
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
// подключаем Mockito к тесту
// это позволяет использовать @Mock и @InjectMocks

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    // создаём mock (поддельный объект) репозитория
    // вместо настоящей базы данных используется mock

    @InjectMocks
    private AuthorService authorService;
    // создаём объект AuthorService
    // Mockito автоматически внедрит в него mock AuthorRepository

    @Test
    void createAuthor() {

        // создаём объект запроса
        // как будто он пришёл из HTTP POST запроса
        CreateAuthorRequest request = new CreateAuthorRequest();
        request.setName("Tolstoy");

        // создаём автора, которого "вернёт" репозиторий
        Author author = new Author(1L, "Tolstoy");

        // говорим Mockito:
        // когда вызывается save(...) → вернуть author
        when(authorRepository.save(any())).thenReturn(author);

        // вызываем метод сервиса
        Author result = authorService.create(request);

        // проверяем результат
        assertEquals("Tolstoy", result.getName());
    }

    @Test
    void findAuthorById() {

        // создаём автора
        Author author = new Author(1L, "Tolstoy");

        // говорим Mockito вернуть автора по id
        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));

        // вызываем сервис
        Author result = authorService.findById(1L);

        // проверяем имя автора
        assertEquals("Tolstoy", result.getName());
    }

    @Test
    void authorNotFound() {

        // Mockito
        // автор не найден
        when(authorRepository.findById(1L))
                .thenReturn(Optional.empty());

        // проверяем что выбрасывается исключение
        assertThrows(
                AuthorNotFoundException.class,
                () -> authorService.findById(1L)
        );
    }

    @Test
    void findAllAuthors() {

        // создаём список авторов
        List<Author> authors = List.of(
                new Author(1L, "Tolstoy"),
                new Author(2L, "Pushkin")
        );

        // Mockito - вернуть список
        when(authorRepository.findAll())
                .thenReturn(authors);

        // вызываем сервис
        List<Author> result = authorService.findAll();

        // проверяем количество авторов
        assertEquals(2, result.size());
    }

    @Test
    void deleteAuthor() {

        // создаём автора
        Author author = new Author(1L, "Tolstoy");

        // Mockito:
        // при поиске выдаёт автора
        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));

        // вызываем метод удаления
        authorService.delete(1L);

        // проверяем что был вызван delete(...)
        verify(authorRepository).delete(author);
    }
}