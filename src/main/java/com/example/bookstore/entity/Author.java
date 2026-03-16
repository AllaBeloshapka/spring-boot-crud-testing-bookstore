package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


 //Сущность Author.
 //Этот класс описывает таблицу authors в базе данных.
@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    // уникальный идентификатор автора
    // генерируется автоматически базой данных
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // имя автора
    // поле обязательное (не может быть null)
    @Column(nullable = false)
    private String name;
}