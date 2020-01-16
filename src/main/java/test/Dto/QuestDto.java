package test.Dto;

import java.util.Map;

public class QuestDto {

    private String name;



    private Map<Long,String> answers;

    private Long questionId;




    public QuestDto() {

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
