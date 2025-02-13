package com.csc340.sp25_rest_api_demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RestApiController {
    Map<Integer, Student> studentDatabase = new HashMap<>();

    /**
     * Hello World API endpoint.
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    /**
     * Greeting API endpoint.
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "Dora") String name) {
        return "Hola, soy " + name;
    }

    /**
     * List all students.
     */
    @GetMapping("students/all")
    public Object getAllStudents() {
        if (studentDatabase.isEmpty()) {
            studentDatabase.put(1, new Student(1, "sample1", "csc", 3.86));
        }
        return studentDatabase.values();
    }

    /**
     * Get one student by Id.
     */
    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentDatabase.get(id);
    }

    /**
     * Create a new Student entry.
     */
    @PostMapping("students/create")
    public Object createStudent(@RequestBody Student student) {
        studentDatabase.put(student.getId(), student);
        return studentDatabase.values();
    }

    /**
     * Delete a Student by id.
     */
    @DeleteMapping("students/delete/{id}")
    public Object deleteStudent(@PathVariable int id) {
        studentDatabase.remove(id);
        return studentDatabase.values();
    }

    /**
     * Get a list of universities from Hipolabs API.
     */
    @GetMapping("/univ")
    public Object getUniversities() {
        try {
            String url = "http://universities.hipolabs.com/search?name=sports";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            for (JsonNode rt : root) {
                String name = rt.get("name").asText();
                String country = rt.get("country").asText();
                System.out.println(name + ": " + country);
            }

            return root;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /univ";
        }
    }

    /**
     * Get a list of 15 GSO breweries from OpenBreweryDB API.
     */
    @GetMapping("/brew")
    public Object getBreweries() {
        try {
            String url = "https://api.openbrewerydb.org/v1/breweries?by_city=greensboro";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            List<Brewery> breweryList = new ArrayList<>();

            for (JsonNode rt : root) {
                String name = rt.get("name").asText();
                String address = rt.has("address_1") ? rt.get("address_1").asText() : "N/A";
                String type = rt.get("brewery_type").asText();

                Brewery brewery = new Brewery(name, address, type);
                breweryList.add(brewery);
            }
            return breweryList;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /brew";
        }
    }

    /**
     * Get info about a fruit from FruityVice API.
     */
    @GetMapping("/fruit")
    public Object getFruit(@RequestParam(value = "name", defaultValue = "kiwi") String fruitName) {
        try {
            String url = "https://www.fruityvice.com/api/fruit/" + fruitName;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            Fruit fruit = new Fruit(root.get("name").asText(), root.get("family").asText(),
                    root.get("nutritions").get("calories").asDouble());

            return fruit;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /fruit";
        }
    }
}

@RestController
@RequestMapping("/dogs")
class DogController {

    @GetMapping
    public List<Dog> getAllDogs() {
        return Arrays.asList(
                new Dog(1, "Labrador Retriever", "Companion", "10-12 years", "Friendly, Energetic"),
                new Dog(2, "German Shepherd", "Herding", "9-13 years", "Intelligent, Loyal")
        );
    }
}