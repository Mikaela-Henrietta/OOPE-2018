package oope2018ht.omalista;
import fi.uta.csjola.oope.lista.*;

import oope2018ht.apulaiset.Ooperoiva;

/**
 * oope harkkatyö
 * @author  Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 * OmaLista-luokka suorittaa Ooperoiva rajapinnan metodit
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {

   /**
    * Hae-metodi tutkii onko listalla haettavaa oliota equals-mielessä vastaava alkio.
    * @param haettava on haettava olio.
    * @return palauttaa alkion jos se on sama kuin haettava olio.
    */

   public Object hae(Object haettava) {
      if(koko() == 0) return null;
      Solmu solmu = paa();
      boolean seuraava = true;
      while (solmu != null) {
         if (solmu.alkio().equals(haettava)) {
            return solmu.alkio();
         }
         solmu = solmu.seuraava();
      }
      return null;
   }

   /**
    * Lisää alkion listalle siten, että alkio sijoittuu kaikkien itseään pienempien
    * tai yhtä suurien alkioiden jälkeen ja ennen kaikkia itseään suurempia alkioita.
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @return palauttaa true jos vertailu on tosi muuten false.
    */
   @SuppressWarnings({"unchecked"})
   public boolean lisaa(Object uusi) {

      if(uusi == null) {
         return false;
      }

      try {
         if (paa() == null) {
            lisaaAlkuun(uusi);
            return true;
         }
         if(((Comparable) hanta().alkio()).compareTo(uusi) < 0) {
            lisaaLoppuun(uusi);
            return true;
         }
         Comparable vertailtava = null;
         for (int i = 0; i < koko(); i++) {
            vertailtava = (Comparable) alkio(i);
            if (vertailtava.compareTo(uusi) > 0) {
               lisaa(i, uusi);
               return true;
            }
         }
         return false;
      } catch (Exception e) {
         return false;
      }

   }

   /**
    * Luo ja palauttaa uuden listan, jolla on viitteet n ensimmäiseen listan
    * tietoalkioon.
    * @param n palautettavalle listalle lisättävien viitteiden lukumäärä.
    * @return palauttaa uuden listan.
    */
   public OmaLista annaAlku(int n) {
      if (koko() == 0 || n == 0 || n > koko()) {
         return null;
      } else {
         OmaLista lista = new OmaLista();
         int laskuri = 0;
         while (laskuri < n) {
            lista.lisaa(alkio(laskuri));
            laskuri++;
         }
         return lista;
      }
   }

   /**
    * Luo ja palauttaa uuden listan, jolla on viitteet n viimeiseen listan
    * tietoalkioon.
    * @param n palautettavalle listalle lisättävien viitteiden lukumäärä.
    * @return palauttaa uuden listan.
    */
   public OmaLista annaLoppu(int n) {
      if(koko() - n < 0 || n == 0 || n > koko()) {
         return null;
      }
      OmaLista lista = new OmaLista();
      int laskuri = 0;
      while (koko() - n + laskuri < koko()) {
         lista.lisaa(alkio(koko() - n + laskuri));
         laskuri++;
      }
      return lista;
   }
}
