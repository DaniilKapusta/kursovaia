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
import test.entity.QuestEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class QuestController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

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

        QuestEntity questEntity = new QuestEntity();
        questEntity.setName(answers.get("name")[0]);
        QuestEntity savedQuestion = questionRepository.save(questEntity);

        AnswersEntity answersEntity = new AnswersEntity();
        answersEntity.setBody(answers.get("opt1")[0]);
        answersEntity.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt1")) {
            answersEntity.setRight(true);
        } else {
            answersEntity.setRight(false);
        }
        answerRepository.save(answersEntity);

        AnswersEntity answersEntity2 = new AnswersEntity();
        answersEntity2.setBody(answers.get("opt2")[0]);
        answersEntity2.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt2")) {
            answersEntity2.setRight(true);
        } else {
            answersEntity2.setRight(false);
        }
        answerRepository.save(answersEntity2);

        AnswersEntity answersEntity3 = new AnswersEntity();
        answersEntity3.setBody(answers.get("opt3")[0]);
        answersEntity3.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt3")) {
            answersEntity3.setRight(true);
        } else {
            answersEntity3.setRight(false);
        }
        answerRepository.save(answersEntity3);

        AnswersEntity answersEntity4 = new AnswersEntity();
        answersEntity4.setBody(answers.get("opt4")[0]);
        answersEntity4.setQuestionId(savedQuestion.getId());
        if (answers.get("correctOption")[0].equals("opt4")) {
            answersEntity4.setRight(true);
        } else {
            answersEntity4.setRight(false);
        }
        answerRepository.save(answersEntity4);


        for (String key : answers.keySet()) {
            String[] strArr = (String[]) answers.get(key);
            for (String val : strArr) {
                logger.info(key + " = " + val);
            }
        }

        return "questCreated";
    }
}
