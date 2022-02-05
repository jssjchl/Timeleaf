package com.example.timeleaf.controller;

import com.example.timeleaf.domain.Board;
import com.example.timeleaf.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "id", defaultValue = "0") Long id, Model model){
        model.addAttribute("board", boardService.findBoardId(id));
        return "/board/form";
    }

    @GetMapping("list")
    public String list(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("boardlist", boardService.findBoardList(pageable));
        return "/board/list";
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody Board board){
        boardService.save(board);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping
    public ResponseEntity<?> putBoard(@PathVariable("id") Long id, @RequestBody Board board){
        Board persistBoard = boardService.findBoardId(id);
        persistBoard.update(board);
        boardService.save(persistBoard);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        boardService.deleteById(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}


/*
* @ResponseBody, @RequestBody의 사용이유
* 스프링에서 비동기 처리를 할 때 사용한다.
* 클라이언트 - > 서버
* Json형태로 데이터를 담아 서버로 보냈을 때, RequestBody에서 http 요청 본문에 담긴 값들을 자바 객체로 변환시켜 객체에 저장
* 서버 - > 클라이언트
* ResponseBody를 사용하여 자바 객체를 http 응답 본문의 객체로 변환하여 클라이언트로 전송
* */