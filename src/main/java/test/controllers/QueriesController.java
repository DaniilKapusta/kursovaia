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
import test.DTO.QueriesDTO;
import test.repository.StudentRepository;
import test.repository.TestResultRepository;
import test.entity.TestResultEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QueriesController {

    Logger logger = LoggerFactory.getLogger(QueriesController.class);

    @RequestMapping(value = "/queries", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("queries", "queries", new QueriesDTO());
    }

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @RequestMapping(value = "/queriesFind", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("queries") QueriesDTO queriesDTO, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }

        Map<String, String[]> pickAnswers = req.getParameterMap();
        for (String key : pickAnswers.keySet()) {
            String[] strArr = (String[]) pickAnswers.get(key);
            for (String val : strArr) {
                if (key.equals("testingDate")) {
                    logger.info("in colc"+val);
                    try {
                        Date date = Date.valueOf(val);
                        queriesDTO.setStudentDate(date);
                    }
                    catch (java.lang.IllegalArgumentException exceptionObject) {
                        model.addAttribute("error","Введите корректную дату");
                        return "queries";
                    }
                    Date date = Date.valueOf(val);
                    queriesDTO.setStudentDate(date);
                }
            }
        }
        logger.info(String.valueOf(queriesDTO.getStudentDate()));

        List<QueriesDTO> queriesDTOList = new ArrayList<>();
        switch (queriesDTO.getFindMethod()) {
            case "findByName":
                studentRepository.findByNameContainingIgnoreCase(queriesDTO.getStudentName()).forEach(st -> {
                    QueriesDTO queriesDTO1 = new QueriesDTO();
                    queriesDTO1.setFindStudent(st);
                    queriesDTO1.setStudentResult(testResultRepository.findByStudentIdLike(queriesDTO1.getFindStudent().getId()));
                    queriesDTOList.add(queriesDTO1);
                });
                model.addAttribute("info","Результаты выборки студентов по И.Ф.  "+queriesDTO.getStudentName());
                model.addAttribute("findStudents",queriesDTOList);

                break;
            case "findByGroup":
                studentRepository.findByStudentsGroupContainingIgnoreCase(queriesDTO.getStudentGroup()).forEach(st -> {
                    QueriesDTO queriesDTO1 = new QueriesDTO();
                    queriesDTO1.setFindStudent(st);
                    queriesDTO1.setStudentResult(testResultRepository.findByStudentIdLike(queriesDTO1.getFindStudent().getId()));
                    queriesDTOList.add(queriesDTO1);
                });
                model.addAttribute("info","Результаты выборки студентов по группе  "+queriesDTO.getStudentGroup());
                model.addAttribute("findStudents",queriesDTOList);

                break;

            case "findByBranch":
                studentRepository.findByBranchContainingIgnoreCase(queriesDTO.getStudentBranch()).forEach(st -> {
                    QueriesDTO queriesDTO1 = new QueriesDTO();
                    queriesDTO1.setFindStudent(st);
                    queriesDTO1.setStudentResult(testResultRepository.findByStudentIdLike(queriesDTO1.getFindStudent().getId()));
                    queriesDTOList.add(queriesDTO1);
                });
                model.addAttribute("info","Результаты выборки студентов по отделению  "+queriesDTO.getStudentBranch());
                model.addAttribute("findStudents",queriesDTOList);

                break;

            case "findByDate":
                List<Long> findId = new ArrayList<>();
                if (queriesDTO.getStudentDate().after(new java.sql.Date(System.currentTimeMillis()))) {
                    model.addAttribute("error","Введите корректную дату");
                    return "queries";
                }

               testResultRepository.findByTestingDate(queriesDTO.getStudentDate()).forEach(st -> {
                  if (!findId.contains(st.getStudentId()))
                      findId.add(st.getStudentId());
               });
                   findId.forEach(st -> {
                       QueriesDTO queriesDTO1 = new QueriesDTO();
                       queriesDTO1.setFindStudent(studentRepository.findByIdLike(st));
                       List<TestResultEntity> tests = new ArrayList<>();
                       testResultRepository.findByTestingDate(queriesDTO.getStudentDate()).forEach(qq -> {
                           if (qq.getStudentId().equals(st))
                           tests.add(qq);
                       });
                       queriesDTO1.setStudentResult(tests);
                       queriesDTOList.add(queriesDTO1);
                   });
                   model.addAttribute("info","Результаты выборки студентов по дате за "+String.valueOf(queriesDTO.getStudentDate()));
                model.addAttribute("findStudents",queriesDTOList);

                break;

        }
        if (queriesDTOList.size()==0)
            return "notFoundStudents";


        return "queriesFind";
    }
}