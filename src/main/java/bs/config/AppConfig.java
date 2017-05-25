package bs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Gotus on 22.05.2017.
 */

//настроить ViewResolver

@Configuration
@EnableWebMvc
@ComponentScan("bs")
public class AppConfig extends WebMvcConfigurerAdapter{
    private final Environment env;
    @Autowired
    public AppConfig(Environment env){

        this.env = env;
    }

    public void addViewControllers(ViewControllerRegistry registry){

       registry.addRedirectViewController("/BattleShipOnline/","/startpage.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}