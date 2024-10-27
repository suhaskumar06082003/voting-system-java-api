# Voting API

## Usage Guide

This is a simple voting API implemented in Java using Spring Boot. The API allows users to enter candidates, cast votes, count votes, list candidates, and determine the winner.

### API Endpoints

1. Enter Candidate
   - URL: `/entercandidate?name={name}`
   - Method: `GET`
   - Description: Adds a candidate with the specified name to the system.
   - Example: `/entercandidate?name=ajay`

2. Cast Vote
   - URL: `/castvote?name={name}`
   - Method**: `GET`
   - Description: Casts a vote for the specified candidate.
   - Example: `/castvote?name=ajay`

3. Count Vote
   - URL: `/countvote?name={name}`
   - Method: `GET`
   - Description: Retrieves the current vote count for the specified candidate.
   - Example: `/countvote?name=ajay`

4. List Votes
   - URL: `/listvote`
   - Method: `GET`
   - Description: Lists all candidates and their respective vote counts.
   - Example: `/listvote`

5. Get Winner
   - URL: `/getwinner`
   - Method: `GET`
   - Description: Returns the name of the candidate with the highest number of votes.
   - Example: `/getwinner`

## Features Implemented
- In-memory candidate management.
- Vote counting functionality.
- Ability to list all candidates and their votes.
- Winner determination based on votes.
- Basic error handling for invalid votes.
