package controllers;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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
//import sun.reflect.generics.tree.Tree;
import utils.GenHash;
import utils.GenList;
import utils.Node;


import javax.swing.tree.TreeNode;
import java.awt.event.ItemEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
   @FXML private TableView<Node<Election>>elecTableView;

   @FXML public TreeView<String> canListView;
   @FXML private TreeItem<String> rootItem,general,local,european,presidential;
   @FXML private ToggleButton toggleViewElection;


  @FXML
    private TableColumn<Node<Politician>,String> nameColumn,dateColumn,partyColumn,countyColumn;

    @FXML  private TableColumn<Node<Election>,String> typeColumn,locationColumn,elecDateColumn,seatsColumn;


    @FXML ChoiceBox<String> elecType,selectPolitician;

   @FXML
   private MenuItem addPolMenu,updatePolMenu,loadMenu,saveMenu;

    @FXML
    private TextField polImg, updatePolImg,searchText;

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
    private Button addPol,addElec,updatePol,deletePol,viewPol,addCandidate,searchPolsButton,sortPolButton,viewAllPols;

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
    private  ObservableList<Node<Politician>> pols = FXCollections.observableArrayList();

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

        pols.add(politician);
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

        elections.add(elecNode);
        elecTableView.getItems().add(elecNode);
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



    public void deleteElection(ActionEvent event){
        Node selected=elecTableView.getSelectionModel().getSelectedItem();
        elecTableView.getItems().remove(selected);

    }





    public void deleteElection(ActionEvent event){
        TreeItem selected=elecTableView.getSelectionModel().getSelectedItem();
    }



    public void addCandidateToElectionGui(ActionEvent actionEvent) {
        Node<Politician> forCandidate = politicians.getValue(politicians.hashFunction(selectPolitician.getValue()));
        Politician candidate = new Candidate(selectPolitician.getValue(),forCandidate.getContents().getDateOfBirth(),forCandidate.getContents().getPoliticalParty(),partyStoodFor.getValue(),forCandidate.getContents().getHomeCounty(),forCandidate.getContents().getImgUrl(),totalVotesCandidate.getValue());
       forCandidate.setContents(candidate);


        Election election = elecTableView.getSelectionModel().getSelectedItem().getContents();



        election.getCandidateGenList().addElement(candidate);

        //
        //New List View alternative code
        //

     ///no
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
        TreeItem<String> date = new TreeItem<>(election.getDate());


        Boolean found = false;
        for (TreeItem<String> x : canListView.getRoot().getChildren()) {
            if (election.getElectionType().toUpperCase().equals(x.getValue().toUpperCase())) {
                for (TreeItem<String> y : x.getChildren()) {
                    if(y!=null) {
                        if (y.getValue().equals(election.getDate())) {
                            y.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));
                            found = true;
                        }
                    }else {
                        for (TreeItem<String> s : canListView.getRoot().getChildren()) {

                            if (election.getElectionType().toUpperCase().equals(s.getValue().toUpperCase())) {
                                s.getChildren().add(date);
                                date.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));

                            }
                        }
                    }

                }


            }
        }
        if (!found) {
            for (TreeItem<String> x : canListView.getRoot().getChildren()) {

                if (election.getElectionType().toUpperCase().equals(x.getValue().toUpperCase())) {
                    x.getChildren().add(date);
                    date.getChildren().add(new TreeItem<>(forCandidate.getContents().getName()));


                }
            }
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

    public ObservableList<Node<Politician>> shellSort(ObservableList<Node<Politician>> toSort){
        //int[] gaps = {1};
        ArrayList<Integer> gaps = new ArrayList<>();
        int size = toSort.size();

        while(size>1){
            size = size/2;

            gaps.add(size/2);
        }



        for(int g : gaps){
            for(int e =g;e<toSort.size();e++){
                Node<Politician> elem = toSort.get(e);
                int i;
                for(i=e;i>=g && toSort.get(i-g).getContents().getPoliticalParty().compareTo(elem.getContents().getPoliticalParty())<0;i-=g){
                        toSort.set(i,toSort.get(i-g)) ;
                }
                toSort.set(i,elem);





            }
        }

           return toSort;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {




        cardViewPane.setStyle("-fx-border-color: black");
        polCounty.setItems(counties);
        elecType.setItems(elecList);
        elecLocation.setItems(counties);
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




        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getElectionType()));
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getElectionLocation()));
        elecDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getDate()));
        seatsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContents().getNumberOfSeats()+""));






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

         general=new  TreeItem<String>("General");
        local=new  TreeItem<String>("Local");
        european=new TreeItem<String>("European");
        presidential=new TreeItem<String>("Presidential");
        rootItem.getChildren().add(general);
        rootItem.getChildren().add(local);
        rootItem.getChildren().add(european);
        rootItem.getChildren().add(presidential);
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



    public void loadPoliticians() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("elecSysPoliticians.xml"));
        politicians=(GenHash<Politician>) is.readObject();

        is.close();


    }

    public void savePoliticians() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("elecSysPoliticians.xml"));

        ((ObjectOutputStream) out).writeObject(politicians);
        out.close();
    }


    public void loadApp(ActionEvent actionEvent) throws Exception {
        try {
            loadPoliticians();
            reloadTable();

        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    public void saveApp(ActionEvent actionEvent) throws Exception {
        try {
            savePoliticians();

        } catch (Exception e) {
            System.err.println("Error saving the file: " + e);
        }
    }

    public void reloadTable(){
        for(Node x : politicians.hashTable){
            if(x!= null && x.getContents()!="tomb"){
                polTableView.getItems().add(x);
                pols.add(x);
            }
        }
    }

    public void sortPoliticians(ActionEvent actionEvent) {

        polTableView.setItems(shellSort(polTableView.getItems()));
    }

    public void searchPolTableView(ActionEvent actionEvent) {
        ObservableList<Node<Politician>> searched = FXCollections.observableArrayList();;
        for(Node<Politician> politicianNode : polTableView.getItems()){
            if(searchText.getText().toUpperCase().indexOf(politicianNode.getContents().getName().toUpperCase())>-1){
                searched.add(politicianNode);
            }
        }
        polTableView.getItems().clear();
        polTableView.setItems(searched);
    }

    public void viewAllPoliticiansInTableView(ActionEvent actionEvent) {
        polTableView.setItems(pols);
    }
}
