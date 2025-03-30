package antonfeklichev.java_intensiv_102.service;

import antonfeklichev.java_intensiv_102.model.User;
import antonfeklichev.java_intensiv_102.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        // проверка, что у пользователя есть имя
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        // передаем сохранение в репозиторий
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}

