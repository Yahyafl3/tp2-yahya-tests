package ma.yahya.tp2.tests;

import ma.yahya.tp2.tests.outil.meteo.MeteoTool;

public class TestMeteo {
    public static void main(String[] args) {
        MeteoTool meteo = new MeteoTool();
        boolean pluie = meteo.isPluiePrevue(48.85341, 2.3488, 0, 3);
        System.out.println("Pluie prévue à Paris : " + pluie);
    }
}