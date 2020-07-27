package Kurs.Dto;

public class QuestionDto {
    private Long id;

    private String name;



    private Long testId;



    public QuestionDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() { return name; }
    public  void setName(String name) {this.name = name; }
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
