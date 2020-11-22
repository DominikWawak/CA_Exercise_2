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