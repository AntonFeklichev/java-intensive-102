package antonfeklichev.java_intensiv_102.repository;

import antonfeklichev.java_intensiv_102.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // URL H2 базы данных. DB_CLOSE_DELAY=-1 означает,
    // что БД не будет уничтожена сразу по закрытию соединений.
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public UserRepository() {
    }

    /**
     * Создаёт таблицу USERS.
     * Вызывается в тестах до начала каждого метода, чтобы БД была чистая.
     */
    public void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
                     "  ID IDENTITY PRIMARY KEY," +
                     "  NAME VARCHAR(255) NOT NULL," +
                     "  EMAIL VARCHAR(255) NOT NULL" +
                     ")";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы Users", e);
        }
    }

    /**
     * Очищает таблицу USERS.
     */
    public void clearTable() {
        String sql = "TRUNCATE TABLE USERS";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при очистке таблицы Users", e);
        }
    }

    /**
     * Сохраняет нового пользователя в БД и возвращает его с установленным ID.
     */
    public User save(User user) {
        String sql = "INSERT INTO USERS (NAME, EMAIL) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Не удалось создать пользователя, строки таблицы не изменены");
            }

            // Получаем сгенерированный ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Не удалось создать пользователя, id не получен");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка сохранения пользователя", e);
        }
        return user;
    }

    /**
     * Ищет пользователя по ID.
     */
    public User findById(Long id) {
        String sql = "SELECT ID, NAME, EMAIL FROM USERS WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Long userId = rs.getLong("ID");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    return new User(userId, name, email);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска пользователя по ID", e);
        }
        return null; // Если не нашёл, возвращаем null
    }

    /**
     * Возвращает всех пользователей из таблицы.
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID, NAME, EMAIL FROM USERS";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                users.add(new User(id, name, email));
            }
        } catch (SQLException e) {
                throw new RuntimeException("Ошибка поиска всех пользователей", e);
        }
        return users;
    }

    /**
     * Обновляет данные пользователя.
     */
    public void update(User user) {
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления пользователя", e);
        }
    }

    /**
     * Удаляет пользователя по ID.
     */
    public void delete(Long id) {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления пользователя", e);
        }
    }
}

