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
import test.Dto.AnswerDto;
import test.Dto.AnswersStatDto;
import test.Dto.QuestionDto;
import test.Dto.TestResultDto;
import test.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuestEndController {

    private Logger logger = LoggerFactory.getLogger(QuestEndController.class);


    @Autowired
    private FindQuestionInterface findQuestionInterface;

    @Autowired
    private FindAnswersInterface findAnswersInterface;

    @Autowired
    private SaveTestResultInterface saveTestResultInterface;

    @Autowired
    private FindTestInterface findTestInterface;


    @RequestMapping(value = "/questEnd", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("questEnd") TestResultDto testResultDTO, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }
        Map<String, String[]> pickAnswers = req.getParameterMap();
        for (String key : pickAnswers.keySet()) {
            String[] strArr = pickAnswers.get(key);
            for (String val : strArr) {
                logger.info(key + " = " + val);
            }
        }
     int correct = 0;
        List<AnswersStatDto> answersStatDtos = new ArrayList<>();


        Long id ;
        Long idQuest;

        for (String key : pickAnswers.keySet()) {
            String[] strArr = pickAnswers.get(key);
            for (String val : strArr) {
                AnswersStatDto answersStatDTO = new AnswersStatDto();
                try {
                 idQuest = Long.parseLong(key);
                 id = Long.parseLong(val);
                }
                catch (java.lang.NumberFormatException exceptionObject) {
                    break;
                }

                AnswerDto answerDTO = findAnswersInterface.findByIdLike(id);
                QuestionDto questionDTO = findQuestionInterface.findByIdLike(idQuest);
                answersStatDTO.setQuestionBody(questionDTO.getName());
                answersStatDTO.setPickAnswerBody(answerDTO.getBody());
                if (answerDTO.getRight())
                    correct++;

                findAnswersInterface.findByQuestionIdLike(questionDTO.getId()).forEach(st -> {
                    if (st.getRight())
                        answersStatDTO.setCorrectAnswer(st.getBody());
                });
                answersStatDtos.add(answersStatDTO);
            }
        }


        int mark = 0;
        double rightPercent = (double)correct/(double)testResultDTO.getQuestionCount()*100;
        logger.info(String.valueOf(testResultDTO.getQuestionCount()));
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
        testResultDTO.setCorrectAnswers(correct);
        testResultDTO.setMark(mark);
        logger.info(String.valueOf(testResultDTO.getStudentId()));
        testResultDTO.setTestingDate(new java.sql.Date(System.currentTimeMillis()));
        testResultDTO.setTestName(findTestInterface.findByIdLike(testResultDTO.getTestId()).getName());
        saveTestResultInterface.saveTestResult(testResultDTO);
        model.addAttribute("testName", findTestInterface.findByIdLike(testResultDTO.getTestId()).getName());

            model.addAttribute("questionCount",testResultDTO.getQuestionCount());
            model.addAttribute("answerStat", answersStatDtos);
        model.addAttribute("correctAnswers",correct);



        return "questEnd";
    }

}
