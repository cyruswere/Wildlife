import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("username", request.session().attribute("username"));

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/login", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String inputtedUsername = request.queryParams("username");
            request.session().attribute("username", inputtedUsername);
            model.put("username", inputtedUsername);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<CommonAnimal> allCommonAnimals = CommonAnimal.all();

            List<EndangeredAnimal> allEndangeredAnimals = EndangeredAnimal.all();

            model.put("allEndangeredAnimals", allEndangeredAnimals);

            model.put("allCommonAnimals", allCommonAnimals);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/common/new", (request, response) -> {

            String location = request.queryParams("location");
            String name = request.queryParams("name");

            String rangerName = request.queryParams("rangerName");

            CommonAnimal newCommonAnimal = new CommonAnimal(name);

            newCommonAnimal.saveCommonAnimal();
            int animalId = newCommonAnimal.getId();
            Sighting newSighting = new Sighting(animalId, location, rangerName);
            newSighting.save();
            response.redirect("/sightings");
            return null;
        }, new HandlebarsTemplateEngine());

        post("/sightings/endangered/new", (request, response) -> {
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String rangerName = request.queryParams("rangerName");
            String location = request.queryParams("location");

            EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name, health, age);

            newEndangeredAnimal.saveEndangeredAnimal();

            int animalId  = newEndangeredAnimal.getId();

            Sighting newSighting = new Sighting(animalId, location, rangerName);
            newSighting.save();

            response.redirect("/sightings");
            return null;
    }, new HandlebarsTemplateEngine());

    get("/sighting/:id", (request, response) -> {

        Map<String, Object> model = new HashMap<>();

        Sighting thisSighting = Sighting.find(Integer.parseInt(request.params("id")));

        model.put("thisSighting", thisSighting);

        return new ModelAndView(model, "sighting-detail.hbs");
    }, new HandlebarsTemplateEngine());

}
}

