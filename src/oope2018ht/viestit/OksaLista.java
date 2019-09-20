package oope2018ht.viestit;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.omalista.OmaLista;

/**
 * Lista ketjuista
 * oope harkkatyö
 * @author Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 */
public class OksaLista {
   private OmaLista oksalista;
   private  OmaLista kaikkiViestit;

   public OksaLista() {
      this.oksalista = new OmaLista();
      this.kaikkiViestit = new OmaLista();
   }

   /**
    *
    * @param v on viesti
    */
   public void lisääViesti(Viesti v) {
      this.oksalista.lisaaLoppuun(v);
      this.kaikkiViestit.lisaaLoppuun(v);
   }

   /**
    * lisää vastauksen
    * @param v on viesti
    */
   public void lisääVastaus(Viesti v) {
      this.kaikkiViestit.lisaaLoppuun(v);
   }
   @Getteri
   public OmaLista getOksalista() {
      return oksalista;
   }
   @Getteri
   public OmaLista getKaikkiViestit() {
      return kaikkiViestit;
   }

   /**
    * poistaa viestin
    * @param v on viesti
    */
   public void poistaViesti(Viesti v) {
      Viesti oksaViesti = (Viesti) oksalista.hae(v);
      if(oksaViesti != null) {
         oksaViesti.tyhjenna();
      }
      Viesti kaikkiViestitViesti = (Viesti) kaikkiViestit.hae(v);
      kaikkiViestitViesti.tyhjenna();
   }
}
