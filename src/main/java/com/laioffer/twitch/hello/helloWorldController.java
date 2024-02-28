package com.laioffer.twitch.hello;

import net.datafaker.Faker;
import net.datafaker.providers.base.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class helloWorldController {
    public record Book(
            String  title,
            String author
    ){
    }
    public record Address(
            String street,
            String city,
            String state,
            String country
    ){}
    public record Person(
        String name,
        String company,
        Address homeAddress,
        Book favoriteBook

    ){}
    @GetMapping("/hello")
    public String sayHello(){
        return "hello world!" ;

    }
    @GetMapping("/faker")
    //
    public Person[] testFaker(@RequestParam(required = false)String locale){
        if(locale==null){
            locale = "en_US";
        }
        Person[] temp = new Person[2];
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        String company = faker.company().name();
        String street = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String bookTitle = faker.book().title();
        String bookAuthor = faker.book().author();
        String template = "This is %s. I work at %s. I live at %s in %s. My favorite book is %s by %s";
        temp[1] = new Person(name,company,new Address(street,city,state,null),new Book(bookTitle,bookAuthor));
        temp[0] = new Person(name,company,new Address(street,city,state,null),new Book(bookTitle,bookAuthor));
        return temp;
    }

    @GetMapping("/123123")
    public String say123123(){
        return "123123" ;
    }


}
