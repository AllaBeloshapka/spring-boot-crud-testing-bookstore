package com.example.bookstore.service;

import com.example.bookstore.dto.request.CreateAuthorRequest;
import com.example.bookstore.entity.Author;
import com.example.bookstore.exception.AuthorNotFoundException;
import com.example.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


 //Сервис для работы с авторами.
 //Здесь находится бизнес-логика приложения.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {

    // репозиторий для работы с таблицей authors
    private final AuthorRepository authorRepository;

    /*
     Получить список всех авторов
    */
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

     //Найти автора по id
     //Если автор не найден — выбрасываем исключение
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() ->
                        new AuthorNotFoundException("Автор с id " + id + " не найден"));
    }

     //Создать нового автора
    @Transactional
    public Author create(CreateAuthorRequest request) {

        Author author = new Author();

        // записываем имя автора
        author.setName(request.getName());

        // сохраняем автора в базе
        return authorRepository.save(author);
    }

     //Обновить данные автора
    @Transactional
    public Author update(Long id, CreateAuthorRequest request) {

        // сначала находим автора
        Author author = findById(id);

        // обновляем имя
        author.setName(request.getName());

        // сохраняем изменения
        return authorRepository.save(author);
    }

     //Удалить автора
    @Transactional
    public void delete(Long id) {

        Author author = findById(id);

        authorRepository.delete(author);
    }
}
