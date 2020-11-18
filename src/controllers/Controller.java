package controllers;

import javafx.fxml.Initializable;
import models.NonCandidate;
import models.Politician;
import utils.GenHash;
import utils.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    GenHash<NonCandidate> politicians =new GenHash(13);


    public void addPolitician(String name,String dob,String party,String homeCounty,String imgUrl){
        NonCandidate pol = new NonCandidate(name,dob,party,homeCounty,imgUrl);
        Node<NonCandidate> politician= new Node<NonCandidate>();
        politician.setContents(pol);
        politician.setKey(pol.getName());
        politicians.add(politician);
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       addPolitician("Dominik","2020/04/13","","wicklow","www.pic.ie");
       politicians.displayHashTable();
       System.out.println(politicians.getValue(politicians.hashFunction("Dominik")).getContents().toString());

    }
}
