package Kurs.Dto;

public class AnswersStatDto {

   private String questionBody;
   private String pickAnswerBody;
   private String CorrectAnswer;

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getPickAnswerBody() {
        return pickAnswerBody;
    }

    public void setPickAnswerBody(String pickAnswerBody) {
        this.pickAnswerBody = pickAnswerBody;
    }


    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }
}
