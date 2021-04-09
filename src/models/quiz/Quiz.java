package models.quiz;


import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private static int counter;
    private List<Question> questionsList;
    public Quiz(){
        counter++;
        this.id = counter;
    }

    public int getId() {
        return id;
    }

    public Quiz(List<Question> questions){
        this.questionsList = new ArrayList<>();
        for (Question ques : questions) {
            try {
                this.questionsList.add((Question) ques.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder();
        for (Question q : this.questionsList)
            aux.append(q.toString());

        return "Id: " + this.id + "\n Question list:\n" + aux.toString();
    }
}
