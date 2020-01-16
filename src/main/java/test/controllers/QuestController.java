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
import test.DTO.QuestionDTO;
import test.service.SaveAnswerInterface;
import test.service.SaveQuestionInterface;
import test.service.SaveQuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class QuestController {
   private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private SaveQuestionInterface saveQuestionInterface;

    @Autowired
    private SaveAnswerInterface saveAnswerInterface;

    @RequestMapping(value = "/addQuest", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("addQuest", "addQuest", new QuestDTO());
    }

    @RequestMapping(value = "/questCreated", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("addQuest") QuestDTO quest, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }
        Map<String, String[]> answers = req.getParameterMap();

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setName(answers.get("name")[0]);
        QuestionDTO savedQuestion = saveQuestionInterface.saveQuestion(questionDTO);

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setBody(answers.get("opt1")[0]);
        answerDTO.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt1")) {
            answerDTO.setRight(true);
        } else {
            answerDTO.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDTO);

        AnswerDTO answerDTO2 = new AnswerDTO();
        answerDTO2.setBody(answers.get("opt2")[0]);
        answerDTO2.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt2")) {
            answerDTO2.setRight(true);
        } else {
            answerDTO2.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDTO2);

        AnswerDTO answerDTO3 = new AnswerDTO();
        answerDTO3.setBody(answers.get("opt3")[0]);
        answerDTO3.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt3")) {
            answerDTO3.setRight(true);
        } else {
            answerDTO3.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDTO3);

        AnswerDTO answerDTO4 = new AnswerDTO();
        answerDTO4.setBody(answers.get("opt4")[0]);
        answerDTO4.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt4")) {
            answerDTO4.setRight(true);
        } else {
            answerDTO4.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDTO4);


        for (String key : answers.keySet()) {
            String[] strArr = answers.get(key);
            for (String val : strArr) {
                logger.info(key + " = " + val);
            }
        }

        return "questCreated";
    }
}
