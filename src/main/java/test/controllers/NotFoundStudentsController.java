package test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotFoundStudentsController {
    @RequestMapping(value = "/notFoundStudents", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("notFoundStudents", "notFoundStudents", null);
    }
}
