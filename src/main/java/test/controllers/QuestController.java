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
import test.Dto.QuestionDto;
import test.Dto.TestDTO;
import test.service.SaveAnswerInterface;
import test.service.SaveQuestionInterface;
import test.service.SaveTestInterface;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class QuestController {
   private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private SaveTestInterface saveTest;
/*
    @Autowired
    private SaveAnswerInterface saveAnswerInterface;
*/
    @RequestMapping(value = "/addQuest", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("addQuest", "addQuest", new QuestDto());
    }

    @RequestMapping(value = "/testQuestions", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("addQuest") TestDTO testDTO, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }
        Map<String, String[]> test = req.getParameterMap();
        testDTO.setId(saveTest.saveTest(testDTO).getId());

/*
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setName(answers.get("name")[0]);
        QuestionDto savedQuestion = saveQuestionInterface.saveQuestion(questionDTO);

        AnswerDto answerDTO = new AnswerDto();
        answerDTO.setBody(answers.get("opt1")[0]);
        answerDTO.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt1")) {
            answerDTO.setRight(true);
        } else {
            answerDTO.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDTO);

        AnswerDto answerDto2 = new AnswerDto();
        answerDto2.setBody(answers.get("opt2")[0]);
        answerDto2.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt2")) {
            answerDto2.setRight(true);
        } else {
            answerDto2.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDto2);

        AnswerDto answerDto3 = new AnswerDto();
        answerDto3.setBody(answers.get("opt3")[0]);
        answerDto3.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt3")) {
            answerDto3.setRight(true);
        } else {
            answerDto3.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDto3);

        AnswerDto answerDto4 = new AnswerDto();
        answerDto4.setBody(answers.get("opt4")[0]);
        answerDto4.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt4")) {
            answerDto4.setRight(true);
        } else {
            answerDto4.setRight(false);
        }
        saveAnswerInterface.saveAnswer(answerDto4);
*/

        for (String key : test.keySet()) {
            String[] strArr = test.get(key);
            for (String val : strArr) {
                logger.info(key + " = " + val);
            }
        }
        logger.info(testDTO.getName());
        logger.info(Integer.toString(testDTO.getQuestionCount()));
        model.addAttribute("testId",testDTO.getId());
        model.addAttribute("questionNumber", testDTO.getQuestionCount());
        model.addAttribute("questionCount", testDTO.getQuestionCount());
        model.addAttribute("qN",1);

        return "testQuestions";
    }
}
