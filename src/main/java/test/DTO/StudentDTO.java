package test.DTO;

public class StudentDTO {
    private Long id;

    private String name;


    private String studentsGroup;

    private String branch;



    public StudentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(String studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public String getBranch() {return branch; }

    public  void setBranch(String branch) {this.branch = branch; }


}
