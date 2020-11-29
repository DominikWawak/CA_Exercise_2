package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import models.Candidate;
import models.Election;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.NonCandidate;

import models.Politician;
//import sun.reflect.generics.tree.Tree;
import utils.GenHash;
import utils.GenList;
import utils.Node;


import javax.swing.tree.TreeNode;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class Controller implements Initializable {                 //im not able to run the programme for some reason so i cant exactly test if any of this that i added works yet

    GenHash<Politician> politicians =new GenHash(3);

   GenHash<Election> elections=new GenHash(13);

   //
    // SET UP TABLE
    //
   @FXML
   private TableView<Node<Politician>> polTableView;
   @FXML private TreeTableView<Election> elecTableView;
   @FXML private TreeTableView<Election> elecTableView2;
   @FXML public TreeView<String> canListView;
   @FXML private TreeItem<String> rootItem;
   @FXML private ToggleButton toggleViewElection;


  @FXML
    private TableColumn<Node<Politician>,String> nameColumn,dateColumn,partyColumn,countyColumn;

    @FXML  private TreeTableColumn<Election,String> typeColumn,locationColumn,elecDateColumn,seatsColumn;
    @FXML private TreeTableColumn<Election,String> typeColumn2,locationColumn2,elecDateColumn2,seatsColumn2;

    @FXML ChoiceBox<String> elecType,selectPolitician;

   @FXML
   private MenuItem addPolMenu,updatePolMenu;

    @FXML
    private TextField polImg, updatePolImg;

    @FXML
    private Pane cardViewPane,addPoliticianPane,updatePoliticianPane,addElectionPane,addCandidatePane,electionTreeViewPane;
    //TODO set visible / not visible

    @FXML
    private ComboBox<String>polName,polParty,polCounty, updatePolParty,updatePolCounty,polToUpdate,partyStoodFor;


    @FXML  private ComboBox<String>elecLocation;

    @FXML
    private Spinner<Integer> noOfSeats,totalVotesCandidate;

    //@FXML private ComboBox<Integer>noOfSeats;

    @FXML
    private DatePicker polDob,elecDate;

    @FXML private Label typeLbl,locationLbl,dateLbl,seatsLbl;



    @FXML
    private Button addPol,addElec,updatePol,deletePol,viewPol,addCandidate;

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
    private  ObservableList<Politician> pols = FXCollections.observableArrayList();
  //  private  ObservableList<> pols = FXCollections.observableArrayList();
    private ObservableList<String> elecList=FXCollections.observableArrayList("general","local","European","presidential");




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
        Politician pol = new NonCandidate(name,dob,party,homeCounty,imgUrl);
        Node<Politician> politician= new Node<Politician>();
        politician.setContents(pol);
        politician.setKey(politicians.hashFunction(pol.getName()));

        pols.add(pol);
         polTableView.getItems().add(politician);

        politicians.add(politician);
        return politician;
}



    public void addPoliticianGui (ActionEvent event) throws IOException {
        Node<Politician> pol = addPolitician(polName.getValue(),polDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),polParty.getValue(),polCounty.getValue(),polImg.getText());
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
        selectPolitician.setItems(names);
        partyStoodFor.setItems(parties);
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



    public Node addElection(String type, String location, String date, int noOfSeats, GenList<Politician> candidate) {
        Election elec=new Election(type,location,date,noOfSeats,candidate);
        Node<Election> elecNode=new Node<Election>();
        elecNode.setContents(elec);
        elecNode.setKey(elections.hashFunction(elec.electionType+elec.date));
        TreeItem<Election> elecItem =new TreeItem<>(elec);
       elecTableView.getRoot().getChildren().add(elecItem);

        elections.add(elecNode);
        ///elecTableView.getItems().add(elec);         //going to get errors if ran because i havent set the labels so they can be seen in scenebuilder can change to public if needed
        return elecNode;                            // the genList will be a drop down, so this needs to be a TREE TABLE VIEW
    }


    public void addElectionGUI(ActionEvent actionEvent) {
        GenList<Politician> candidateGenList = new GenList<>();
        Node<Election> elecNode=addElection(elecType.getValue().toString(),elecLocation.getValue(),elecDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),noOfSeats.getValue(),candidateGenList);
        System.out.println(elecNode.getKey());
        System.out.println(elections.getValue(elections.hashFunction(elecNode.getContents().electionType+elecNode.getContents().date)).getContents().toString());
        elections.displayHashTable();
    }

    public void addCandidateToElectionGui(ActionEvent actionEvent) {
        Node<Politician> forCandidate = politicians.getValue(politicians.hashFunction(selectPolitician.getValue()));
        Politician candidate = new Candidate(selectPolitician.getValue(),forCandidate.getContents().getDateOfBirth(),forCandidate.getContents().getPoliticalParty(),partyStoodFor.getValue(),forCandidate.getContents().getHomeCounty(),forCandidate.getContents().getImgUrl(),totalVotesCandidate.getValue());
       forCandidate.setContents(candidate);

        Election election = elecTableView2.getSelectionModel().getSelectedItem().getValue();

        election.getCandidateGenList().addElement(candidate);

        //
        //New List View
        //


/*
        TreeItem<String> electionItem = new TreeItem<String>(election.toString());
        if(!(canListView.getRoot().getChildren().contains(electionItem))) {

            canListView.getRoot().getChildren().add(electionItem);
            electionItem.getChildren().add(new TreeItem<>(candidate.toString()));
        }
        else {
            for(TreeItem<String> item: canListView.getRoot().getChildren()){
                if(election.toString().equals(item.getValue())){
                    item.getChildren().add(new TreeItem<>(candidate.toString()));
                }
            }
        }*/






        TreeItem<String> date=new TreeItem<>(election.date);
        TreeItem<String> general=new  TreeItem<String>("General");
        TreeItem<String> local=new  TreeItem<String>("Local");
        TreeItem<String> european=new TreeItem<String>("European");
        TreeItem<String> presidential=new TreeItem<String>("Presidential");
        rootItem.getChildren().add(date);
        date.getChildren().add(general);
        date.getChildren().add(local);
        date.getChildren().add(european);
        date.getChildren().add(presidential);


      if(election.date.matches(canListView.getRoot().getChildren().toString())) {
          if (election.electionType.matches("general")) {
              general.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else if (election.electionType.matches("local")) {
              local.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else if (election.electionType.matches("European")) {
              european.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else if (election.electionType.matches("presidential")) {
              presidential.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else System.out.println("no");
      }else{
          date=new TreeItem<>(election.date);
          if (election.electionType.matches("general")) {
              general.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else if (election.electionType.matches("local")) {
              local.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else if (election.electionType.matches("European")) {
              european.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else if (election.electionType.matches("presidential")) {
              presidential.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
          } else System.out.println("no");
      }


        canListView.getRoot().getChildren();


        System.out.println(election.getCandidateGenList().head.getContents().toString());
    }




    /**
     * updatePoliticianGui
     *
     * this method uses the method in the NonCandidate Class to get some inputs and update them.
     *
     * @param actionEvent
     */
    public void updatePoliticianGui(ActionEvent actionEvent) {


        Node<Politician> upPol= searchPoliticianByName(polToUpdate.getValue());
        upPol.getContents().update(upPol.getContents(),updatePolParty.getValue(),updatePolCounty.getValue(),updatePolImg.getText());
        setProfileView(upPol);
        polTableView.refresh();
    }

    public void deletePoliticianGui(ActionEvent actionEvent) {

        //ObservableList<Node<Politician>> selected,allPoliticians;
     // allPoliticians = polTableView.getItems();
     Node selected = polTableView.getSelectionModel().getSelectedItem();
     // for(Node<Politician> politicianNode : selected){
            polTableView.getItems().remove(selected);
            politicians.remove(selected.getKey());
    //  }

      politicians.displayHashTable();
      System.out.println(selected.getContents().toString());

    }

    /**
     * Makes the card object of the Politician
     * @param node NonCandidate
     */
    public void setProfileView(Node<Politician> node) {
        cName.setText(node.getContents().getName());
        cParty.setText(node.getContents().getPoliticalParty());
        cCounty.setText(node.getContents().getHomeCounty());
        cDate.setText(node.getContents().getDateOfBirth());


        try{
            Image image = new Image(node.getContents().getImgUrl());
            cImg.setImage(image);


        }
        catch (java.lang.IllegalArgumentException ex){
           Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Joe_Cunningham%2C_Official_Porrtait%2C_116th_Congress.jpg/1200px-Joe_Cunningham%2C_Official_Porrtait%2C_116th_Congress.jpg");
            cImg.setImage(image);
            System.out.println("Default image set");
        }



    }

    public void displaySelectedPol(ActionEvent actionEvent) {

        Node<Politician> nodeSelected =  polTableView.getSelectionModel().getSelectedItem();
        if(nodeSelected!= null) {
            nodeSelected.setContents(nodeSelected.getContents());
            setProfileView(nodeSelected);
        }
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
        // Table view Initialise
        //
        //Allow multiple selection
        polTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getName()));
      dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getDateOfBirth()));;
      partyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getPoliticalParty()));
        countyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getHomeCounty()));

        //Election table


        TreeItem<Election> root = new TreeItem<Election>( new Election("type","Location","date",0,null));
        elecTableView.setRoot(root);
        elecTableView.setShowRoot(false);
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getElectionType()));
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getElectionLocation()));
        elecDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getDate()));
        seatsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getNumberOfSeats()+""));


        elecTableView2.setRoot(root);
        elecTableView2.setShowRoot(false);
        typeColumn2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getElectionType()));
        locationColumn2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getElectionLocation()));
        elecDateColumn2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getDate()));
        seatsColumn2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getNumberOfSeats()+""));

        elecType.setItems(elecList);
        elecLocation.setItems(counties);





        //
        // Spinners set up
        //

        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60,10,1);
        noOfSeats.setValueFactory(spinner);
        totalVotesCandidate.setValueFactory(spinner);

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


        rootItem=new TreeItem<String>("Election Type");
        canListView.setRoot(rootItem);




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

    public void toggleView(){
        if(toggleViewElection.isSelected()){
            electionTreeViewPane.setVisible(true);
        }else{
            electionTreeViewPane.setVisible(false);
        }
    }


    public void openAddElectionMenu(ActionEvent actionEvent) {
        addElectionPane.setVisible(true);
        addCandidatePane.setVisible(false);

    }

    public void openAddCandidateMenu(ActionEvent actionEvent) {
        addElectionPane.setVisible(false);
        addCandidatePane.setVisible(true);
    }




}
