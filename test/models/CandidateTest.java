package models;

import controllers.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GenList;

import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {
    Controller controller = new Controller();
   Candidate p1 ;
    Politician p2 ;
   Candidate p3 ;
    GenList<Election> electionGenList;
    GenList<Politician> candidates;
    Election e1;

    @BeforeEach
    void setUp() {
        electionGenList=new GenList<>();
        candidates = new GenList<>();
        p1 = new Candidate("Billy","20/06/1980","Blue Party","Green Party","wicklow","pic.ie",22,null,electionGenList);

        p2 = new Candidate("Jill","27/11/1984","Red Party","Green Party","wicklow","pic.ie",22,null,electionGenList);
        p3 = new Candidate("Phill","20/03/1990","Good Party","Green Party","wicklow","pic.ie",22,null,electionGenList);
        e1 = new Election("General","Derry","5/06/2021",2,candidates);
        electionGenList.addElement(e1);



    }

    @AfterEach
    void tearDown() {
        p2= null;
        p1=p3=null;
        candidates = null;
        electionGenList=null;
        e1=null;
    }

    @Test
    void getElections() {
        assertEquals(e1,p1.getElections().head.getContents());

    }

    @Test
    void getPartyStoodFor() {
        assertEquals("Green Party",p3.getPartyStoodFor());
    }
}