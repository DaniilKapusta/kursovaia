package test.entity;



import javax.persistence.*;

@Entity
@Table(name="Quest")
public class QuestEntity  {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;



    private Long testId;



    public QuestEntity() {

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
