package info.cheremisin.jokeapp.controllers;

import info.cheremisin.jokeapp.services.JokesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokeController {

    private JokesService jokesService;

    public JokeController(JokesService jokesService) {
        this.jokesService = jokesService;
    }

    @RequestMapping({"", "/"})
    public String showJoker(Model model) {
        model.addAttribute("joke", jokesService.getJoke());
        return "joke";
    }
}
