package com.example.bookstore.exception;

// Исключение, которое выбрасывается,
// когда автор не найден в базе данных
public class AuthorNotFoundException extends RuntimeException {

    // конструктор принимает сообщение об ошибке
    // и передаёт его в родительский класс RuntimeException
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
