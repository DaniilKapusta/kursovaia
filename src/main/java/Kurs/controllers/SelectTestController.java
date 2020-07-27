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
import Kurs.Dto.QuestDto;
import Kurs.Dto.QuestionDto;
import Kurs.Dto.StudentDto;
import Kurs.Dto.TestDTO;
import Kurs.service.FindAnswersInterface;
import Kurs.service.FindQuestionInterface;
import Kurs.service.FindStudentInterface;
import Kurs.service.FindTestInterface;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SelectTestController {

    private Logger logger = LoggerFactory.getLogger(SelectTestController.class);


    @RequestMapping(value = "/selectTest", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("selectTest", "selectTest", new TestDTO());
    }

    @Autowired
    private FindStudentInterface findStudentInterface;
    @Autowired
    private FindTestInterface findTestInterface;
    @Autowired
    private FindQuestionInterface findQuestionInterface;
    @Autowired
    private FindAnswersInterface findAnswersInterface;

    @RequestMapping(value = "/QuestionView", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("selectTest") StudentDto student, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }

        Map<String, String[]> test = req.getParameterMap();
        Long testId = Long.valueOf("1");
        Long studentId =Long.valueOf("1") ;
        for (String key : test.keySet()) {
            String[] strArr = test.get(key);
            for (String val : strArr) {
                logger.info(key + " = " + val);
                if (key.equals("studentId")) {

                    studentId = Long.parseLong(val);
                }
                if (key.equals("testSelect")) {
                  testId = Long.parseLong(val);
                }
            }
        }
        logger.info(String.valueOf(testId));
       StudentDto studentDto = findStudentInterface.findByIdLike(studentId);
        TestDTO testDTO = findTestInterface.findByIdLike(testId);
        logger.info("test = "+testDTO.getName());
        logger.info("student = "+ studentDto.getName());
      List<QuestionDto> questionDtos = findQuestionInterface.findByTestIdLike(testDTO.getId());
        List<QuestDto> questDtos = new ArrayList<>();
        questionDtos.forEach(quest ->{
            QuestDto questDto = new QuestDto();
            questDto.setName(quest.getName());
            questDto.setQuestionId(quest.getId());
            questDto.setAnswers(findAnswersInterface.findByQuestionIdLike(quest.getId()));
            questDtos.add(questDto);
        });
        model.addAttribute("quest", questDtos);
        model.addAttribute("student1", studentDto.getId());
        model.addAttribute("studentName", studentDto.getName());
        model.addAttribute("questionCount",testDTO.getQuestionCount());
        model.addAttribute("testName", testDTO.getName());
        model.addAttribute("testId",testDTO.getId());

        return "QuestionView";
    }
}
