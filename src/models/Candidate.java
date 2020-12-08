package models;

import utils.GenList;
import utils.Node;

public class Candidate extends Politician {
        private int totalVotes;
        private String partyStoodFor;


        private Election electionTookPartIn;
        private GenList<Election> elections;

    public Election getElectionTookPartIn() {
        return electionTookPartIn;
    }

    public void setElectionTookPartIn(Election electionTookPartIn) {
        this.electionTookPartIn = electionTookPartIn;
    }


    public Candidate(String name, String dateOfBirth, String politicalParty, String partyStoodFor, String homeCounty, String imgUrl, int totalVotes, Election electionTookPartIn, GenList<Election> elections) {
        super(name, dateOfBirth, politicalParty, homeCounty, imgUrl);
        this.partyStoodFor=partyStoodFor;
        this.totalVotes=totalVotes;
        this.electionTookPartIn =electionTookPartIn;
        this.elections = elections ;

        elections.addElement(electionTookPartIn);




    }
    public Candidate(String name, String dateOfBirth, String politicalParty, String partyStoodFor, String homeCounty, String imgUrl, int totalVotes) {
        super(name, dateOfBirth, politicalParty, homeCounty, imgUrl);
        this.partyStoodFor=partyStoodFor;
        this.totalVotes=totalVotes;


        //
        // When adding a candidate replace it, the non candidate with setContents
        //


    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public String getPartyStoodFor() {
        return partyStoodFor;
    }

    public void setPartyStoodFor(String partyStoodFor) {
        this.partyStoodFor = partyStoodFor;
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
