package hi.Interview.vidmot;

import hi.Interview.vinnsla.Spurningar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.Optional;

/******************************************************************************
 *  Nafn    : Hildur Agla
 *  T-póstur: hao37@hi.is
 *
 *  Lýsing  : Stýringg fyrir spurninga gluggann. Notatndi getur valið flokk og
 *            síðan spurningar úr ListView. Notandi ýtir á svara, til baka til að
 *            komast aftur á velkomin gluggan eða hætta og þá fer hann í kveðju gluggann.
 *
 *
 *****************************************************************************/

public class SpurningarController {

    @FXML
    private ListView<String> fxSpurningaflokkar; //ListView sem geymir spurningaflokkana
    @FXML
    private ListView<String> fxSpurningar; //ListView sem geymir spurningarnar
    @FXML
    private Label fxFjoldaSvar; //Label sem sýnir fjölda svaraðra spurninga
    @FXML
    private ListView<String> fxLogger; //ListView sem geymir loggið

    private Spurningar spurningar = new Spurningar(); //spurningar-hlutur

    private String valinSpurning; // spurningin sem valin var

    private ObservableList<String> loggListi = FXCollections.observableArrayList(); // ObservableList fyrir loggið

    /**
     * upphafsstilla reglur sem tengja flokka og spurningaflokka
     */
    public void initialize() {
        ObservableList<String> flokkar = spurningar.getFlokkar();
        fxSpurningaflokkar.setItems(flokkar);

        // Listener á selectedIndexProperty fyrir spurningaflokkana
        fxSpurningaflokkar.getSelectionModel().selectedIndexProperty().addListener((obs, old, newIndex) -> {
            if (newIndex.intValue() > -1) { // Gæta þess að newIndex sé gildur
                String valinnFlokkur = fxSpurningaflokkar.getItems().get(newIndex.intValue());

                // Sækja spurningar fyrir valinn flokk
                ObservableList<String> spurningarListi = spurningar.getSpurningaListi(valinnFlokkur);
                fxSpurningar.setItems(spurningarListi); // Uppfæra spurningalistann

            }
        });

        // Listener á selectedIndexProperty fyrir spurninguna sem er valin
        fxSpurningar.getSelectionModel().selectedIndexProperty().addListener((obs, old, newIndex) -> {
            if (newIndex.intValue() > -1) {
                valinSpurning = fxSpurningar.getItems().get(newIndex.intValue());
            }
        });

        // Binda fjölda svaraðra spurninga við fjölda svara og upphafstilla loggið
        fxFjoldaSvar.textProperty().bind(spurningar.fjoldiSvaradraSpurningaProperty());
        fxLogger.setItems(loggListi);
    }

    /**
     * Handler fyrir að fara aftur í byrjunarsenu
     */
    public void onTilbaka() {
        ViewSwitcher.switchTo(View.VELKOMINN);
    }

    /**
     * Handler fyrir að fara í kveðjuna
     */
    public void onHaetta() {
        ViewSwitcher.switchTo(View.KVEDJA);
    }

    /**
     * Ef búið er að velja spurningu er hægt
     * að svara spurningu með því að ýta á svara
     * @param actionEvent
     */
    public void onSvara(ActionEvent actionEvent) {
        if (valinSpurning != null) {
            svara();
        }
    }

    /**
     * Opna dialoginn fyrir svarið og fá gögn frá honum
     *
     */
    private void svara() {
        SvarDialogController d = new SvarDialogController(valinSpurning);
        Optional<String> utkoma = d.showAndWait();

        // Ef notandi gaf svar bæta þá spruninguna við loggið
        utkoma.ifPresent(spurningin -> {
            String logEntry = spurningin;
            // Bæta svaraðri spurningu við loggið (ObservableList)
            loggListi.add(logEntry);
            // Auka fjölda svaraðra spurninga
            spurningar.aukaFjoldiSvaradra();
        });
    }

}
