package platon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //сообщает Spring что данный класс является конфигурационным и содержит определения и зависимости
@EnableWebMvc //позволяет импортировать конфигурацию Spring MVC из класса WebMvcConfigurationSupport
//сообщает Spring где искать компоненты, которыми он должен управлять, т.е. классы, помеченные аннотацией
// @Component или ее производными, такими как @Controller, @Repository, @Service.
//  Эти аннотации автоматически определяют бин класс
@ComponentScan(basePackages = "platon")
public class WebConfig {

    //В методе viewResolver() мы создаем его реализацию и определяем где именно
    // искать представления в webapp. Поэтому когда в методе контроллера мы устанавливали имя
    // "users" представление найдется как "/pages/users.jsp"
    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}