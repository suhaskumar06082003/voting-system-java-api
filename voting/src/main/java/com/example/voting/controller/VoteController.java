


// package com.example.voting.controller;

// import com.example.voting.service.VoteService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/")
// public class VoteController {

//     @Autowired
//     private VoteService voteService;

//     @GetMapping("/entercandidate")
//     public String enterCandidate(@RequestParam String name) {
//         return voteService.addCandidate(name);
//     }

//     @GetMapping("/castvote")
//     public int castVote(@RequestParam String name) {
//         return voteService.castVote(name);
//     }

//     @GetMapping("/countvote")
//     public int countVote(@RequestParam String name) {
//         return voteService.getVoteCount(name);
//     }

//     @GetMapping("/listvote")
//     public Map<String, Integer> listVote() {
//         return voteService.getAllVotes();
//     }

//     @GetMapping("/getwinner")
//     public String getWinner() {
//         return voteService.getWinner();
//     }
//      @DeleteMapping("/removecandidate")
//     public ResponseEntity<String> removeCandidate(@RequestParam String name) {
//         String response = voteService.removeCandidate(name);
//         return ResponseEntity.ok(response);
//     }


//     @GetMapping("/votehistory")
// public ResponseEntity<List<LocalDateTime>> getVoteHistory(@RequestParam String name) {
//     List<LocalDateTime> history = voteService.getVoteHistory(name);
//     return ResponseEntity.ok(history);
// }


    
// }





package com.example.voting.controller;

import com.example.voting.model.Candidate;
import com.example.voting.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/addcandidate")
    public ResponseEntity<String> addCandidate(@RequestParam String name, @RequestParam String party) {
        String response = voteService.addCandidate(name, party);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/castvote")
    public ResponseEntity<Integer> castVote(@RequestParam String name) {
        int voteCount = voteService.castVote(name);
        return ResponseEntity.ok(voteCount);
    }

    @GetMapping("/countvote")
    public ResponseEntity<Integer> getVoteCount(@RequestParam String name) {
        int voteCount = voteService.getVoteCount(name);
        return ResponseEntity.ok(voteCount);
    }

    @GetMapping("/allvotes")
    public ResponseEntity<?> getAllVotes() {
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    @GetMapping("/winner")
    public ResponseEntity<String> getWinner() {
        String winner = voteService.getWinner();
        return ResponseEntity.ok(winner);
    }

    @DeleteMapping("/removecandidate")
    public ResponseEntity<String> removeCandidate(@RequestParam String name) {
        String response = voteService.removeCandidate(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/votehistory")
    public ResponseEntity<List<LocalDateTime>> getVoteHistory(@RequestParam String name) {
        List<LocalDateTime> history = voteService.getVoteHistory(name);
        return ResponseEntity.ok(history);
    }

    @DeleteMapping("/resetvotes")
    public ResponseEntity<String> resetVotes() {
        voteService.resetVotes();
        return ResponseEntity.ok("All votes have been reset.");
    }

    @GetMapping("/canditatedetails")
    public ResponseEntity<Candidate> getCandidateDetails(@RequestParam String name) {
        Candidate candidate = voteService.getCandidateDetails(name);
        return ResponseEntity.ok(candidate);
    }
}

