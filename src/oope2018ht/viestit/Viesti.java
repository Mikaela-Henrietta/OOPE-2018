package oope2018ht.viestit;

import oope2018ht.apulaiset.*;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.*;

/** oope harkkatyö
 * @author Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 * Viesti-luokka, jonka vastuulla on yksittäiseen viestiin liittyvien tietojen hallinnointi.
 */
public class Viesti implements Komennettava<Viesti>, Comparable<Viesti>  {

   private int viestinTunniste;
   private String viestiTekstina;
   private Viesti viiteViestiin;
   private Tiedosto tiedostonLiittaja;
   private OmaLista viitteenSailoja;

   /**
    * neliparametrinen rakentaja
    * @param viestinTunniste on viestin tunniste juoksevana kokonaislukuna
    * @param viestiTekstina on viestin sisältö
    * @param viiteViestiin on viite viestiin
    * @param tiedostonLiittaja liittää parametrina saadun tiedoston viitteeseen
    * @throws IllegalArgumentException jos viestin tunniste on nolla tai pienempi tai viestitekstinä
    * on nolla tai pienempi
    */
   public Viesti (int viestinTunniste, String viestiTekstina, Viesti viiteViestiin, Tiedosto tiedostonLiittaja)
         throws IllegalArgumentException {

      if (viestiTekstina != null && viestinTunniste > 0 && viestiTekstina.length() > 0) {
         this.viestinTunniste = viestinTunniste;
         this.viestiTekstina = viestiTekstina;
      } else {
         throw new IllegalArgumentException();
      }
      this.viitteenSailoja = new OmaLista();

      this.viiteViestiin = viiteViestiin;
      this.tiedostonLiittaja = tiedostonLiittaja;
   }

   @Getteri
   public int haeViestinTunniste() {
      return viestinTunniste;
   }
   @Setteri
   public void asetaViestinTunniste(int viestinTunniste) throws IllegalArgumentException {
      if (viestinTunniste > 0) this.viestinTunniste = viestinTunniste;
      else throw new IllegalArgumentException();
   }
   @Getteri
   public String haeViestiTekstina() {
      return viestiTekstina;
   }
   @Setteri
   public void asetaViestiTekstina(String viestiTekstina) throws IllegalArgumentException {
      if (viestiTekstina.length() > 0) this.viestiTekstina = viestiTekstina;
      else throw new IllegalArgumentException();
   }
   @Getteri
   public Viesti haeViiteViestiin() {
      return viiteViestiin;
   }
   @Setteri
   public void asetaViiteViestiin(Viesti viiteViestiin)  {
         this.viiteViestiin = viiteViestiin;
   }
   @Getteri
   public Tiedosto haeTiedostonLiittaja() {
      return tiedostonLiittaja;
   }
   @Setteri
   public void asetaTiedostonLiittaja(Tiedosto tiedostonLiittaja) {
      this.tiedostonLiittaja = tiedostonLiittaja;
   }
   @Getteri
   public OmaLista haeViitteenSailoja() {
      return viitteenSailoja;
   }
   @Setteri
   public void asetaViitteenSailoja(OmaLista viitteenSailoja) throws IllegalArgumentException {
      if (viitteenSailoja != null) this.viitteenSailoja = viitteenSailoja;
      else throw new IllegalArgumentException();
   }

   /**
    * Toteuttaa Comparable-rajapinnan compareTo-metodin Viesti-luokassa. Geneeriseksi tyypiksi on kiinnitetty Viesti.
    * Olioita vertaillaan niiden tunnisteiden perusteella.
    * @param v String muotoinen vistin teksti
    * @return miinus yksi jos viestin ntunniste on pienempi kuin v.viestin tunniste ja nolla jos viestin tunniste
    * on yhtäkuin v.viestintunniste
    */
   public int compareTo(Viesti v) {
      if (viestinTunniste < v.viestinTunniste) {
         return -1;
      } else if (viestinTunniste == v.viestinTunniste) {
         return 0;
      } else {
         return 1;
      }
   }

   /**
    * Toteutetaan Object-luokan equals-metodi siten, että oliot katsotaan samoiksi, jos niiden tunnisteet samat.
    * @param obj objekti
    * @return true jos viestintunniste on v.viestintunniste
    */
   @Override
   public boolean equals(Object obj) {
      try {
         Viesti v = (Viesti)obj;
         return (viestinTunniste == v.viestinTunniste);
      } catch (Exception e) {
         return false;
      }
   }

   /**
    *
    * @return viestin tunnisteen viestin ja tiedoston
    */
   @Override
   public String toString() {
      String tiedosto = "";
      if(tiedostonLiittaja != null) {
         tiedosto = "(" + tiedostonLiittaja.toString() + ")";
      }
      return "#" + viestinTunniste + " " + viestiTekstina + tiedosto;
   }

   /**
    * toteutetaan Komennettava-rajapinnan metodin hae
    * @param haettava viite haettavaan viestiin.
    * @return viestin
    * @throws IllegalArgumentException heittää poikkeuksen jos heattava viesti on null
    */
   @Override
   public Viesti hae(Viesti haettava) throws IllegalArgumentException {
      if(haettava == null) {
         throw new IllegalArgumentException();
      } else {
         return (Viesti) viitteenSailoja.hae(haettava);
      }
   }

   /**
    * toteutetaan Komennettava-rajapinnan metodin lisääVastaus
    * @param lisattava viite lisättävään viestiin.
    * @throws IllegalArgumentException jos lisattava on yhtä kuin null
    */
   @Override
   public void lisaaVastaus(Viesti lisattava) throws IllegalArgumentException {
      // || viitteenSailoja.hae(lisattava) != null
      if (lisattava == null ) {
         throw new IllegalArgumentException();
      } else {
         viitteenSailoja.lisaa(lisattava);
      }
   }

   /**
    * toteutetaan Komennettava-rajapinnan metodin tyhjenna
    */
   @Override
   public void tyhjenna() {
      viestiTekstina = POISTETTUTEKSTI;
      tiedostonLiittaja = null;
   }
}
