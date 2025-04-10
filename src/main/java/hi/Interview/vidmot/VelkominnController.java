package hi.Interview.vidmot;

/******************************************************************************
 *  Nafn    : Hildur Agla
 *  T-póstur: hao37@hi.is
 *
 *  Lýsing  : Stýring fyrir fyrsta gluggan sem býður notandann velkominn. Hægt er að
 *            hætta og fara í kveðju gluggann með því að ýta á hætta eða fara í spurninga
 *            gluggann með því að ýta á spurningar.
 *
 *
 *****************************************************************************/

public class VelkominnController {

    public void onSpurningar() {
        ViewSwitcher.switchTo(View.SPURNINGAR);
    }

    public void onHaetta() {
        ViewSwitcher.switchTo(View.KVEDJA);
    }


}