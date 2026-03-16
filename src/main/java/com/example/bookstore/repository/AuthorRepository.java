package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


 //Создаю репозиторий для работы с авторами.
 //Этот интерфейс позволяет работать с базой данных
 //без написания SQL.

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

     //Находим автора по имени.
     //Возвращаем Optional, потому что автор может не существовать.
    Optional<Author> findByName(String name);

     //Проверяем, существует ли автор с таким именем, чтобы не создавать дубликаты.
    boolean existsByName(String name);
}
