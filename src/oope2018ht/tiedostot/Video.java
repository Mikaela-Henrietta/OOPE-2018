

package oope2018ht.tiedostot;
import oope2018ht.apulaiset.*;

/**
 * @author  Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 * Video-luokka kertoo videon pituuden sekuntteina
 */
public class Video extends Tiedosto {
   /**
    * Atribuutti
    */
   private double videonPituusSekuntteina;

   /**
    * Rakentaja
    * @param tiedostonNimi on tiedoston nimi
    * @param tiedostonKokoTavuina on tiedoston koko
    * @param videonPituusSekuntteina on viedeon pituus sekuntteina
    * @throws IllegalArgumentException jos tiedosto onnolla tai pienempi
    */
   public Video(String tiedostonNimi, int tiedostonKokoTavuina, double videonPituusSekuntteina)
   throws IllegalArgumentException {
      super (tiedostonNimi, tiedostonKokoTavuina); // kutsutaan yliluokan rakentajia
      if (videonPituusSekuntteina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.videonPituusSekuntteina = videonPituusSekuntteina;
      }
   }

   @Getteri
   public double haeVideonPituusSekuntteina() {
      return videonPituusSekuntteina;
   }

   @Setteri
   public void asetaVideonPituusSekuntteina(double videonPituusSekuntteina) throws IllegalArgumentException {
      if (videonPituusSekuntteina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.videonPituusSekuntteina = videonPituusSekuntteina;
      }
   }

   @Override
   public String toString() {
      return super.toString() + " B " + haeVideonPituusSekuntteina() + " s";
   }
}
