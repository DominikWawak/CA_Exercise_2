package models;

public class NonCandidate extends Politician
{
    public NonCandidate(String name, String dateOfBirth, String politicalParty, String homeCounty, String imgUrl) {
        super(name, dateOfBirth, politicalParty, homeCounty, imgUrl);
    }
    @Override
    public String toString() {
        return "nonCandidate"+ super.toString();
    }
}
