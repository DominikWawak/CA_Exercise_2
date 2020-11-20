package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.NonCandidate;
import models.Politician;
import utils.GenHash;
import utils.Node;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    GenHash<NonCandidate> politicians =new GenHash(13);

    @FXML
    private TextField polName,polParty,polCounty,polImg;

    @FXML
    private DatePicker polDob;

    @FXML
    private Button addPol;

    @FXML
    private Label cName, cDate,cParty,cCounty;
    @FXML
    private ImageView cImg;





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
        Node<NonCandidate> pol = addPolitician(polName.getText(),polDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),polParty.getText(),polCounty.getText(),polImg.getText());

        System.out.println(pol.getKey());

        System.out.println(politicians.getValue(politicians.hashFunction(pol.getKey())).getContents().toString());

        politicians.displayHashTable();

        //Setting the profile view
        setProfileView(polName.getText(),polDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),polParty.getText(),polCounty.getText(),polImg.getText());





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

       // String [] words = {"Wicklow","Waterford","Kilkenny"};
       // TextFields.bindAutoCompletion(polCounty,words);

        //
        //LAST STOP
        //
        //




    }



}
