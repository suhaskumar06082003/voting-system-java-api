
package com.example.voting.service;
import com.example.voting.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteService {
    private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();

    public String addCandidate(String name) {
        candidates.putIfAbsent(name, new Candidate(name));
        return "Candidate " + name + " added successfully.";
    }

    public synchronized int castVote(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate != null) {
            synchronized (candidate) {
                candidate.incrementVote();
                return candidate.getVoteCount();
            }
        }
        throw new IllegalArgumentException("Invalid candidate name.");
    }

    public int getVoteCount(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate != null) {
            return candidate.getVoteCount();
        }
        throw new IllegalArgumentException("Invalid candidate name.");
    }

    public Map<String, Integer> getAllVotes() {
        Map<String, Integer> voteCounts = new ConcurrentHashMap<>();
        candidates.forEach((name, candidate) -> voteCounts.put(name, candidate.getVoteCount()));
        return voteCounts;
    }

    public String getWinner() {
        return candidates.values().stream()
                .max((c1, c2) -> Integer.compare(c1.getVoteCount(), c2.getVoteCount()))
                .map(Candidate::getName)
                .orElse("No candidates available.");
    }
}



