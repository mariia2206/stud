package domain;

public class Group {
    private Long id;
    private String groupName;
    private String faculty;
    private Integer course;
    private String studyType;

    public Group() {}

    public Group(Long id, String groupName, String faculty, Integer course, String studyType) {
        this.id = id;
        this.groupName = groupName;
        this.faculty = faculty;
        this.course = course;
        this.studyType = studyType;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public Integer getCourse() { return course; }
    public void setCourse(Integer course) { this.course = course; }
    public String getStudyType() { return studyType; }
    public void setStudyType(String studyType) { this.studyType = studyType; }
}