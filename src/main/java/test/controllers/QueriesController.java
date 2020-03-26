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
import test.Dto.QueriesDto;
import test.Dto.TestResultDto;
import test.service.FindStudentInterface;
import test.service.FindTestInterface;
import test.service.FindTestResultInterface;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class QueriesController {

    private Logger logger = LoggerFactory.getLogger(QueriesController.class);

    @RequestMapping(value = "/queries", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("queries", "queries", new QueriesDto());
    }

    @Autowired
    private FindStudentInterface findStudentInterface;

    @Autowired
    private FindTestResultInterface findTestResultInterface;
    @Autowired
    private FindTestInterface findTestInterface;

    @RequestMapping(value = "/queriesFind", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("queries") QueriesDto queriesDTO, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "error";
        }

        Map<String, String[]> pickAnswers = req.getParameterMap();
        for (String key : pickAnswers.keySet()) {
            String[] strArr = pickAnswers.get(key);
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

        List<QueriesDto> queriesDtoList = new ArrayList<>();
        switch (queriesDTO.getFindMethod()) {
            case "findByName":
                Objects.requireNonNull(findStudentInterface.findByNameContainingIgnoreCase(queriesDTO.getStudentName())).forEach(st -> {
                    QueriesDto queriesDto1 = new QueriesDto();
                    queriesDto1.setFindStudent(st);
                    queriesDto1.setStudentResult(findTestResultInterface.findByStudentIdLike(queriesDto1.getFindStudent().getId()));

                    queriesDtoList.add(queriesDto1);
                });
                model.addAttribute("info","Результаты выборки студентов по И.Ф.  "+queriesDTO.getStudentName());
                model.addAttribute("findStudents", queriesDtoList);

                break;
            case "findByGroup":
                Objects.requireNonNull(findStudentInterface.findByStudentsGroupContainingIgnoreCase(queriesDTO.getStudentGroup())).forEach(st -> {
                    QueriesDto queriesDto1 = new QueriesDto();
                    queriesDto1.setFindStudent(st);
                    queriesDto1.setStudentResult(findTestResultInterface.findByStudentIdLike(queriesDto1.getFindStudent().getId()));
                    queriesDtoList.add(queriesDto1);
                });
                model.addAttribute("info","Результаты выборки студентов по группе  "+queriesDTO.getStudentGroup());
                model.addAttribute("findStudents", queriesDtoList);

                break;

            case "findByBranch":
                Objects.requireNonNull(findStudentInterface.findByBranchContainingIgnoreCase(queriesDTO.getStudentBranch())).forEach(st -> {
                    QueriesDto queriesDto1 = new QueriesDto();
                    queriesDto1.setFindStudent(st);
                    queriesDto1.setStudentResult(findTestResultInterface.findByStudentIdLike(queriesDto1.getFindStudent().getId()));
                    queriesDtoList.add(queriesDto1);
                });
                model.addAttribute("info","Результаты выборки студентов по отделению  "+queriesDTO.getStudentBranch());
                model.addAttribute("findStudents", queriesDtoList);

                break;

            case "findByDate":
                List<Long> findId = new ArrayList<>();
                if (queriesDTO.getStudentDate().after(new java.sql.Date(System.currentTimeMillis()))) {
                    model.addAttribute("error","Введите корректную дату");
                    return "queries";
                }

                findTestResultInterface.findByTestingDate(queriesDTO.getStudentDate()).forEach(st -> {
                  if (!findId.contains(st.getStudentId()))
                      findId.add(st.getStudentId());
               });
                   findId.forEach(st -> {
                       QueriesDto queriesDto1 = new QueriesDto();
                       queriesDto1.setFindStudent(findStudentInterface.findByIdLike(st));
                       List<TestResultDto> tests = new ArrayList<>();
                       findTestResultInterface.findByTestingDate(queriesDTO.getStudentDate()).forEach(qq -> {
                           if (qq.getStudentId().equals(st))
                           tests.add(qq);
                       });
                       queriesDto1.setStudentResult(tests);
                       queriesDtoList.add(queriesDto1);
                   });
                   model.addAttribute("info","Результаты выборки студентов по дате за "+ queriesDTO.getStudentDate());
                model.addAttribute("findStudents", queriesDtoList);

                break;

            case "findByTest":
                findId = new ArrayList<>();
                findTestResultInterface.findByTestIdLike(queriesDTO.getTestId()).forEach(st -> {
                    if (!findId.contains(st.getStudentId()))
                        findId.add(st.getStudentId());
                });
                findId.forEach(st -> {
                    QueriesDto queriesDto1 = new QueriesDto();
                    queriesDto1.setFindStudent(findStudentInterface.findByIdLike(st));
                    List<TestResultDto> tests = new ArrayList<>();
                    findTestResultInterface.findByTestIdLike(queriesDTO.getTestId()).forEach(qq -> {
                        if (qq.getStudentId().equals(st))
                            tests.add(qq);
                    });
                    queriesDto1.setStudentResult(tests);
                    queriesDtoList.add(queriesDto1);
                });
                queriesDTO.setTestName(findTestInterface.findByIdLike(queriesDTO.getTestId()).getName());
                model.addAttribute("info","Результаты выборки студентов по тесту "+ queriesDTO.getTestName());
                model.addAttribute("findStudents", queriesDtoList);



                break;
        }
        if (queriesDtoList.size()==0)
            return "notFoundStudents";


        return "queriesFind";
    }
}
