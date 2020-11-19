package models;

import utils.Node;

public class Election {
    public String electionType;
    public String electionLocation;
    public String date;
    public int numberOfSeats;

    public Election(String electionType, String electionLocation, String date, int numberOfSeats , Node<Candidate> candidates) {
        this.electionType = electionType;
        this.electionLocation = electionLocation;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
    }

    // DO UPDATE METHOD HERE
}
