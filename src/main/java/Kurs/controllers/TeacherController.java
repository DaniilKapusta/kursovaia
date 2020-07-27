package Kurs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import Kurs.Dto.QuestionDto;
import Kurs.service.FindTestInterface;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class TeacherController {

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("teacher", "teacher", null);
    }

    @Autowired
   private FindTestInterface findTestInterface;
    @RequestMapping(value = "/queries", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("queries") QuestionDto quest, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("tests", findTestInterface.findAll());
        return "queries";
    }

}
