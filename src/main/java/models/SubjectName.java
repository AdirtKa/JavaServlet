package models;

public class SubjectName {
    int subjectId;
    String subjectName;

    public SubjectName(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public int getSubjectId() {return subjectId;}
    public String getSubjectName() {return subjectName;}
    public void setSubjectId(int subjectId) {this.subjectId = subjectId;}
    public void setSubjectName(String subjectName) {this.subjectName = subjectName;}

}
