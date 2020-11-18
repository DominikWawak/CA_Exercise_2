package models;

public class Candidate extends Politician {



    public Candidate(String name, String dateOfBirth, String politicalParty, String homeCounty, String imgUrl) {
        super(name, dateOfBirth, politicalParty, homeCounty, imgUrl);


    }
    @Override
    public String toString() {
        return "Candidate"+ super.toString();
    }

}
