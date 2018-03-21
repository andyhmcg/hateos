package com.amcg.hateos.controller;


import com.amcg.hateos.model.Greeting;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GreetingController {
    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingController.class).putGreeting(name)).withRel("update"));
        greeting.add(linkTo(methodOn(GreetingController.class).deleteGreeting()).withRel("delete").withType("DELETE"));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }



    @PutMapping("/greeting")
    public HttpEntity<Greeting> putGreeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));

        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public HttpEntity<Greeting> deleteGreeting(){

        System.out.println("Delete Greeting");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
