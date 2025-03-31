package antonfeklichev.java_intensiv_102.repository;

import antonfeklichev.java_intensiv_102.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        // Создаём таблицу
        userRepository.initDatabase();
        userRepository.clearTable();
    }

    @Test
    void testSaveAndFindById() {
        User user = new User("Anton", "anton@example.com");
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId(), "ID должен быть сгенерирован");
        assertEquals("Anton", savedUser.getName());
        assertEquals("anton@example.com", savedUser.getEmail());

        User foundUser = userRepository.findById(savedUser.getId());
        assertNotNull(foundUser);
        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals("Anton", foundUser.getName());
        assertEquals("anton@example.com", foundUser.getEmail());
    }

    @Test
    void testFindAll() {
        // Сохраняем нескольких пользователей
        userRepository.save(new User("Natalia", "natalia@example.com"));
        userRepository.save(new User("Stanislav", "stanislav@example.com"));

        List<User> allUsers = userRepository.findAll();
        assertEquals(2, allUsers.size());
    }

    @Test
    void testUpdate() {
        User user = new User("Roman", "roman@example.com");
        user = userRepository.save(user); // возвращает user с ID

        // Обновляем
        user.setName("Roman Updated");
        user.setEmail("roman.updated@example.com");
        userRepository.update(user);

        User updatedUser = userRepository.findById(user.getId());
        assertNotNull(updatedUser);
        assertEquals("Roman Updated", updatedUser.getName());
        assertEquals("roman.updated@example.com", updatedUser.getEmail());
    }

    @Test
    void testDelete() {
        User user = new User("Alexander", "alexander@example.com");
        user = userRepository.save(user);

        userRepository.delete(user.getId());

        User deletedUser = userRepository.findById(user.getId());
        assertNull(deletedUser, "Пользователь должен быть удалён, и findById вернуть null");
    }
}
