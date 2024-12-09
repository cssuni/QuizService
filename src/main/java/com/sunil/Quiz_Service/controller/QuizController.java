package com.sunil.Quiz_Service.controller;


import com.sunil.Quiz_Service.model.QuestionWrapper;
import com.sunil.Quiz_Service.model.QuizDto;
import com.sunil.Quiz_Service.model.Response;
import com.sunil.Quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){

        return service.createQuiz(quizDto.getCategory(),quizDto.getTitle(),quizDto.getNumQ());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){

        return service.getQuizById(id);

    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getQuiz(@RequestBody List<Response> responses){

        System.out.println("Get Score Called");



        ResponseEntity<Integer> score = service.calculateQuiz(responses);

        System.out.println(score);

        return score;

    }

}
