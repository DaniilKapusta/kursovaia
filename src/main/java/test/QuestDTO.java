package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestDTO {

    private String name;



    private Map<Long,String> answers;

    private Long questionId;




    public QuestDTO() {

    }


    public String getName() { return name; }
    public  void setName(String name) {this.name = name; }

    public Map<Long,String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long,String> answers) {
        this.answers = answers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }



}
