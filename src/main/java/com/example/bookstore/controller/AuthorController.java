package com.example.bookstore.controller;

import com.example.bookstore.dto.request.CreateAuthorRequest;
import com.example.bookstore.entity.Author;
import com.example.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


 //Контроллер для работы с авторами.
 //Здесь я принимаю HTTP-запросы и передаю их в сервис.
// Сервис уже работает с бизнес-логикой и базой данных.

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    // сервис, который содержит бизнес-логику для авторов
    private final AuthorService authorService;


     //Получить список всех авторов.
     //Запрос: GET /api/authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {

        // прошу сервис вернуть всех авторов
        List<Author> authors = authorService.findAll();

        // возвращаю список и статус 200
        return ResponseEntity.ok(authors);
    }

     //Получить одного автора по id.
     //Запрос: GET /api/authors/{id}

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {

        // прошу сервис найти автора по id
        Author author = authorService.findById(id);

        // возвращаю найденного автора
        return ResponseEntity.ok(author);
    }

     //Создать нового автора.
     //Запрос: POST /api/authors
    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody CreateAuthorRequest request) {

        // передаю данные в сервис для создания автора
        Author author = authorService.create(request);

        // возвращаю созданный объект и статус 201
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }


     //Обновить автора по id.
     //Запрос: PUT /api/authors/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody CreateAuthorRequest request) {

        // передаю id и новые данные в сервис
        Author author = authorService.update(id, request);

        // возвращаю обновлённого автора
        return ResponseEntity.ok(author);
    }

     //Удалить автора по id.
     //Запрос: DELETE /api/authors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        // прошу сервис удалить автора
        authorService.delete(id);

        // возвращаю статус 204 (успешно удалено)
        return ResponseEntity.noContent().build();
    }
}