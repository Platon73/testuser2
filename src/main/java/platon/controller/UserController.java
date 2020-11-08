package platon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import platon.model.User;
import platon.service.UserService;
import platon.service.UserServiceImpl;

import java.util.List;

//класс, который будет обрабатывать запросы, т.е. контроллер
@Controller
public class UserController {

    private UserService userService ;

    //(автосвязывание) сообщает Spring о том, что он должен
    // покопаться у себя в контексте и подставить сюда подходящий бин
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allUser() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("userslist", users);
        return modelAndView;
    }

    //методом, который будет возвращать страницу редактирования конкретного user:
    //аннотация @PathVariable. Она указывает на то, что данный параметр (int id)
    // получается из адресной строки. Чтобы указать место этого параметра в адресной
    // строке используется конструкция {id} (кстати, если имя переменной совпадает,
    // как в данном случае, то в скобках это можно не указывать, а написать просто @PathVariable int id).
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", userService.getById(id));
        return modelAndView;
    }

    //метод для получения страницы редактирования, теперь нужен метод для самого редактирования:
    //Метод запроса POST потому что здесь мы будем передавать данные. "redirect:/" означает,
    // что после выполнения данного метода мы будем перенаправлены на адрес "/", т.е. запустится метод
    // allUsers и мы вернемся на главную страницу.
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.edit(user);
        return modelAndView;
    }

    //Метод для получения страницы чтобы добавить user (используем ту ж страницу что и для редактирования)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    //метод для добавления user
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("user") User User) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.add(User);
        return modelAndView;
    }

    //метод контроллера для удаления user из списка
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }
}