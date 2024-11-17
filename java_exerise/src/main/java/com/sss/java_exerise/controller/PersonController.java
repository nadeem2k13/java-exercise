package com.sss.java_exerise.controller;


import com.sss.java_exerise.util.Constants;
import com.sss.java_exerise.dto.ApiResponse;
import com.sss.java_exerise.entity.Person;
import com.sss.java_exerise.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonServiceImpl personServiceImpl;


    @GetMapping("/time")
    public ResponseEntity<String> getTime() {
        String url = "http://worldtimeapi.org/api/timezone/Asia/karachi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @GetMapping("/persons")
    public ResponseEntity<ApiResponse> getAllPersons() {
        ApiResponse apiResponse =new ApiResponse(Constants.SUCCESS,1);
        List<Person> list = personServiceImpl.getAllPersons();
        if(list.size()>0) {
            apiResponse.setData(list);
        }else {
            apiResponse.setCode(0);
            apiResponse.setMsg(Constants.NOT_FOUND);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<ApiResponse> getPersonById(@PathVariable Long id) {
        ApiResponse apiResponse =new ApiResponse(Constants.SUCCESS,1);
        Optional<Person> person = personServiceImpl.getPersonById(id);
        if(person.isPresent()) {
            apiResponse.setData(person.get());
        }else {
            apiResponse.setCode(0);
            apiResponse.setMsg(Constants.NOT_FOUND);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<ApiResponse> createPerson(@RequestBody Person person) {
        ApiResponse apiResponse =new ApiResponse(Constants.SUCCESS,1);
        Person createdPerson = personServiceImpl.createPerson(person);
        if(createdPerson!=null) {
            apiResponse.setData(person);
        }else {
            apiResponse.setCode(0);
            apiResponse.setMsg(Constants.NOT_SAVED);
        }
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<ApiResponse> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        ApiResponse apiResponse =new ApiResponse(Constants.SUCCESS,1);
        Person updatedPerson = personServiceImpl.updatePerson(id, person);
        if(updatedPerson!=null) {
            apiResponse.setData(person);
        }else {
            apiResponse.setCode(0);
            apiResponse.setMsg(Constants.NOT_UPDATE);
        }
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<ApiResponse>  deletePerson(@PathVariable Long id) {
        ApiResponse apiResponse =new ApiResponse(Constants.SUCCESS,1);
        Optional<Person> person = personServiceImpl.getPersonById(id);
        if (person.isPresent()) {
            personServiceImpl.deletePerson(id);
            apiResponse.setMsg(Constants.DELETED);
        }else {
            apiResponse.setCode(0);
            apiResponse.setMsg(Constants.NOT_FOUND);
        }

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/persons/search")
    public ResponseEntity<ApiResponse>  searchPersons(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse apiResponse =new ApiResponse(Constants.SUCCESS,1);
        Page<Person> personPage = personServiceImpl.searchPersons(name, age, page, size);
        if(personPage.hasContent()) {
            apiResponse.setData(personPage);
        }else {
            apiResponse.setCode(0);
            apiResponse.setMsg(Constants.NOT_FOUND);
        }
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
