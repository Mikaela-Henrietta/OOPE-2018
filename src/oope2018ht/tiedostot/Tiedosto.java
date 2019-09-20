
package oope2018ht.tiedostot;

import oope2018ht.apulaiset.*;

/** oope harkkatyö
 * @author  Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 * Abstrakti Tiedosto-luokka, joka sisältää tiedon tiedoston koosta ja nimestä
 */
public abstract class Tiedosto {
   private String tiedostonNimi;
   private int tiedostonKokoTavuina;

   /**
    * rakentaja
    * @param tiedostonNimi tiedoston nimi
    * @param tiedostonKokoTavuina tiedoston koko
    * @throws IllegalArgumentException heittää poikkeuksen jos nimi tai koko on virheellinen
    */
   public Tiedosto(String tiedostonNimi, int tiedostonKokoTavuina) throws IllegalArgumentException {
      if (tiedostonNimi == null || tiedostonNimi.length() <= 0 || tiedostonKokoTavuina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.tiedostonNimi(tiedostonNimi);
         this.tiedostonKokoTavuina(tiedostonKokoTavuina);
      }
   }

   @Getteri
   public String tiedostonNimi() {
      return tiedostonNimi;
   }

   @Setteri
   public void tiedostonNimi(String tiedostonNimi) throws IllegalArgumentException {
      if (tiedostonNimi.length() <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.tiedostonNimi = tiedostonNimi;
      }
   }

   @Getteri
   public int tiedostonKokoTavuina() {
      return tiedostonKokoTavuina;
   }

   @Setteri
   public void tiedostonKokoTavuina(int tiedostonKokoTavuina) throws IllegalArgumentException {
      if (tiedostonKokoTavuina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.tiedostonKokoTavuina = tiedostonKokoTavuina;
      }
   }

   @Override
   public String toString() {
      return tiedostonNimi() + " " + tiedostonKokoTavuina();
   }
}
