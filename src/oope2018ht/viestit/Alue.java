package oope2018ht.viestit;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2018ht.apulaiset.Getteri;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.Kuva;
import oope2018ht.tiedostot.Tiedosto;
import oope2018ht.tiedostot.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Alue-luokka vastaa alueeseen kuuluvista toiminnallisuuksista ja hallinnoi alueeseen kuuluvia ketjuja
 * @author Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 */
public class Alue {
   private LinkitettyLista ketjut;
   private int tunnisteLaskuri;
   private Ketju valittuKetju = null;
   private String tulostus;
   private String RIVINVAIHTO = System.getProperty("line.separator");
   private String SISENNYS = "   ";
   private int viestinTunnisteLaskuri = 1;

   /**
    * rakentaja
    */
   public Alue() {
      this.ketjut = new OmaLista();
      tunnisteLaskuri = 1;
      tulostus = "";
   }

   /**
    * Metodi luo uuden aiheen
    * @param aihe on String tyyppinen otsikko.
    */
   public void luoUusiAihe(String aihe) {
      Ketju uusiKetju = new Ketju(aihe, tunnisteLaskuri);
      ketjut.lisaaLoppuun(uusiKetju);
      if(tunnisteLaskuri == 1) {
         valittuKetju = uusiKetju;
      }
      tunnisteLaskuri++;
   }
   @Getteri
   public LinkitettyLista haeKetjut() {
      return ketjut;
   }

