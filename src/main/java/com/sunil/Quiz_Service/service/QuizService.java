package com.sunil.Quiz_Service.service;

import com.sunil.Quiz_Service.feign.QuizInterface;
import com.sunil.Quiz_Service.model.QuestionWrapper;
import com.sunil.Quiz_Service.model.Quiz;
import com.sunil.Quiz_Service.model.Response;
import com.sunil.Quiz_Service.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo repo;

    @Autowired
    QuizInterface quizInterface;



    public ResponseEntity<String> createQuiz(String category, String title, Integer numQ) {

        List<Integer> qIds = quizInterface.createQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(qIds);

        repo.save(quiz);


        return new ResponseEntity<>("Quiz Created",HttpStatus.CREATED);

           }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {

        Quiz quiz = repo.findById(id).orElse(new Quiz());

        List<Integer> qIds = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromIds(qIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateQuiz(List<Response> responses) {

        return quizInterface.getScore(responses);

    }
}
