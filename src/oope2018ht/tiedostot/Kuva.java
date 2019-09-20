
package oope2018ht.tiedostot;
import oope2018ht.apulaiset.*;

/**
 * oope harkkatyö
 * @author  Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 * Kuva-luokka kertoo kuvan korkeuden ja leveyden pikseleinä
 */
public class Kuva extends Tiedosto {
   /**
    *  atribuutit
    */
   private int leveysPikseleina;
   private int korkeusPikseleina;

   /**
    * neliparametrinen rakentaja
    * @param tiedostonNimi tiedoston nimi
    * @param tiedostonKokoTavuina tiedoston koko tavuina
    * @param leveysPikseleina leveys pikseleinä
    * @param korkeusPikseleina korkeus pikseleinä
    * @throws IllegalArgumentException heittää poikkeuksen jos leveys tai korkeus on virheellinen
    */
   public Kuva(String tiedostonNimi, int tiedostonKokoTavuina, int leveysPikseleina, int korkeusPikseleina)
   throws IllegalArgumentException {
      super(tiedostonNimi, tiedostonKokoTavuina);//kutsutaan yliluokan rakentajia
      if (leveysPikseleina <= 0 || korkeusPikseleina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.leveysPikseleina = leveysPikseleina;
         this.korkeusPikseleina = korkeusPikseleina;
      }
   }

   @Getteri
   public int haeLeveysPikseleina() {
      return leveysPikseleina;
   }

   @Setteri
   public void asetaLeveysPikseleina(int leveysPikseleina) throws IllegalArgumentException {
      if (leveysPikseleina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.leveysPikseleina = leveysPikseleina;
      }
   }
   @Getteri
   public int haeKorkeusPikseleina() {
      return korkeusPikseleina;
   }

   @Setteri
   public void asetaKorkeusPikseleina(int korkeusPikseleina) throws IllegalArgumentException {
      if (korkeusPikseleina <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.korkeusPikseleina = korkeusPikseleina;
      }
   }
   @Override
   public String toString() {
      return super.toString() + " B " + haeLeveysPikseleina() + "x" + haeKorkeusPikseleina();
   }
}