   /**
    * Metodi valitsee ketjun
    * @param index on ketjun tunnus.
    * @throws IllegalArgumentException Heittää virheen jos tunnus
    * pienempi kuin 1 tai tunnus suurempi kuin viimeinen tunnus.
    */
   public void valitseKetju(int index) throws IllegalArgumentException {
      if (index > 0 && index <= ketjut.koko()) {
         valittuKetju = (Ketju) ketjut.alkio(index-1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   /**
    * Lisää viestin valittuun ketjuun
    * @param viesti on viesti tekstinä
    */
   public void lisääViestiValittuunKetjuun(String viesti) {
      if(viesti != "" && valittuKetju != null) {
         valittuKetju.lisääViesti(viestinTunnisteLaskuri, viesti);
         viestinTunnisteLaskuri++;
      }
   }

   /**
    * Lisää viestin valittuun ketjuun
    * @param viesti on viesti tekstinä
    * @param tiedosto on viite tiedostoon
    * @throws FileNotFoundException heittää poikkeusen jos tiedostoa ei löydy
    */
   public void lisääViestiValittuunKetjuun(String viesti, String tiedosto) throws FileNotFoundException {
      if(viesti != "" && valittuKetju != null ) {
         try {
            Tiedosto t = lueTiedosto(tiedosto);
            if(t == null) {
               throw new FileNotFoundException();
            } else {
               valittuKetju.lisääViestiTiedostolla(viestinTunnisteLaskuri, viesti, t);
               viestinTunnisteLaskuri++;
            }
         } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   /**
    * Luo vastauksen viestiin
    * @param tunnus vastattavan viestin tunnus
    * @param viesti viesti tekstinä
    * @throws IllegalArgumentException jos tunnus on virheellinen
    */
   public void vastaaViestiin(int tunnus, String viesti) throws IllegalArgumentException {
      if(valittuKetju != null) {
         try {
            valittuKetju.lisääVastaus(viestinTunnisteLaskuri, viesti, tunnus);
            viestinTunnisteLaskuri++;
         } catch (Exception e) {
            throw new IllegalArgumentException();
         }
      }
   }

   /**
    * Luo vastauksen viestiin silloin kun on myös tiedosto parametrina
    * @param tunnus vastattavan viestin tunnus
    * @param viesti viesti tekstinä
    * @param tiedosto on viite tiedostoon
    * @throws FileNotFoundException jos tiedostoa ei ole
    * @throws IllegalArgumentException jos viestiä ei ole
    */
   public void vastaaViestiin(int tunnus, String viesti, String tiedosto)
         throws FileNotFoundException, IllegalArgumentException {
      if(valittuKetju != null) {
         try {
            Tiedosto t = lueTiedosto(tiedosto);
            valittuKetju.lisääVastausTiedostolla(viestinTunnisteLaskuri, viesti, tunnus, t);
            viestinTunnisteLaskuri++;
         } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
         }
      }
   }

   /**
    * Hakee tulostettavan puun
    * @return stringin, missä puun tulostus
    * @throws IllegalArgumentException jos valittu ketju on null
    */
   public String haeTulostettavaPuu() throws IllegalArgumentException {
      if(valittuKetju == null) {
         throw new IllegalArgumentException();
      } else {
         return tulostaPuunaAlustus();
      }
   }

   /**
    * Alustaa puun tulostuksen
    * @return puun stringinä
    */
   public String tulostaPuunaAlustus() {
      tulostus = "=" + RIVINVAIHTO;
      tulostus += "== " + valittuKetju + RIVINVAIHTO;
      tulostus += "===" + RIVINVAIHTO;

      int i = 0;
      OmaLista k = valittuKetju.getOksaViestit();
      if(k != null ) {
         while (i < k.koko()) {
            tulostaPuuna((Viesti) k.alkio(i), 0);
            i++;
         }
      }
      return tulostus;
   }

   /**
    * Tulostaa puuna
    * @param viesti on viestin tyyppi
    * @param syvyys on puun syvyys, jota kasvatetaan rekursion edetessä
    */
   private void tulostaPuuna(Viesti viesti, int syvyys) {
      tulostus += sisennä(syvyys) + viesti + RIVINVAIHTO;
      int j = 0;
      OmaLista viitteenSailoja = viesti.haeViitteenSailoja();
      syvyys++;
      if(viitteenSailoja != null) {
         while (j < viitteenSailoja.koko()) {
            tulostaPuuna((Viesti) viitteenSailoja.alkio(j), syvyys);
            j++;
         }
      }
   }

   /**
    * Sisentää
    * @param koko on sisennyksen "välilyöntien" määrä
    * @return sisennys
    */
   public String sisennä(int koko) {
      String sisennys = "";
      for(int i = 0; i < koko; i++) {
         sisennys += SISENNYS;
      }
      return sisennys;
   }

   /**
    * Hakee tulostettavan listan
    * @return listaTulostus
    * @throws IllegalArgumentException jos valittuketju on null
    */
   public String haeTulostettavaLista() throws IllegalArgumentException {
      if(valittuKetju == null) {
         throw new IllegalArgumentException();
      } else {
         String listaTulostus = "";
         listaTulostus = "=" + RIVINVAIHTO;
         listaTulostus += "== " + valittuKetju + RIVINVAIHTO;
         listaTulostus += "===" + RIVINVAIHTO;
         OmaLista kaikkiViestit = valittuKetju.getKaikkiViestit();
         for(int i = 0; i < kaikkiViestit.koko(); i++) {
            listaTulostus += kaikkiViestit.alkio(i).toString() + RIVINVAIHTO;
         }
         return listaTulostus;
      }
   }

   /**
    * Hakee vanhat viestit
    * @param määrä on vanhojen viestien määrä
    * @return stringginä annetun määrän viestejä
    */
   public String haeVanhatViestit(int määrä) {
      return valittuKetju.annaLoppu(määrä);
   }

   /**
    * Hakee uusimmat viestit
    * @param määrä on uusien viestien määrä
    * @return stringinä annetun määrän viestejä
    */
   public String haeUusimmatViestit(int määrä) {
      return valittuKetju.annaAlku(määrä);
   }

   /**
    * Käy kaikki ketjut läpi ja poistaa viestin jos se löytyy
    * @param tunnus on viestin tunnus
    */
   public void poistaViesti(int tunnus) {
      try {
         for(int i = 0; i < ketjut.koko(); i++) {
            Ketju k = (Ketju) ketjut.alkio(i);
            if(k.getViesti(tunnus) != null) {
               k.poistaViesti(tunnus);
            }
         }
      } catch (Exception e) {

      }
   }

   /**
    * Hakee viesteistä hakusanaa vastaavan viestin
    * @param hakusana on hakusana
    * @return haun tulos
    */
   public String haeViesteistä(String hakusana) {
      String tulokset = "";
      OmaLista viestit = valittuKetju.getKaikkiViestit();
      for (int i = 0; i < viestit.koko(); i++ ){
         Viesti viesti = (Viesti) valittuKetju.getKaikkiViestit().alkio(i);
         if(viesti.haeViestiTekstina().contains(hakusana)
               || (viesti.haeTiedostonLiittaja() != null
               && viesti.haeTiedostonLiittaja().tiedostonNimi().contains(hakusana))) {
            tulokset += viesti.toString() + RIVINVAIHTO;
         }
      }
      return tulokset;
   }

   /**
    * Lukee tiedoston
    * @param tiedostonNimi on tiedoston nimi
    * @return tiedosto tyypin
    * @throws FileNotFoundException jos teidostoa ei löydy tai tiedosto on tyhjä
    */
   public Tiedosto lueTiedosto(String tiedostonNimi) throws FileNotFoundException {
      File tiedostoOlionNimi = new File(tiedostonNimi);
      Scanner lukija = new Scanner(tiedostoOlionNimi);
      if(lukija != null && lukija.hasNext()) {
         String tiedostoSisältö = lukija.nextLine();
         String[] sisältö = tiedostoSisältö.split(" ");
         String tyyppi = sisältö[0];
         Tiedosto t = null;
         if(tyyppi.equals("Kuva")) {
            int koko = Integer.parseInt(sisältö[1]);
            int leveys = Integer.parseInt(sisältö[2]);
            int korkeus = Integer.parseInt(sisältö[3]);
            t = new Kuva(tiedostonNimi, koko, leveys, korkeus);
         } else if(tyyppi.equals("Video")) {
            int koko = Integer.parseInt(sisältö[1]);
            double kesto = Double.parseDouble(sisältö[2]);
            t = new Video(tiedostonNimi, koko, kesto);
         }
         return t;
      } else {
         throw new FileNotFoundException();
      }
   }

   @Override
   public String toString() {
      String tulostus = "";
      for(int i = 0; i < ketjut.koko(); i++) {
         tulostus += ketjut.alkio(i).toString() + RIVINVAIHTO;
      }
      return tulostus;
   }
}
