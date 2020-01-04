package test;

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
import test.entity.AnswersEntity;
import test.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("student", "student", new Student());
    }
/*
   @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("student") Student student, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        logger.info("student name " + student.getName());
        model.addAttribute("name", student.getName());
        model.addAttribute("group", student.getStudentsGroup());
        model.addAttribute("branch", student.getBranch());
        return "QuestionView";
    }
*/

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswersDAO answersDAO;

    @Autowired
    private StudentRepository studentRepository;

    //@ResponseBody
    @RequestMapping(value = "/QuestionView", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("student") Student student, BindingResult result, ModelMap model, HttpServletRequest req) {
       /* if (result.hasErrors()) {
            return "error";
        } */
        Student student1 = new Student();
        if (studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(student.getName(),student.getStudentsGroup(),student.getBranch())==null) {
        student1 = studentRepository.save(student);
        }
                else
                    student1=studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(student.getName(),student.getStudentsGroup(),student.getBranch());
        logger.info(student.getName());

        List<QuestDTO> arrayDTO = new ArrayList<>();
        questionRepository.getRandomQueries().forEach(  setA -> {
          // arrayAE.addAll(answersDAO.findByQuestionIdLike(setA.getId()));
            QuestDTO questDTO = new QuestDTO();
            questDTO.setName(setA.getName());
            questDTO.setQuestionId(setA.getId());
           // questDTO.setAnswers(answersDAO.findByQuestionIdLike(setA.getId()));
            List<AnswersEntity> answersEntities = answersDAO.findByQuestionIdLike(setA.getId());
            questDTO.setAnswers(new HashMap<>());
            answersEntities.forEach(answer ->{
                questDTO.getAnswers().put(answer.getId(),answer.getBody());
            });

            arrayDTO.add(questDTO);

        } );
        model.addAttribute("quest",arrayDTO);
        model.addAttribute("student1",student1.getId());
        model.addAttribute("questionCount",arrayDTO.size());
        model.addAttribute("studentName",student.getName());
        if (arrayDTO.size()<10) {
            String error = (String.format("Необходимо ещё %d вопросов", 10-arrayDTO.size()));
            model.addAttribute("error",error);
            return "errorSmallSize";
        }

        /*
        Iterable<AnswersEntity> allAnswers = arrayAE;

      //  Iterable<AnswersEntity> allAnswers = answersDAO.findAll();
        ArrayList<QuestDTO> arrayDTO = new ArrayList<>();
        for (QuestEntity entity : all ) {
            ArrayList<String> answersToDTO = new ArrayList<>();
            QuestDTO questDTO = new QuestDTO();
            questDTO.setName(entity.getName());
            for (AnswersEntity answers : allAnswers) {
               if (answers.getQuestionId() == entity.getId()) {
                    answersToDTO.add(answers.getBody());
                }
            }
            questDTO.setAnswers(answersToDTO);
            arrayDTO.add(questDTO);
            logger.info("query = " + questDTO.getName());
            logger.info("query = " + questDTO.getAnswers());
          //  answersToDTO.clear();
        }
        model.addAttribute("quest",arrayDTO);
*/
        return "QuestionView";
    }
}