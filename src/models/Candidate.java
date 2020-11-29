package models;

public class Candidate extends Politician {
        private int totalVotes;
        private String partyStoodFor;
        Election electionTookPartIn;



    public Candidate(String name, String dateOfBirth, String politicalParty,String partyStoodFor, String homeCounty, String imgUrl,int totalVotes) {
        super(name, dateOfBirth, politicalParty, homeCounty, imgUrl);
        this.partyStoodFor=partyStoodFor;
        this.totalVotes=totalVotes;

        //
        // When adding a candidate replace it, the non candidate with setContents
        //


    }


    public void update(Politician politician, String politicalParty, String homeCounty, String imgUrl) {
       politician.setPoliticalParty(politicalParty);
       politician.setHomeCounty(homeCounty);
       politician.setImgUrl(imgUrl);


    }

    @Override
    public String toString() {
        return
                totalVotes+
                partyStoodFor + super.toString()
                ;
    }
}
