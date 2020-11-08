package platon.dao;

import platon.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    void add(User film);
    void delete(User film);
    void edit(User film);
    User getById(int id);
}

//Data Access Object (DAO) — это такой паттерн проектирования.
// Смысл в том, чтобы создать специальную прослойку,
// которая будет отвечать исключительно за доступ к данным
// (работа с базой данных или другим механизмом хранения).
// В пакете dao создадим интерфейс UserDAO в котором будут такие методы как добавить, удалить и т.д.