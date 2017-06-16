package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;


public class Controller {

    private final int MINATLYGINIMAS = 380;
    private final double GYVENTOJUPAJAMUMOKESCIOTARIFAS = 0.15;
    private final double SOCIALINISDRAUDIMAS = 0.03;
    private final double PENSIJOSKAUPIMAS = 0.02;
    private final double PRIVALOMASISSVEIKATOSDRAUDIMAS = 0.06;


    @FXML
    private TextField input;
    @FXML
    private ChoiceBox kiekVaikuAuginateChoiceBox;
    @FXML
    private ChoiceBox arAuginuVienasChoiceBox;
    @FXML
    private ChoiceBox socialineDraudimoGrupeChoiceBox;
    @FXML
    private ChoiceBox pensijosKaupimasChoiceBox;
    @FXML
    private RadioButton radioButtonNe;
    @FXML
    private RadioButton radioButtonTaip;
    @FXML
    private Label kiekAuginateVaikuLabel;
    @FXML
    private Label arAuginuVienasLabel;
    @FXML
    private Label pagrindinioNPDReiksme;

    ObservableList<String> kiekVaikuAuginateList = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    ObservableList<String> arAuginuVienasList = FXCollections.observableArrayList("Vienas", "Dviese");
    ObservableList<String> socialineDraudimoGrupeList = FXCollections.observableArrayList("Ne", "(30-55)%", "(0-25)%");
    ObservableList<String> pensijosKaupimasList = FXCollections.observableArrayList("Ne", "Taip");

    @FXML
    public void initialize() {
        kiekVaikuAuginateChoiceBox.setItems(kiekVaikuAuginateList);
        kiekVaikuAuginateChoiceBox.getSelectionModel().selectFirst();

        arAuginuVienasChoiceBox.setItems(arAuginuVienasList);
        arAuginuVienasChoiceBox.getSelectionModel().selectFirst();

        socialineDraudimoGrupeChoiceBox.setItems(socialineDraudimoGrupeList);
        socialineDraudimoGrupeChoiceBox.getSelectionModel().selectFirst();

        pensijosKaupimasChoiceBox.setItems(pensijosKaupimasList);
        pensijosKaupimasChoiceBox.getSelectionModel().selectFirst();

        // ((Pane) kiekAuginateVaikuLabel.getParent()).getChildren().remove(kiekAuginateVaikuLabel);
        kiekAuginateVaikuLabel.setVisible(false);
        kiekVaikuAuginateChoiceBox.setVisible(false);
        arAuginuVienasLabel.setVisible(false);
        arAuginuVienasChoiceBox.setVisible(false);
    }

    public void radioAction(ActionEvent e) {

        if (radioButtonNe.isSelected()) {
            kiekAuginateVaikuLabel.setVisible(false);
            kiekVaikuAuginateChoiceBox.setVisible(false);
            arAuginuVienasLabel.setVisible(false);
            arAuginuVienasChoiceBox.setVisible(false);
        }
        if (radioButtonTaip.isSelected()) {
            kiekAuginateVaikuLabel.setVisible(true);
            kiekVaikuAuginateChoiceBox.setVisible(true);
            arAuginuVienasLabel.setVisible(true);
            arAuginuVienasChoiceBox.setVisible(true);
        }
    }

    public void reiksmesIvedimas(ActionEvent e) {
        int atlyginimasIRankas;
        atlyginimasIRankas = Integer.parseInt(input.getText());
        //pagrindinioNPDReiksme.setText(String.valueOf(atlyginimasIRankas));

    }
}
