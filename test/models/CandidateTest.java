package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GenList;

import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {
    Politician p1 ;
    Politician p2 ;
    Politician p3 ;
    GenList<Election> electionGenList;

    @BeforeEach
    void setUp() {
        electionGenList=new GenList<>();
        p1 = new Candidate("d","20/06/1980","Blue Party","Green Party","wicklow","pic.ie",22,null,electionGenList);

        //p2 = new Candidate("Xavier","20/06/1980","Blue Party","wicklow","pic.ie");
     //   p3 = new Candidate("ADA","20/06/1980","Red Party","wicklow","pic.ie");

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getElections() {
    }

    @Test
    void getPartyStoodFor() {
    }
}