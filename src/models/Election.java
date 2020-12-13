package models;

import utils.GenList;
import utils.Node;

public class Election {
    public String electionType;
    public String electionLocation;
    public String date;
    public int numberOfSeats;
    private int seatsRemaining = 0;
    GenList<Politician> candidateGenList;

    public GenList<Politician> getCandidateGenList() {
        return candidateGenList;
    }

    public void setCandidateGenList(GenList<Politician> candidateGenList) {
        this.candidateGenList = candidateGenList;
    }

    public Election(String electionType, String electionLocation, String date, int numberOfSeats , GenList<Politician> candidateGenList) {
        this.electionType = electionType;
        this.electionLocation = electionLocation;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
        this.candidateGenList=candidateGenList;
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


    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(int seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

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
