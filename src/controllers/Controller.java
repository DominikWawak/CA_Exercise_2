package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Candidate;
import models.Election;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.NonCandidate;
import models.Politician;
import utils.GenHash;
import utils.GenList;
import utils.Node;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {                 //im not able to run the programme for some reason so i cant exactly test if any of this that i added works yet

    GenHash<NonCandidate> politicians =new GenHash(13);
   GenHash<Election> elections=new GenHash(13);

   //
    // SET UP TABLE
    //
   @FXML
   private TableView<NonCandidate> polTableView;
   @FXML private TableView<Election> elecTableView;

   @FXML
    private TableColumn<NonCandidate,String> nameColumn,dateColumn,partyColumn,countyColumn;

    @FXML
    private TableColumn<Election,String> typeColumn,locationColumn,elecDateColumn,seatsColumn;

    @FXML ChoiceBox elecType;

   @FXML
   private MenuItem addPolMenu,updatePolMenu;

    @FXML
    private TextField polImg, updatePolImg;

    @FXML
    private Pane cardViewPane,addPoliticianPane,updatePoliticianPane;
    //TODO set visible / not visible

    @FXML
    private ComboBox<String>polName,polParty,polCounty, updatePolParty,updatePolCounty,polToUpdate;


    @FXML  private ComboBox<String>elecLocation;



    @FXML private ComboBox<Integer>noOfSeats;

    @FXML
    private DatePicker polDob,elecDate;

    @FXML private Label typeLbl,locationLbl,dateLbl,seatsLbl;

    @FXML private GenList<Candidate> candidateGenList;

    @FXML
    private Button addPol,addElec,updatePol;

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
    private  ObservableList<NonCandidate> pols = FXCollections.observableArrayList();
    private ObservableList<String> elecList=FXCollections.observableArrayList("neutral","local","European","presidential");


//
    // sample image https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg
    ////
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

        pols.add(pol);
        polTableView.getItems().add(pol);

        politicians.add(politician);
        return politician;
}



    public void addPoliticianGui (ActionEvent event) throws IOException {
        Node<NonCandidate> pol = addPolitician(polName.getValue(),polDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),polParty.getValue(),polCounty.getValue(),polImg.getText());
        names.add(polName.getValue());
        //
        // TODO sort these arrays.
        //
        parties.add(polParty.getValue());
        polName.setItems(names);
        polParty.setItems(parties);
        //
        // FOR UPDATE METHOD
        polToUpdate.setItems(polName.getItems());
        updatePolParty.setItems(polParty.getItems());
        updatePolCounty.setItems(counties);
        //
        polName.getEditor().clear();
        polDob.getEditor().clear();
        polParty.getEditor().clear();
        polCounty.getEditor().clear();




        System.out.println(pol.getKey());

        System.out.println(politicians.getValue(politicians.hashFunction(pol.getKey())).getContents().toString());

        politicians.displayHashTable();

        //Setting the profile view
        setProfileView(pol);





    }

    /**
     * This is a method for creating and adding an election.
     * The first method creates the election object and the node that is added to the hashtable.
     * The key is set to the election type and date.
     * @param type
     * @param location
     * @param date
     * @param noOfSeats
     * @param candidate
     * @return Node of the type Election.
     */



    public Node addElection(String type, String location, String date, int noOfSeats, GenList<Candidate> candidate) {
        Election elec=new Election(type,location,date,noOfSeats,candidate);
        Node<Election> elecNode=new Node<Election>();
        elecNode.setContents(elec);
        elecNode.setKey(elections.hashFunction(elec.electionType+elec.date));

        elections.add(elecNode);
        elecTableView.getItems().add(elec);
        return elecNode;                            //how do u want me to list out the GenList<Candidate>?
    }

    public void addElectionGUI(Action event) throws IOException{
        Node<Election> elecNode=addElection(elecType.getItems().toString(),elecLocation.getValue(),elecDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),noOfSeats.getValue(),candidateGenList);



        elections.displayHashTable();
        setElectionview(elecNode);
    }

    public void setElectionview(Node<Election> node){
        typeLbl.setText(node.getContents().getElectionType());
        locationLbl.setText(node.getContents().getElectionLocation());
        dateLbl.setText(node.getContents().getDate());

        //seatsLbl.setText();
    }


    /**
     * updatePoliticianGui
     *
     * this method uses the method in the NonCandidate Class to get some inputs and update them.
     *
     * @param actionEvent
     */
    public void updatePoliticianGui(ActionEvent actionEvent) {

        //

        //


        Node<NonCandidate> upPol= searchPoliticianByName(polToUpdate.getValue());
        upPol.getContents().update(upPol.getContents(),updatePolParty.getValue(),updatePolCounty.getValue(),updatePolImg.getText());
        setProfileView(upPol);
    }

    public void deletePolitician(){

    }

    /**
     * Makes the card object of the Politician
     * @param node NonCandidate
     */
    public void setProfileView(Node<NonCandidate> node) {
        cName.setText(node.getContents().getName());
        cParty.setText(node.getContents().getPoliticalParty());
        cCounty.setText(node.getContents().getHomeCounty());
        cDate.setText(node.getContents().getDateOfBirth());

        Image image = new Image(node.getContents().getImgUrl());
        cImg.setImage(image);



    }
    public void setProfileViewSelected(){
        //TODO set profile view on selection from the table
    }



    public Node searchPoliticianByName(String Name){

        return politicians.getValue(politicians.hashFunction(Name));


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
        //
        // Table view setters
        //
        nameColumn.setCellValueFactory(new PropertyValueFactory<NonCandidate,String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<NonCandidate,String>("dateOfBirth"));
        partyColumn.setCellValueFactory(new PropertyValueFactory<NonCandidate,String>("politicalParty"));
        countyColumn.setCellValueFactory(new PropertyValueFactory<NonCandidate,String>("homeCounty"));

        typeColumn.setCellValueFactory(new PropertyValueFactory<Election,String>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Election,String>("location"));
        elecDateColumn.setCellValueFactory(new PropertyValueFactory<Election,String>("date"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<Election,String>("Number of Seats"));

        elecType.setItems(elecList);





    }

    /*
     * =============================================================
     * ================ Menu Items ==================================
     * =============================================================
     */

    /**
     * open<PANE>Menu methods
     *
     * make the panes chosen by the menu visible and invisible,
     * ie switch the panes.
     * @param actionEvent
     */



    public void openAddPolMenu(ActionEvent actionEvent) {
        addPoliticianPane.setVisible(true);
        updatePoliticianPane.setVisible(false);
    }

    public void openUpPolMenu(ActionEvent actionEvent) {
        addPoliticianPane.setVisible(false);
        updatePoliticianPane.setVisible(true);
    }
}
