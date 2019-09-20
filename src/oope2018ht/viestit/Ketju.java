package oope2018ht.viestit;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.Tiedosto;

/**
 * Ketju-luokka hallitsee yhtä keskusteluketjua
 * oope harkkatyö
 * @author Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 */
public class Ketju {
   private OksaLista oksaViestit;
   private int ketjunTunniste;
   private int ketjunViestitLaskuri;
   private String aihe;
   private String RIVINVAIHTO = System.getProperty("line.separator");

   /**
    * rakentaja
    * @param aihe on keskustelun aihe
    * @param ketjunTunniste on juokseva kokonaislukutunniste viestiketjulle
    */
   public Ketju(String aihe, int ketjunTunniste) {
      this.aihe = aihe;
      ketjunViestitLaskuri = 1;
      this.oksaViestit = new OksaLista();
      this.ketjunTunniste = ketjunTunniste;
   }

   /**
    * Lisää viestin ketjuun
    * @param tunniste viestin tunniste
    * @param v viesti
    */
   public void lisääViesti(int tunniste, String v) {
      Viesti viesti = new Viesti(tunniste, v, null, null);
      oksaViestit.lisääViesti(viesti);
      ketjunViestitLaskuri++;
   }

   /**
    * Lisää viestin tiedostolla
    * @param tunniste on viestin tunniste
    * @param v viesti
    * @param t tiedosto
    */
   public void lisääViestiTiedostolla(int tunniste, String v, Tiedosto t) {
      Viesti viesti = new Viesti(tunniste, v, null, t);
      oksaViestit.lisääViesti(viesti);
      ketjunViestitLaskuri++;
   }

   /**
    * Lisää vastauksen viestiin
    * @param tunnniste viestin tunniste
    * @param v on viesti
    * @param vastattavanViestintunnus  on vastattavan viestin tunnus
    */
   public void lisääVastaus(int tunnniste, String v, int vastattavanViestintunnus) {

      Viesti vastattavaViesti = getViesti(vastattavanViestintunnus);
      if(vastattavaViesti != null) {
         Viesti uusiViesti = new Viesti(tunnniste, v, vastattavaViesti, null);
         ketjunViestitLaskuri++;
         vastattavaViesti.lisaaVastaus(uusiViesti);
         oksaViestit.lisääVastaus(uusiViesti);
      } else {
         throw new IllegalArgumentException();
      }
   }

   /**
    * Lisää vastauksen tiedostolla
    * @param tunniste viestin tunniste
    * @param v on viesti
    * @param vastattavanViestintunnus on vastattavan viestin tunnus
    * @param t on tiedosto
    * @throws IllegalArgumentException jos vastattavan viestin tunniste on virheellinen
    */
   public void lisääVastausTiedostolla(int tunniste, String v, int vastattavanViestintunnus, Tiedosto t)
         throws IllegalArgumentException {
      Viesti vastattavaViesti = getViesti(vastattavanViestintunnus);

      if(vastattavaViesti != null) {
         Viesti uusiViesti = new Viesti(tunniste, v, vastattavaViesti, t);
         ketjunViestitLaskuri++;
         vastattavaViesti.lisaaVastaus(uusiViesti);
         oksaViestit.lisääVastaus(uusiViesti);
      } else {
         throw new IllegalArgumentException();
      }
   }

   /**
    * Poistaa viestin
    * @param tunnus viestin tunnus
    * @throws IllegalArgumentException jos
    */
   public void poistaViesti(int tunnus) throws IllegalArgumentException{
      try {
         Viesti poistettava = getViesti(tunnus);
         oksaViestit.poistaViesti(poistettava);
      } catch (Exception e) {
         throw new IllegalArgumentException();
      }
   }
   @Getteri
   public OmaLista getOksaViestit() {
      return oksaViestit.getOksalista();
   }
   @Getteri
   public OmaLista getKaikkiViestit() {
      return oksaViestit.getKaikkiViestit();
   }

   /**
    * Hakee viestin
    * @param tunnus viestin tunnus
    * @return viestin
    */
   public Viesti getViesti(int tunnus) {
      OmaLista oksat = oksaViestit.getKaikkiViestit();
      for(int i = 0; i < oksat.koko(); i++){
         Viesti v = (Viesti)oksat.alkio(i);
         if(v.haeViestinTunniste() == tunnus){
            return v;
         }
      }
      return null;
   }

   /**
    * Hakee viimeisen viestin
    * @param määrä viestien määrä
    * @return vanhat viestit
    */
   public String annaLoppu(int määrä) {
      if(määrä < 1 || määrä > ketjunViestitLaskuri) {
         throw new IllegalArgumentException();
      } else {
         OmaLista viestit = getKaikkiViestit().annaLoppu(määrä);
         String vanhatViestit = "";
         for(int i = 0; i < viestit.koko(); i++) {
            vanhatViestit += viestit.alkio(i).toString() + RIVINVAIHTO;
         }
         return vanhatViestit;
      }
   }

   /**
    * Antaa alusta uusimpia viestejä
    * @param määrä viestien määrä
    * @return uusimmat viestit
    */

   public String annaAlku(int määrä) {
      if(määrä < 1 || määrä > ketjunViestitLaskuri) {
         return "";
      } else {
         OmaLista viestit = getKaikkiViestit().annaAlku(määrä);
         String uusimmatViestit = "";
         for(int i = 0; i < viestit.koko(); i++) {
            uusimmatViestit += viestit.alkio(i).toString() + RIVINVAIHTO;
         }
         return uusimmatViestit;
      }
   }

   @Override
   public String toString() {
      return "#"+ ketjunTunniste + " " + aihe + " (" + (ketjunViestitLaskuri - 1) + " messages)";
   }
}
