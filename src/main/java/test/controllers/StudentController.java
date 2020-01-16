package test.controllers;

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
import test.DTO.AnswerDTO;
import test.DTO.QuestDTO;
import test.DTO.StudentDTO;
import test.service.FindAnswers;
import test.service.FindQuestion;
import test.service.FindStudent;
import test.service.SaveStudent;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class StudentController {
    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController() {
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("student", "student", new StudentDTO());
    }

@Autowired
    private FindQuestion findQuestion;
@Autowired
    private FindAnswers findAnswers;
@Autowired
    private FindStudent findStudent;
@Autowired
    private SaveStudent save;

    @RequestMapping(value = "/QuestionView", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("student") StudentDTO student, BindingResult result, ModelMap model, HttpServletRequest req) {

        logger.info("student = "+student.getName());
        StudentDTO student1;
        if (findStudent.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(student.getName(),student.getStudentsGroup(),student.getBranch()) == null)
            student1 = save.saveStudent(student);
        else
        student1=findStudent.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(student.getName(),student.getStudentsGroup(),student.getBranch());
       if (student1 !=null)
        logger.info("student = "+student1.getName());
        List<QuestDTO> arrayDTO = new ArrayList<>();
        findQuestion.getRandomQuestions().forEach(  setA -> {

            QuestDTO questDTO = new QuestDTO();
            questDTO.setName(setA.getName());
            questDTO.setQuestionId(setA.getId());

            List<AnswerDTO> answerDTOS = findAnswers.findByQuestionIdLike(setA.getId());
            questDTO.setAnswers(new HashMap<>());
            answerDTOS.forEach(answer -> questDTO.getAnswers().put(answer.getId(),answer.getBody()));

            arrayDTO.add(questDTO);

        } );
        model.addAttribute("quest",arrayDTO);
        if (student1 != null)
        model.addAttribute("student1",student1.getId());
        model.addAttribute("questionCount",arrayDTO.size());
        model.addAttribute("studentName",student.getName());
        if (arrayDTO.size()<10) {
            String error = (String.format("Необходимо ещё %d вопросов", 10-arrayDTO.size()));
            model.addAttribute("error",error);
            return "errorSmallSize";
        }
        return "QuestionView";
    }
}