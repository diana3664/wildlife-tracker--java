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
            return new ModelAndView(model, "index.vtl");
        }, new HandlebarsTemplateEngine());

        post("/endangered_sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
            String latLong = request.queryParams("latLong");
            Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", Endengered.all());
            String animal = Endengered.find(animalIdSelected).getName();
            model.put("animal", animal);
            return new ModelAndView(model, "success.vtl");
        }, new HandlebarsTemplateEngine());

        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
            String latLong = request.queryParams("latLong");
            Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", Animal.all());
            String animal = Animal.find(animalIdSelected).getName();
            model.put("animal", animal);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, "success.vtl");
        }, new HandlebarsTemplateEngine());


        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", Endengered.all());

            return new ModelAndView(model, "animalForm.vtl");
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
            response.redirect("/");
            return null;
        });


        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal animal = Animal.find(Integer.parseInt(request.params("id")));
            model.put("animal", animal);
            return new ModelAndView(model, "animal.vtl");
        }, new HandlebarsTemplateEngine());

        get("/endangered_animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Endengered endangeredAnimal = Endengered.find(Integer.parseInt(request.params("id")));
            model.put("endangeredAnimal", endangeredAnimal);
            return new ModelAndView(model, "endangered_animal.vtl");
        }, new HandlebarsTemplateEngine());

        get("/error", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "error.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
