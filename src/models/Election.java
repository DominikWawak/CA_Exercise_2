package models;

import utils.GenList;
import utils.Node;

public class Election {
    public String electionType;
    public String electionLocation;
    public String date;
    public int numberOfSeats;

    public Election(String electionType, String electionLocation, String date, int numberOfSeats , GenList<Candidate> candidateGenList) {
        this.electionType = electionType;
        this.electionLocation = electionLocation;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public String getElectionLocation() {
        return electionLocation;
    }

    public void setElectionLocation(String electionLocation) {
        this.electionLocation = electionLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
// DO UPDATE METHOD HERE


    @Override
    public String toString() {
        return "Election{" +
                "electionType='" + electionType + '\'' +
                ", electionLocation='" + electionLocation + '\'' +
                ", date='" + date + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
