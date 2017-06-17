package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    // KONSTANTOS
    private static final int MINATLYGINIMAS = 380;
    private static final double GYVENTOJUPAJAMUMOKESCIOTARIFAS = 0.15;
    private static final double SOCIALINISDRAUDIMAS = 0.03;
    private static final double PENSIJOSKAUPIMAS = 0.02;
    private static final double PRIVALOMASISSVEIKATOSDRAUDIMAS = 0.06;
    private static final float VALSTIBINIOSOCIALINIODRAUDIMOTARIFAS = 0.3098f;
    private static final float IMOKOSIGARANTINIFONDADARBDAVIUIDRAUDIMOTARIFAS = 0.002f;

    //naudojami kintamieji skaciavimams
    private int atlyginimasAntPopieriaus;
    private double pagrindinisNPD;
    private double papildomasNPD;
    private double visasNPD;
    private int vaikuSkaicius;
    private int augintojuSkaicius;
    private double gyventojuPajamuMokestis;
    private double sveikatosDraudimas;
    private double socialinisDraudimas;
    private double pensijosKaupimas;
    private double sodrosImokaDarbdaviui;
    private double imokaIGarantiniFondaDarbdaviui;
    private double atlyginimasIRankas;
    private double darboVietosKainaDarbdaviui;

    //Naudojami elementai veiksmams
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
    @FXML
    private Label papildomoNPDReiksme;
    @FXML
    private Label visoNPDReiksme;
    @FXML
    private Label pajamuMokescioReiksme;
    @FXML
    private Label sveikatosDraudimoReiksme;
    @FXML
    private Label socialinioDraudimoReiksme;
    @FXML
    private Label pensijosKaupimoReiksme;
    @FXML
    private Label sodrosImokosDarbdaviuiReiksme;
    @FXML
    private Label imokosIGarantiniFondaDarbdaviuiReiksme;
    @FXML
    private Label darboVietosKainosReiksme;
    @FXML
    private Label atlyginimoAntPopieriausReiksme;

    // nustatomi listai ir ju reiksmes kurie bus naudojami choice BOX pasirinikimams
    ObservableList<String> kiekVaikuAuginateList = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    ObservableList<String> arAuginuVienasList = FXCollections.observableArrayList("Vienas", "Dviese");
    ObservableList<String> socialineDraudimoGrupeList = FXCollections.observableArrayList("Ne", "(30-55)%", "(0-25)%");
    ObservableList<String> pensijosKaupimasList = FXCollections.observableArrayList("Ne", "Taip");


    // atliekama paleidziant programą
    @FXML
    public void initialize() {

        // ChoiceBox elementui pasirenkamos reiksmes is sukurtu listu
        kiekVaikuAuginateChoiceBox.setItems(kiekVaikuAuginateList);
        // nustatoma kad pirma listo reiksme elemente ChoiceBox bus rodoma kaip defaultine
        kiekVaikuAuginateChoiceBox.getSelectionModel().selectFirst();
        // nuvedus pelyte ant ChoiseBox, rodomas pranesimas -> Pasirinkite vaiku skaiciu"
        // kiekVaikuAuginateChoiceBox.setTooltip(new Tooltip("Pasirinkite vaikų skaičių"));


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

    // paspaudus radio mygtuka taip arba ne
    public void radioAction(ActionEvent e) {

        if (radioButtonNe.isSelected()) {
            kiekAuginateVaikuLabel.setVisible(false);
            kiekVaikuAuginateChoiceBox.setVisible(false);
            arAuginuVienasLabel.setVisible(false);
            arAuginuVienasChoiceBox.setVisible(false);
            kiekVaikuAuginateChoiceBox.getSelectionModel().selectFirst();
            arAuginuVienasChoiceBox.getSelectionModel().selectFirst();
        }
        if (radioButtonTaip.isSelected()) {
            kiekAuginateVaikuLabel.setVisible(true);
            kiekVaikuAuginateChoiceBox.setVisible(true);
            arAuginuVienasLabel.setVisible(true);
            arAuginuVienasChoiceBox.setVisible(true);
        }
    }

    // button Skaiciuoti metodas
    public void skaiciuoti(ActionEvent e) {
        atlyginimasAntPopieriaus = Integer.parseInt(input.getText());
        vaikuSkaicius = kiekVaikuAuginateChoiceBox.getSelectionModel().getSelectedIndex();
        augintojuSkaiciausNustatymas();
        pagrindinioNPDSkaiciavimas();
        papildomoNPDSkaiciavimas();
        visoNPDSkaiciavimas();
        gyventojuPajamuMokescioSkaiciavimas();
        privalomasSveikatosDraudimoSkaiciavimas();
        socialinioDraudimoSkaiciavimas();
        pensijosKaupimoSkaiciavimas();
        sodrosImokosDarbdaviuiSkaiciavimas();
        imokosIGarantiniFondaDarbdaviuiSkaiciavimas();
        darboVietosKainosDarbdaviuiSkaiciavimas();
        atlyginimoIRankasSkaiciavimas();
    }

    // augintoju skaiciaus nustatymas is ChoiceBox
    private void augintojuSkaiciausNustatymas() {
        int option = arAuginuVienasChoiceBox.getSelectionModel().getSelectedIndex();
        if (option == 0) {
            augintojuSkaicius = 1;
        } else {
            augintojuSkaicius = 2;
        }
    }


    private void pagrindinioNPDSkaiciavimas() {
        pagrindinisNPD = 0;
        int option = socialineDraudimoGrupeChoiceBox.getSelectionModel().getSelectedIndex();
        switch (option) {
            case 0:
                if (atlyginimasAntPopieriaus > 380 && atlyginimasAntPopieriaus < 1000) {
                    pagrindinisNPD = (double) 310 - 0.5 * (atlyginimasAntPopieriaus - MINATLYGINIMAS);

                } else if (atlyginimasAntPopieriaus >= 1000) {
                    pagrindinisNPD = 0;

                } else if (atlyginimasAntPopieriaus >= 310 && atlyginimasAntPopieriaus <= 380) {
                    pagrindinisNPD = 310;

                } else {
                    pagrindinisNPD = atlyginimasAntPopieriaus;
                }
                break;
            case 1:
                pagrindinisNPD = 320;
                break;
            case 2:
                pagrindinisNPD = 380;
                break;
        }
        pagrindinioNPDReiksme.setText(String.format("%.2f", pagrindinisNPD));
    }

    // papildomo NPD skaiciavimas ir isvedimas i TextField
    private void papildomoNPDSkaiciavimas() {
        papildomasNPD = vaikuSkaicius==0?0:(double) (200 * vaikuSkaicius) / augintojuSkaicius;
       // papildomasNPD = atlyginimasAntPopieriaus<=310?0:atlyginimasAntPopieriaus - pagrindinisNPD;

        papildomoNPDReiksme.setText(String.format("%.2f", papildomasNPD));
    }

    private void visoNPDSkaiciavimas() {
        visasNPD = pagrindinisNPD + papildomasNPD;
        if (visasNPD >= atlyginimasAntPopieriaus){
            visasNPD = atlyginimasAntPopieriaus;
            papildomasNPD = visasNPD - pagrindinisNPD;
        }
        visoNPDReiksme.setText(String.format("%.2f", visasNPD));
        papildomoNPDReiksme.setText(String.format("%.2f", papildomasNPD));
    }

    private void gyventojuPajamuMokescioSkaiciavimas() {
        gyventojuPajamuMokestis = ((double) atlyginimasAntPopieriaus - visasNPD) * GYVENTOJUPAJAMUMOKESCIOTARIFAS;
        if (gyventojuPajamuMokestis <= 0) {
            gyventojuPajamuMokestis = 0;
        }
        pajamuMokescioReiksme.setText(String.format("%.2f", gyventojuPajamuMokestis));
    }

    private void privalomasSveikatosDraudimoSkaiciavimas() {
        sveikatosDraudimas = (double) atlyginimasAntPopieriaus * PRIVALOMASISSVEIKATOSDRAUDIMAS;
        sveikatosDraudimoReiksme.setText(String.format("%.2f", sveikatosDraudimas));
    }

    private void socialinioDraudimoSkaiciavimas() {
        socialinisDraudimas = (double) atlyginimasAntPopieriaus * SOCIALINISDRAUDIMAS;
        socialinioDraudimoReiksme.setText(String.format("%.2f", socialinisDraudimas));
    }

    private void pensijosKaupimoSkaiciavimas() {
        int option = pensijosKaupimasChoiceBox.getSelectionModel().getSelectedIndex();
        pensijosKaupimas = 0;
        if (option == 1) {
            pensijosKaupimas = (double) atlyginimasAntPopieriaus * PENSIJOSKAUPIMAS;
        }
        pensijosKaupimoReiksme.setText(String.format("%.2f", pensijosKaupimas));
    }

    private void sodrosImokosDarbdaviuiSkaiciavimas() {
        sodrosImokaDarbdaviui = (double) atlyginimasAntPopieriaus * VALSTIBINIOSOCIALINIODRAUDIMOTARIFAS;
        sodrosImokosDarbdaviuiReiksme.setText(String.format("%.2f", sodrosImokaDarbdaviui));
    }


    private void imokosIGarantiniFondaDarbdaviuiSkaiciavimas() {
        imokaIGarantiniFondaDarbdaviui = (double) atlyginimasAntPopieriaus * IMOKOSIGARANTINIFONDADARBDAVIUIDRAUDIMOTARIFAS;
        imokosIGarantiniFondaDarbdaviuiReiksme.setText(String.format("%.2f", imokaIGarantiniFondaDarbdaviui));
    }

    private void atlyginimoIRankasSkaiciavimas() {
        atlyginimasIRankas = atlyginimasAntPopieriaus - gyventojuPajamuMokestis - sveikatosDraudimas - socialinisDraudimas - pensijosKaupimas;
        atlyginimoAntPopieriausReiksme.setText(String.format("%.2f", atlyginimasIRankas));
    }

    private void darboVietosKainosDarbdaviuiSkaiciavimas() {
        darboVietosKainaDarbdaviui = atlyginimasAntPopieriaus + sodrosImokaDarbdaviui + imokaIGarantiniFondaDarbdaviui;
        darboVietosKainosReiksme.setText(String.format("%.2f", darboVietosKainaDarbdaviui));
    }
}