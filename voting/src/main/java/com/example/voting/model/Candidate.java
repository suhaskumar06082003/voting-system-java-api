// package com.example.voting.model;

// public class Candidate {
//     private String name;
//     private int voteCount;

//     public Candidate(String name) {
//         this.name = name;
//         this.voteCount = 0;
//     }

//     public String getName() {
//         return name;
//     }

//     public int getVoteCount() {
//         return voteCount;
//     }

//     public void incrementVote() {
//         this.voteCount++;
//     }
// }




package com.example.voting.model;

public class Candidate {
    private String name;
    private int voteCount;
    private String party; // New field

    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.voteCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getParty() {
        return party;
    }

    public void incrementVote() {
        this.voteCount++;
    }
}
