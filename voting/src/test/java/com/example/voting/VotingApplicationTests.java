

package com.example.voting;

import com.example.voting.service.VoteService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VoteServiceTests {

    private final VoteService voteService = new VoteService();

    @Test
    void testAddCandidate() {
        assertEquals("Candidate ajay added successfully.", voteService.addCandidate("ajay"));
    }

    @Test
    void testCastVote() {
        voteService.addCandidate("ajay");
        assertEquals(1, voteService.castVote("ajay"));
    }

    @Test
    void testCountVote() {
        voteService.addCandidate("ajay");
        voteService.castVote("ajay");
        assertEquals(1, voteService.getVoteCount("ajay"));
    }

    @Test
    void testInvalidCandidateVote() {
        assertThrows(IllegalArgumentException.class, () -> voteService.castVote("nonexistent"));
    }

    @Test
    void testGetWinner() {
        voteService.addCandidate("ajay");
        voteService.castVote("ajay");
        assertEquals("ajay", voteService.getWinner());
    }
}


