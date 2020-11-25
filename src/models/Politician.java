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

        this.politicalParty = (politicalParty.isEmpty()) ? "Independent":politicalParty;
        this.homeCounty = homeCounty;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    public String getHomeCounty() {
        return homeCounty;
    }

    public void setHomeCounty(String homeCounty) {
        this.homeCounty = homeCounty;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    //public abstract void update(Politician politician, String politicalParty, String homeCounty, String imgUrl);

    @Override
    public String toString() {
        return "Politician{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", politicalParty='" + politicalParty + '\'' +
                ", homeCounty='" + homeCounty + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }


    public abstract void update( Politician pol,String politicalParty, String homeCounty, String imgUrl);
}
