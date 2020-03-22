package test.Dto;

import java.util.List;
import java.util.Map;

public class QuestDto {

    private String name;



    private List<AnswerDto> answers;

    private Long questionId;




    public QuestDto() {

    }


    public String getName() { return name; }
    public  void setName(String name) {this.name = name; }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }



}
