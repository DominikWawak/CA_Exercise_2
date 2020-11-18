package models;

public class Candidate extends Politician {
        private int totalVotes;
        private String partyStoodFor;



    public Candidate(String name, String dateOfBirth, String politicalParty,String partyStoodFor, String homeCounty, String imgUrl,int totalVotes) {
        super(name, dateOfBirth, politicalParty, homeCounty, imgUrl);
        this.partyStoodFor=partyStoodFor;
        this.totalVotes=totalVotes;


    }
    @Override
    public String toString() {
        return "Candidate"+ super.toString();
    }

}
