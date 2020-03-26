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
import test.Dto.AnswerDto;
import test.Dto.QuestDto;
import test.Dto.StudentDto;
import test.service.*;

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
        return new ModelAndView("student", "student", new StudentDto());
    }

@Autowired
    private FindQuestionInterface findQuestionInterface;
@Autowired
    private FindAnswersInterface findAnswersInterface;
@Autowired
    private FindStudentInterface findStudentInterface;
@Autowired
    private SaveStudentInterface save;
@Autowired
private FindTestInterface findTestInterface;

    @RequestMapping(value = "/selectTest", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("student") StudentDto student, BindingResult result, ModelMap model, HttpServletRequest req) {

        logger.info("student = "+student.getName());
        StudentDto student1;
        if (findStudentInterface.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(student.getName(),student.getStudentsGroup(),student.getBranch()) == null)
            student1 = save.saveStudent(student);
        else
        student1= findStudentInterface.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(student.getName(),student.getStudentsGroup(),student.getBranch());
    /*   if (student1 !=null)
        logger.info("student = "+student1.getName());
        List<QuestDto> arrayDTO = new ArrayList<>();
        findQuestionInterface.getRandomQuestions().forEach(setA -> {

            QuestDto questDTO = new QuestDto();
            questDTO.setName(setA.getName());
            questDTO.setQuestionId(setA.getId());

            List<AnswerDto> answerDtos = findAnswersInterface.findByQuestionIdLike(setA.getId());
            questDTO.setAnswers(new HashMap<>());
            answerDtos.forEach(answer -> questDTO.getAnswers().put(answer.getId(),answer.getBody()));

            arrayDTO.add(questDTO);

        } );


        model.addAttribute("quest",arrayDTO);

     */
        model.addAttribute("tests",findTestInterface.findAll());
        if (student1 != null)
        model.addAttribute("student1",student1.getId());
       // model.addAttribute("questionCount",arrayDTO.size());
     //   model.addAttribute("studentName",student.getName());
        return "selectTest";
    }
}