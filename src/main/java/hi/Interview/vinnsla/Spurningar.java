package hi.Interview.vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******************************************************************************
 *  Nafn    : Hildur Agla
 *  T-póstur: hao37@hi.is
 *
 *  Lýsing  : Vinnsluklasi fyrir spurningar. Geymir spurningar innan spurningaflokka.
 *            Hefur lista fyrir flokka og spurninga.
 *
 *
 *****************************************************************************/

public class Spurningar {
    private final ObservableList<String> flokkar;
    private final Map<String, ObservableList<String>> spurningar;
    private final SimpleStringProperty fjoldiSvaradraSpurninga;

    public Spurningar() {
        this.flokkar = FXCollections.observableArrayList();
        this.spurningar = new HashMap<>();
        this.fjoldiSvaradraSpurninga = new SimpleStringProperty("0");

        // Skilgreina flokka
        addFlokkur("Almennt");
        addFlokkur("Tækni");
        addFlokkur("Persónulegt");
        addFlokkur("Færni");

        // Bæta við spurningum
        addSpurningar("Persónulegt",
                "Ef þú gætir farið aftur í tímann, hvaða ráð myndirðu gefa sjálfum/sjálfri þér?",
                "Hvaða hæfileika langar þig að læra?",
                "Hvað finnst þér skemmtilegt að gera í frítímanum þínum?",
                "Hvaða bók eða kvikmynd hefur haft mest áhrif á þig?"
        );

        addSpurningar("Færni",
                "Hvaða forritunarmál hefur þú unnið mest með?",
                "Hvaða verkefnastjórnunartæki þekkir þú best?",
                "Hversu vel kannt þú á Git?"
        );

        addSpurningar("Almennt",
                "Hvað er Big-O greining og hvers vegna er hún mikilvæg?",
                "Hvað eru endurkvæm (recursive) reiknirit og hvenær eru þau gagnleg?",
                "Hver er munurinn á samhliða (parallel) og samtímis (concurrent) útreikningum?"
        );

        addSpurningar("Tækni",
                "Hverjir eru helstu kostir og gallar við Insertion Sort?",
                "Hvernig virkar Garbage Collection í Java?",
                "Hver er munurinn á stack og heap í minni (memory) stjórnun?"
        );
    }

    public void addFlokkur(String flokkur) {
        if (!flokkar.contains(flokkur)) {
            flokkar.add(flokkur);
            spurningar.put(flokkur, FXCollections.observableArrayList());
        }
    }

    public void addSpurningar(String flokkur, String... spurningarTextar) {
        List<String> listi = spurningar.get(flokkur);
        if (listi != null) {
            listi.addAll(List.of(spurningarTextar));
        }
    }

    public ObservableList<String> getFlokkar() {
        return flokkar;
    }

    public ObservableList<String> getSpurningaListi(String flokkur) {
        return spurningar.getOrDefault(flokkur, FXCollections.observableArrayList());
    }

    public SimpleStringProperty fjoldiSvaradraSpurningaProperty() {
        return fjoldiSvaradraSpurninga;
    }

    public void aukaFjoldiSvaradra() {
        int nyttGildi = Integer.parseInt(fjoldiSvaradraSpurninga.get()) + 1;
        fjoldiSvaradraSpurninga.set(String.valueOf(nyttGildi));
    }

}
