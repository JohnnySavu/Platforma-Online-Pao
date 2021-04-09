package models.quiz;


import java.util.List;

public class Quiz {
    private List<Question> questionsList;
    Quiz(){

    }
    Quiz(List<Question> questionList){
        this.questionsList = questionList;
        for (Question ques : questionsList) {
            try {
                this.questionsList.add((Question) ques.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
