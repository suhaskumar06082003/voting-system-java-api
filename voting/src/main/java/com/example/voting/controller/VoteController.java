


package com.example.voting.controller;

import com.example.voting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/entercandidate")
    public String enterCandidate(@RequestParam String name) {
        return voteService.addCandidate(name);
    }

    @GetMapping("/castvote")
    public int castVote(@RequestParam String name) {
        return voteService.castVote(name);
    }

    @GetMapping("/countvote")
    public int countVote(@RequestParam String name) {
        return voteService.getVoteCount(name);
    }

    @GetMapping("/listvote")
    public Map<String, Integer> listVote() {
        return voteService.getAllVotes();
    }

    @GetMapping("/getwinner")
    public String getWinner() {
        return voteService.getWinner();
    }
     @DeleteMapping("/removecandidate")
    public ResponseEntity<String> removeCandidate(@RequestParam String name) {
        String response = voteService.removeCandidate(name);
        return ResponseEntity.ok(response);
    }

    
}
