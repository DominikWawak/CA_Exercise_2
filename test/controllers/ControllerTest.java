package controllers;

import models.NonCandidate;
import models.Politician;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GenHash;
import utils.Node;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller=new Controller();
    GenHash<Politician> politicians =new GenHash(13);
    Politician p1 ;
    Politician p2 ;
    Politician p3 ;
    Node<Politician> n1;
    Node<Politician> n2;
    Node<Politician> n3;


    @BeforeEach
    void setUp() {
       p1 = new NonCandidate("John","20/06/1980","Green Party","wicklow","pic.ie");
      p2 = new NonCandidate("Xavier","20/06/1980","Green Party","wicklow","pic.ie");
         p3 = new NonCandidate("ADA","20/06/1980","Green Party","wicklow","pic.ie");
         n1 = new Node<>();
         n2 = new Node<>();
         n3 = new Node<>();
         n1.setContents(p1);
         n2.setContents(p2);
         n3.setContents(p3);
        politicians.add(n1);
        politicians.add(n2);
        politicians.add(n3);


    }

    @AfterEach
    void tearDown() {
        politicians=null;
    }

    @Test
    void addPolitician() {
        //TODO
      // Node pol = politicians.getValue(politicians.hashFunction(politicians.hashFunction("John")));
       // assertEquals(controller.addPolitician("John","20/06/1980","Green Party","wicklow","pic.ie"),pol);

    }

    @Test
    void addElection() {
        //TODO
    }


    @Test
    void deleteElection() {
        //TODO
    }


    @Test
    void deletePolitician() {
        //TODO
    }


    @Test
    void addCandidateToElection() {
        //TODO
    }

    @Test
    void shellSortTest(){
        System.out.println(politicians);
        System.out.println(politicians.makeList());
        Politician three = politicians.makeList().getAtIndex(0).getContents();
        Politician one =politicians.makeList().getAtIndex(1).getContents();
        Politician two = politicians.makeList().getAtIndex(2).getContents();
        assertEquals(one,controller.myOwnShellSort(politicians.makeList(), Comparator.comparing(a -> a.getContents().getName())).head.getContents());
        assertEquals(two,controller.myOwnShellSort(politicians.makeList(), Comparator.comparing(a -> a.getContents().getName())).head.next.getContents());
        assertEquals(three,controller.myOwnShellSort(politicians.makeList(), Comparator.comparing(a -> a.getContents().getName())).head.next.next.getContents());
    }




    @Test
    void quickSortTest(){
        //TODO
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

