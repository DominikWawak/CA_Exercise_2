package controllers;

import models.NonCandidate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GenHash;
import utils.Node;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller=new Controller();
    GenHash<NonCandidate> politicians =new GenHash(13);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        politicians=null;
    }

    @Test
    void addPolitician() {

       // Node pol = politicians.getValue(politicians.hashFunction(politicians.hashFunction("John")));
       // assertEquals(controller.addPolitician("John","20/06/1980","Green Party","wicklow","pic.ie"),pol);

    }
}

/*
  //
        //Some Testing
        NonCandidate pol = new NonCandidate("Tony Stark","20/02/22/","","Laois","Mypic.ie");
        Node<Politician> p=new Node<>();
        p.setContents(pol);
        NonCandidate pol2 = new NonCandidate("Tony bark","20/02/22/","","Laois","Mypic.ie");
        Node<Politician> p2=new Node<>();
        p2.setContents(pol2);

        politicians.add(p);
        GenList<Politician> candidates = new GenList<>();
        Election el = new Election("Local","Waterford","30-20-2992",22,candidates);
        el.getCandidateGenList().addElement(pol2);
        el.getCandidateGenList().addElement(pol);


        System.out.println(el.getCandidateGenList().head.getContents().toString());
        el.getCandidateGenList().removeElement(p,el.getCandidateGenList());
        System.out.println(el.getCandidateGenList().head.getContents().toString());

        =============================
         /* NonCandidate pol = new NonCandidate("Tony Stark","20/02/22/","","Laois","Mypic.ie");
        Node<Politician> p=new Node<>();
        p.setContents(pol);

        politicians.add(p);
        politicians.add(p);
        politicians.add(p);
        politicians.displayHashTable();

        */

//

//

