package com.kchmielewski.sda.java.spring01java.config;

import com.kchmielewski.sda.java.spring01java.data.repository.PlayerRepository;
import com.kchmielewski.sda.java.spring01java.service.PlayerService;
import com.kchmielewski.sda.java.spring01java.web.PlayerController;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;
import java.util.Random;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackageClasses = {PlayerService.class, PlayerController.class})
@Import(SqlConfig.class)
public class Config implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new
                ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/WEB-INF/classes/Players");

        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new
                LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.UK);
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    /*@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public Integer randomInteger(ApplicationContext context) {
        System.out.println(context.getBean(PlayerRepository.class) == context.getBean(PlayerRepository.class));
        System.out.println(context.getBean(PlayerRepository.class) == context.getBean(PlayerRepository.class));
        System.out.println(context.getBean(PlayerRepository.class) == context.getBean(PlayerRepository.class));
        return new Random().nextInt();

    }

    @Bean
    public Integer randomInteger2(ApplicationContext context){
        System.out.println(context.getBean(PlayerRepository.class) == context.getBean(PlayerRepository.class));
        System.out.println(context.getBean(PlayerRepository.class) == context.getBean(PlayerRepository.class));
        System.out.println(context.getBean(PlayerRepository.class) == context.getBean(PlayerRepository.class));

        return new Random().nextInt();
    }*/




}
