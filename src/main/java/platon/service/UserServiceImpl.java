package platon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import platon.dao.UserDAO;
import platon.dao.UserDAOImpl;
import platon.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    private  UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional //аннотацией @Transactional, которая укажет на то, что метод должен выполняться в транзакции
    // (без этого Hibernate работать откажется)
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }
}
