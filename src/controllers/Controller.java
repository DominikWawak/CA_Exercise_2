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
      //  politician.setKey(pol.getName()+imgUrl);
        politicians.add(politician);
}


    public void deletePolitician(){

    }

    public void searchPoliticianByName(){

    }
    public void searchPoliticianByParty(){

    }
    public void searchPoliticianByLocation(){

    }
    public void sort(){

    }

    public String listPoliticians(){
        String list = "";
        for(Node politician : politicians.hashTable){
            if(politician != null){
                list = list + " " + politician.getContents().toString()+"\n";
            }
        }
        return list;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
   /*    addPolitician("Dominik","2020/04/13","","wicklow","www.pic.ie");
        addPolitician("kinimoD","2020/04/13","","wicklow","www.pic.ie");
        addPolitician("Dominik","2020/04/13","","wicklow","www.pic.ie");
        addPolitician("Dominik","2020/04/13","","wicklow","www.pic.ie");
        addPolitician("Dominik","2020/04/13","","wicklow","www.pic.ie");

       //politicians.displayHashTable();
       System.out.println("The chosen     "+politicians.getValue(politicians.hashFunction("kinimoDwww.pic.ie")).getContents().toString());
       System.out.println(listPoliticians());


    */

    }



}
