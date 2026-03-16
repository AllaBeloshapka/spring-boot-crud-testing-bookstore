package com.example.bookstore.dto.request;

import jakarta.validation.constraints.*; // аннотации для проверки входящих данных
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для создания автора
// этот объект приходит в теле HTTP запроса
@Data // Lombok автоматически создаёт геттеры, сеттеры, toString
@NoArgsConstructor // пустой конструктор
@AllArgsConstructor // конструктор со всеми полями
public class CreateAuthorRequest {

    // имя автора
    // NotBlank проверяет, чтобы строка не была пустой
    @NotBlank(message = "Имя обязательно")

    // ограничение длины имени
    @Size(min = 2, max = 50)
    private String name;

}
