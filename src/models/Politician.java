package models;

public abstract class Politician {
    private String name;
    private String dateOfBirth;
    private String politicalParty;
    private String homeCounty;
    private String imgUrl;

    public Politician(String name, String dateOfBirth, String politicalParty, String homeCounty, String imgUrl) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

        this.politicalParty = (politicalParty == null) ? "Independent":politicalParty;
        this.homeCounty = homeCounty;
        this.imgUrl = imgUrl;
    }
}
