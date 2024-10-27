
// package com.example.voting.service;
// import com.example.voting.model.Candidate;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;

// @Service
// public class VoteService {
//     private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();

//     public String addCandidate(String name) {
//         candidates.putIfAbsent(name, new Candidate(name));
//         return "Candidate " + name + " added successfully.";
//     }

//     public synchronized int castVote(String name) {
//         Candidate candidate = candidates.get(name);
//         if (candidate != null) {
//             synchronized (candidate) {
//                 candidate.incrementVote();
//                 return candidate.getVoteCount();
//             }
//         }
//         throw new IllegalArgumentException("Invalid candidate name.");
//     }

//     public int getVoteCount(String name) {
//         Candidate candidate = candidates.get(name);
//         if (candidate != null) {
//             return candidate.getVoteCount();
//         }
//         throw new IllegalArgumentException("Invalid candidate name.");
//     }

//     public Map<String, Integer> getAllVotes() {
//         Map<String, Integer> voteCounts = new ConcurrentHashMap<>();
//         candidates.forEach((name, candidate) -> voteCounts.put(name, candidate.getVoteCount()));
//         return voteCounts;
//     }

//     public String getWinner() {
//         return candidates.values().stream()
//                 .max((c1, c2) -> Integer.compare(c1.getVoteCount(), c2.getVoteCount()))
//                 .map(Candidate::getName)
//                 .orElse("No candidates available.");
//     }

//     public String removeCandidate(String name) {
//         if (candidates.containsKey(name)) {
//             candidates.remove(name);
//             return "Candidate " + name + " removed successfully.";
//         }
//         return "Candidate " + name + " does not exist.";
//     }


//     private Map<String, List<LocalDateTime>> voteHistory = new HashMap<>();

// public int casteVote(String name) {
//     if (!candidates.containsKey(name)) {
//         throw new IllegalArgumentException("Candidate does not exist.");
//     }
//     int currentCount = Candidate();
//     Candidate.put(name, currentCount + 1);
    
//     // Add vote timestamp
//     voteHistory.computeIfAbsent(name, k -> new ArrayList<>()).add(LocalDateTime.now());
    
//     return currentCount + 1;
// }

// public List<LocalDateTime> getVoteHistory(String name) {
//     return voteHistory.getOrDefault(name, new ArrayList<>());
// }
    
// }



// package com.example.voting.service;

// import com.example.voting.model.Candidate;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.*;

// @Service
// public class VoteService {
//     private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();
//     private final Map<String, List<LocalDateTime>> voteHistory = new HashMap<>();
//     private static final int MAX_VOTES = 10; // Set a maximum limit

//     public String addCandidate(String name, String party) {
//         candidates.putIfAbsent(name, new Candidate(name, party));
//         return "Candidate " + name + " added successfully.";
//     }

//     public synchronized int castVote(String name) {
//         Candidate candidate = candidates.get(name);
//         if (candidate != null) {
//             int currentCount = candidate.getVoteCount();
//             if (currentCount >= MAX_VOTES) {
//                 throw new IllegalStateException("Candidate has reached the maximum number of votes.");
//             }
//             candidate.incrementVote();
//             voteHistory.computeIfAbsent(name, k -> new ArrayList<>()).add(LocalDateTime.now());
//             return candidate.getVoteCount();
//         }
//         throw new IllegalArgumentException("Invalid candidate name.");
//     }

//     public int getVoteCount(String name) {
//         Candidate candidate = candidates.get(name);
//         if (candidate != null) {
//             return candidate.getVoteCount();
//         }
//         throw new IllegalArgumentException("Invalid candidate name.");
//     }

//     public Map<String, Integer> getAllVotes() {
//         Map<String, Integer> voteCounts = new ConcurrentHashMap<>();
//         candidates.forEach((name, candidate) -> voteCounts.put(name, candidate.getVoteCount()));
//         return voteCounts;
//     }

//     public String getWinner() {
//         return candidates.values().stream()
//                 .max(Comparator.comparingInt(Candidate::getVoteCount))
//                 .map(Candidate::getName)
//                 .orElse("No candidates available.");
//     }

//     public String removeCandidate(String name) {
//         if (candidates.containsKey(name)) {
//             candidates.remove(name);
//             voteHistory.remove(name); // Remove vote history for the candidate
//             return "Candidate " + name + " removed successfully.";
//         }
//         return "Candidate " + name + " does not exist.";
//     }

//     public List<LocalDateTime> getVoteHistory(String name) {
//         return voteHistory.getOrDefault(name, new ArrayList<>());
//     }

//     public void resetVotes() {
//         candidates.values().forEach(candidate -> candidate.setVoteCount(0));
//         voteHistory.clear();
//     }

//     public Candidate getCandidateDetails(String name) {
//         if (candidates.containsKey(name)) {
//             return candidates.get(name);
//         }
//         throw new IllegalArgumentException("Candidate does not exist.");
//     }
// }




package com.example.voting.service;

import com.example.voting.model.Candidate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; // Import this line

@Service
public class VoteService {
    private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();
    private final Map<String, List<LocalDateTime>> voteHistory = new HashMap<>();
    private static final int MAX_VOTES = 10; // Set a maximum limit

    public String addCandidate(String name, String party) {
        candidates.putIfAbsent(name, new Candidate(name, party));
        return "Candidate " + name + " added successfully.";
    }

    public synchronized int castVote(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate does not exist.");
        }
        if (candidate.getVoteCount() >= MAX_VOTES) {
            throw new IllegalStateException("Candidate has reached the maximum number of votes.");
        }
        candidate.incrementVote();
        voteHistory.computeIfAbsent(name, k -> new ArrayList<>()).add(LocalDateTime.now());
        return candidate.getVoteCount();
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

    public String removeCandidate(String name) {
        if (candidates.containsKey(name)) {
            candidates.remove(name);
            voteHistory.remove(name);
            return "Candidate " + name + " removed successfully.";
        }
        return "Candidate " + name + " does not exist.";
    }

    public List<LocalDateTime> getVoteHistory(String name) {
        return voteHistory.getOrDefault(name, new ArrayList<>());
    }

    public void resetVotes() {
        candidates.clear(); // Reset all candidates
        voteHistory.clear(); // Clear the vote history
    }

    public Candidate getCandidateDetails(String name) {
        if (candidates.containsKey(name)) {
            return candidates.get(name);
        }
        throw new IllegalArgumentException("Candidate does not exist.");
    }
}


