package utils;

import models.NonCandidate;

import static org.junit.jupiter.api.Assertions.*;

class GenHashTest {

     GenHash<NonCandidate> politicians = new GenHash<NonCandidate>(11);
    Node<NonCandidate> politician;
    Node<NonCandidate> politician2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        NonCandidate pol = new NonCandidate("Tony Stark","20/02/22/","","Laois","Mypic.ie");

        politician= new Node<NonCandidate>();
        politician.setContents(pol);
        politician.setKey(politicians.hashFunction(pol.getName()));




        ///////////////////////////////////////////////
        NonCandidate pol2 = new NonCandidate("Tony Stark","20/02/22/","","Laois","Mycip.ie");

        politician2= new Node<NonCandidate>();
        politician2.setContents(pol);
        politician2.setKey(politicians.hashFunction(pol.getName()+pol.getImgUrl()));

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        politicians=null;
    }

    @org.junit.jupiter.api.Test
    void hashFunction() {
        assertEquals(politicians.hashFunction("Tony Stark"),politicians.hashFunction(politician.getKey()));
    }

    @org.junit.jupiter.api.Test
    void add() {
        //Testing for collisions with the same hashcode
        politicians.add(politician);
        politicians.add(politician2);
        assertEquals(politician,politicians.getValue(politicians.hashFunction(politician.getKey())));
        assertEquals(politician2,politicians.getValue(politicians.hashFunction(politician2.getKey())));

    }

    @org.junit.jupiter.api.Test
    void getValue() {
        //Tested above,
    }
}