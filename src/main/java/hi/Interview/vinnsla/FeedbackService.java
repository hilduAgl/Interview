package hi.Interview.vinnsla;

/******************************************************************************
 *  Nafn    : Hildur Agla
 *  T-póstur: hao37@hi.is
 *
 *  Lýsing  : Klasi sem gefur feedback á hve svar við viðtalsspurningu er gott.
 *
 *
 *****************************************************************************/
public class FeedbackService {

    public static String provideFeedback(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            return "Svar þitt virðist vera of stutt. Reyndu að bæta við fleiri atriðum.";
        }

        if (answer.toLowerCase().contains("samvinna")) {
            return "Gott! Þú nefndir samvinnu. Geturðu gefið ákveðið dæmi?";
        }

        if (answer.toLowerCase().contains("leysa vandamál")) {
            return "Frábært! Að leysa vandamál er lykilatriði í mörgum störfum. Geturðu nefnt dæmi þar sem þú leystir vandamál á árangursríkan hátt?";
        }

        return "Svarið þitt er fínt, en reyndu að gera það skipulagðara með skýru dæmi.";
    }

}

