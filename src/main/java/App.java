import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", Endengered.all());
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", Endengered.all());

            return new ModelAndView(model, "animalForm.hbs");
        }, new HandlebarsTemplateEngine());


        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            boolean endangered = request.queryParamsValues("endangered") != null;
            if (endangered) {
                String name = request.queryParams("name");
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                Endengered endangeredAnimal = new Endengered(name, health, age);
                endangeredAnimal.save();
                model.put("animals", Animal.all());
                model.put("endangeredAnimals", Endengered.all());
            } else {
                String name = request.queryParams("name");
                Animal animal = new Animal(name);
                animal.save();
                model.put("animals", Animal.all());
                model.put("endangeredAnimals", Endengered.all());

            }
            response.redirect("/records.hbs");
            return null;
        });
    }
    }
