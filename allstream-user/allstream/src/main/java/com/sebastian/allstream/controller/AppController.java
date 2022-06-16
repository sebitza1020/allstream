package com.sebastian.allstream.controller;

import com.sebastian.allstream.model.DAO.Language;
import com.sebastian.allstream.model.DAO.User;
import com.sebastian.allstream.repository.LanguageRepository;
import com.sebastian.allstream.repository.UserRepository;
import com.sebastian.allstream.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageService languageService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/process_register")
    public String signUpProcessor(User user) throws Exception {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(user.getPassword());
        user.setPassword(password);
        user.setLanguage(languageService.findLanguageById(1));
        userRepository.save(user);
        return "success_signup";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/languages")
    public String getAllLanguages(Model model) {
        List<Language> languages = languageService.getAllLanguages();
        model.addAttribute("languages", languages);
        return "languages";
    }

    @RequestMapping("/new_language")
    public String addNewLanguagePage(Model model) {
        Language language = new Language();
        model.addAttribute("language", language);
        return "new_language";
    }

    @RequestMapping( value = "/add_language", method = RequestMethod.POST)
    public String saveLanguage(@ModelAttribute("language") Language language) throws Exception {
        languageService.saveLanguage(language);
        return "redirect:/languages";
    }

    @RequestMapping("edit/{language_id}")
    public ModelAndView editLanguagePage(@PathVariable(name = "language_id") int id) throws Exception {
        ModelAndView mv = new ModelAndView("edit_language");
        Language language = languageService.findLanguageById(id);
        mv.addObject("language", language);
        return mv;
    }

    @RequestMapping("delete/{language_id}")
    public String deleteProduct(@PathVariable(name = "language_id") int id) throws Exception {
        languageService.deleteLanguageById(id);
        return "redirect:/languages";
    }
}
