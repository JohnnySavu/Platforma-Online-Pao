package models.quiz;

import java.util.List;
import java.util.Set;

public class Question {
    private String questionText;
    private List<String> answerChoices;
    private Set<Integer> correctAnswers;

    public Question(String questionText, List<String> answerChoices, Set<Integer> correctAnswers){
        this.questionText = questionText;
        this.answerChoices = answerChoices;
        this.correctAnswers = correctAnswers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Set<Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public boolean verify(Set<Integer> answers){
        for (Integer i : answers){
            if (!this.correctAnswers.contains(i))
                return false;
        }
        if (answers.size() != this.correctAnswers.size())
            return false;
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
