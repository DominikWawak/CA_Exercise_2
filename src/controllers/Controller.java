package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import models.Candidate;
import models.Election;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.NonCandidate;
import models.Politician;
import utils.GenHash;
import utils.GenList;
import utils.Node;


import javax.swing.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    GenHash<NonCandidate> politicians =new GenHash(13);
   GenHash<Election> elections=new GenHash<>(13);

    @FXML
    private TextField polImg;

    @FXML
    private Pane cardViewPane;

    @FXML
    private ComboBox<String>polName,polParty,polCounty;


    @FXML  private ComboBox<String>elecType,elecLocation;

    @FXML private ComboBox<Integer>noOfSeats;

    @FXML
    private DatePicker polDob,elecDate;

    @FXML private GenList<Candidate> candidateGenList;

    @FXML
    private Button addPol,addElec;

    @FXML
    private Label cName, cDate,cParty,cCounty;
    @FXML
    private ImageView cImg;

    private ObservableList<String> names =  FXCollections.observableArrayList();
    private ObservableList<String> counties =  FXCollections.observableArrayList("Antrim",
            "Armagh",
            "Carlow",
            "Cavan",
            "Clare",
            "Cork",
            "Derry",
            "Donegal",
            "Down",
            "Dublin",
            "Fermanagh",
            "Galway",
            "Kerry",
            "Kildare",
            "Kilkenny",
            "Laois",
            "Leitrim",
            "Limerick",
            "Longford",
            "Louth",
            "Mayo",
            "Meath",
            "Monaghan",
            "Offaly",
            "Roscommon",
            "Sligo",
            "Tipperary",
            "Tyrone",
           "Waterford",
            "Westmeath",
            "Wexford",
            "Wicklow");
    private ObservableList<String> parties =  FXCollections.observableArrayList();


    /**
     * addPoloticianMethod and addPoliticianGui
     *
     * This is a method for adding politicians that are non candidates of a election.
     * The first method creates the politician object and the node that is added to the hashtable.
     * The key is set to the name of the politician.
     * @param name
     * @param dob
     * @param party
     * @param homeCounty
     * @param imgUrl
     * @return Node of type Politician
     */

    public Node addPolitician(String name,String dob,String party,String homeCounty,String imgUrl){
        NonCandidate pol = new NonCandidate(name,dob,party,homeCounty,imgUrl);
        Node<NonCandidate> politician= new Node<NonCandidate>();
        politician.setContents(pol);
        politician.setKey(politicians.hashFunction(pol.getName()));

        politicians.add(politician);
        return politician;
}
    @FXML


    void addPoliticianGui(ActionEvent event) {
        Node<NonCandidate> pol = addPolitician(polName.getValue(),polDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),polParty.getValue(),polCounty.getValue(),polImg.getText());
        names.add(polName.getValue());
        //
        // TODO sort these arrays.
        //
        parties.add(polParty.getValue());
        polName.setItems(names);
        polParty.setItems(parties);
        polName.getEditor().clear();
        polDob.getEditor().clear();
        polParty.getEditor().clear();
        polCounty.getEditor().clear();




        System.out.println(pol.getKey());

        System.out.println(politicians.getValue(politicians.hashFunction(pol.getKey())).getContents().toString());

        politicians.displayHashTable();

        //Setting the profile view
        setProfileView(polName.getValue(),polDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),polParty.getValue(),polCounty.getValue(),polImg.getText());





    }

    //
    //
    // TODO Make a add for elections simmillar to my of politicians
    //
    // key for election is election type + date (strings)
    //
    // Try to comment
    //
    // test with System.out and then make a simple method in tests
    //
    //

    public Node addElection(String type, String location, String date, int noOfSeats, GenList<Candidate> candidate) {
        Election elec=new Election(type,location,date,noOfSeats,candidate);
        Node<Election> elecNode=new Node<Election>();
        elecNode.setContents(elec);
        elecNode.setKey(elections.hashFunction(elec.electionType+elec.date));

        elections.add(elecNode);
        return elecNode;
    }

    public void addElectionGUI(Action event){
        Node<Election> eleccNode=addElection(elecType.getValue(),elecLocation.getValue(),elecDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),noOfSeats.getValue(),candidateGenList);




        elections.displayHashTable();
    }


    public void deletePolitician(){

    }

    public void setProfileView(String name,String DOB,String party, String county,String img) {
        cName.setText(name);
        cParty.setText(party);
        cCounty.setText(county);
        cDate.setText(DOB);

        Image image = new Image(img);
        cImg.setImage(image);



    }



    public void searchPoliticianByName(){

    }
    public void searchPoliticianByParty(){

    }
    public void searchPoliticianByLocation(){

    }
    public void sort(){

    }

    /**\
     * listPoliticians
     *
     * this method loops through the Nodes in the hashtable returns all that are not null
     * @return String list
     */

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
        cardViewPane.setStyle("-fx-border-color: black");
        polCounty.setItems(counties);







    }



}
