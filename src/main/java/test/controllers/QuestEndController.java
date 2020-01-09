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
import test.DTO.AnswersStatDTO;
import test.entity.AnswersEntity;
import test.entity.QuestEntity;
import test.entity.TestResultEntity;
import test.repository.AnswersDAO;
import test.repository.QuestionDAO;
import test.repository.StudentRepository;
import test.repository.TestResultRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class QuestEndController {

    Logger logger = LoggerFactory.getLogger(QuestEndController.class);


    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private AnswersDAO answersDAO;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private StudentRepository studentRepository;


    @RequestMapping(value = "/questEnd", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("questEnd") TestResultEntity testResultEntity, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }
        Map<String, String[]> pickAnswers = req.getParameterMap();
        for (String key : pickAnswers.keySet()) {
            String[] strArr = (String[]) pickAnswers.get(key);
            for (String val : strArr) {
                logger.info(key + " = " + val);
            }
        }
     int correct = 0;
        List<AnswersStatDTO> answersStatDTOS = new ArrayList<>();


        Long id ;
        Long idQuest;

        for (String key : pickAnswers.keySet()) {
            String[] strArr = (String[]) pickAnswers.get(key);
            for (String val : strArr) {
                AnswersStatDTO answersStatDTO = new AnswersStatDTO();
                try {
                 idQuest = Long.parseLong(key);
                 id = Long.parseLong(val);
                }
                catch (java.lang.NumberFormatException exceptionObject) {
                    break;
                }

                AnswersEntity answersEntity = answersDAO.findByIdLike(id);
                QuestEntity questEntity = questionDAO.findByIdLike(idQuest);
                answersStatDTO.setQuestionBody(questEntity.getName());
                answersStatDTO.setPickAnswerBody(answersEntity.getBody());
                if (answersEntity.getRight())
                    correct++;

                answersDAO.findByQuestionIdLike(questEntity.getId()).forEach(st -> {
                    if (st.getRight())
                        answersStatDTO.setCorrectAnswer(st.getBody());
                });
                answersStatDTOS.add(answersStatDTO);
            }
        }


        int mark = 0;
        double rightPercent = (double)correct/(double)testResultEntity.getQuestionCount()*100;
        logger.info(String.valueOf(testResultEntity.getQuestionCount()));
        logger.info(String.valueOf(rightPercent));

        if (rightPercent < 30.0) {
          mark = 2;
            model.addAttribute("mark","2"); }

            if ((rightPercent >= 30.0)&&(rightPercent < 50.0)) {
                mark = 3;
            model.addAttribute("mark","3"); }

            if ((rightPercent >= 50.0)&&(rightPercent < 70.0)) {
                mark = 4;
                model.addAttribute("mark","4"); }

            if (rightPercent >= 70.0) {
                mark = 5;
                model.addAttribute("mark","5"); }

           // testResultEntity.setStudentId();
        testResultEntity.setCorrectAnswers(correct);
            testResultEntity.setMark(mark);
            logger.info(String.valueOf(testResultEntity.getStudentId()));
            testResultEntity.setTestingDate(new java.sql.Date(System.currentTimeMillis()));
            testResultRepository.save(testResultEntity);

            model.addAttribute("questionCount",testResultEntity.getQuestionCount());
            model.addAttribute("answerStat",answersStatDTOS);
        model.addAttribute("correctAnswers",correct);



        return "questEnd";
    }

}
