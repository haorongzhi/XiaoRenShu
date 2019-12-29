package com.hrz.controller;

import com.hrz.entity.Person;
import com.hrz.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/getAll")
    public void getAll(){

    }

    @RequestMapping("/save")
    public void save(){

    }

    @RequestMapping("/find")
    @ResponseBody
    public Person find(@RequestParam("id") String id){
        Optional<Person> p1 = personRepository.findById(Long.valueOf(id));
        if(p1.isPresent()){
            return  p1.get();
        }
        return null;
    }
}
