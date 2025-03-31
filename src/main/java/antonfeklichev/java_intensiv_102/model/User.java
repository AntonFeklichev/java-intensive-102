package antonfeklichev.java_intensiv_102.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;

    // конструктор, чтобы создавать новых пользователей без указания id при сохранении в БД
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

