package antonfeklichev.java_intensiv_102.service;

import antonfeklichev.java_intensiv_102.model.User;
import antonfeklichev.java_intensiv_102.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser_Success() {
        User userToSave = new User("Alice", "alice@example.com");
        User savedUser = new User(1L, "Alice", "alice@example.com");

        // Настраиваем мок: при вызове userRepository.save(...) вернуть savedUser
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.save(userToSave);

        // Проверяем, что вернулся ожидаемый пользователь
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());

        // Проверяем, что метод save в репозитории был вызван ровно один раз
        verify(userRepository, times(1)).save(userToSave);
    }

    @Test
    void testSaveUser_EmptyName_ThrowsException() {
        User user = new User("", "test@example.com");

        // Ожидаем, что при попытке сохранить без имени выбросится исключение
        assertThrows(IllegalArgumentException.class, () -> userService.save(user));

        // Убеждаемся, что репозиторий вовсе не вызывался
        verify(userRepository, never()).save(any());
    }

    @Test
    void testFindById() {
        User user = new User(1L, "Sergey", "sergey@example.com");
        when(userRepository.findById(1L)).thenReturn(user);

        User result = userService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Sergey", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void testDeleteUser() {
        // Проверяем, что метод delete вызывается
        userService.delete(1L);
        verify(userRepository, times(1)).delete(1L);
    }
}
