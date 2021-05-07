package service;

import models.quiz.Question;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionService {
    public static List<Question> questionServiceList = new ArrayList<>();

    public static QuestionService instance = null;
    private QuestionService() {}

    public static QuestionService getInstance() {
        if(instance == null)
            instance = new QuestionService();
        return instance;
    }
    
    public void addQuestionService(String questionText, List<String> answerChoices, Set<Integer> correctAnswers)  {
        questionServiceList.add(new Question(questionText, answerChoices, correctAnswers));

        List<String> newRow = new ArrayList<>();
        newRow.add(questionText);

        String choices = "";
        for (var choice : answerChoices) {
            choices += choice + " | ";
        }
        newRow.add(choices);
        String corrects = "";
        for (var correct : correctAnswers) {
            choices += correct + " | ";
        }
        newRow.add(corrects);

        WritingInFileService.getInstance().csvWrite("../resources/questionServices.csv", newRow);

        System.out.println("\nThe QuestionService was written to the csv file\n");
    }

    public void readQuestionService() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("../resources/questionServices.csv");
        for (List<String> lst: content) {
            String questionText = lst.get(0);
            List<String> answerChoices = new ArrayList<>();
            String[] ans = lst.get(1).split(" | ");
                for(int i = 0; i < ans.length; ++i) {
                    answerChoices.add(ans[i]);
                }
            Set<Integer> correctChoices = new HashSet<>();
            String[] ans2 = lst.get(2).split(" | ");
            for(int i = 0; i < ans2.length; ++i) {
                correctChoices.add(Integer.parseInt(ans2[i]));
            }

            questionServiceList.add(new Question(questionText, answerChoices, correctChoices));

        }
    }
}
