package oope2018ht.kayttoliittyma;

import oope2018ht.apulaiset.In;
import oope2018ht.viestit.Alue;

/**
 * Kayttoliittymä-luokka toimii ohjelman käyttöliittymänä,
 * jossa aloitetaan ohjelma, lisätään uusi aihe, viesti, ketjut voi listata,
 * tulostaa puu ja lista muodossa, viestiin voi vastata,
 * viestin voi tyhjentää ja voi etsiä uusimmat ja vanhimmat viestit.
 * @author Mikaela Lindfors (Lindfors.Mikaela.H@student.uta.fi)
 */

public class Kayttoliittyma {
   private Alue alue;

   public Kayttoliittyma() {
      this.alue = new Alue();
   }

   /**
    * Aloittaa ohjelman
    */
   public void start() {
      System.out.println("Welcome to S.O.B.");
      boolean käynnissä = true;
      while (käynnissä) {
         System.out.print(">");
         String kokoKomento = In.readString();
         String komento = kokoKomento.split(" ")[0];
         String arg1 = kokoKomento.split(" ").length > 1 ? kokoKomento.split(" ")[1] : "";

         if (komento.equals("add")) {
            // luo uuden aiheen
            alue.luoUusiAihe(kokoKomento.split(" ", 2)[1]);

         } else if (komento.equals("catalog")) {
            System.out.print(alue);
         } else if (komento.equals("select")) {
            String[] selectKomento = kokoKomento.split(" ");
            if (selectKomento.length == 2) {
               try {
                  int ketjuIndex = Integer.parseInt(selectKomento[1]);
                  alue.valitseKetju(ketjuIndex);
               } catch (Exception e) {
                  System.out.println("Error!");
               }
            } else {
               System.out.println("Error!");
            }
         } else if (komento.equals("new")) {
            String[] newKomento = kokoKomento.split(" ");
            if (newKomento.length > 1) {
               try {
                  if(newKomento.length > 2  && kokoKomento.contains("&")) {
                     String tiedostoNimi = kokoKomento.substring(kokoKomento.lastIndexOf("&") + 1);
                     String viesti = kokoKomento.substring(
                              kokoKomento.indexOf(newKomento[1]), kokoKomento.lastIndexOf("&"));
                     alue.lisääViestiValittuunKetjuun(viesti, tiedostoNimi);
                  } else {
                     alue.lisääViestiValittuunKetjuun(kokoKomento.split(" ", 2)[1]);
                  }
               } catch (Exception e) {
                  System.out.println("Error!");
               }
            } else {
               System.out.println("Error!");
            }
         } else if (komento.equals("reply")) {
            if(arg1 != "") {
               try {
                  int tunnus = Integer.parseInt(kokoKomento.split(" ")[1]);
                  if(kokoKomento.contains("&")) {
                     String tiedostoNimi = kokoKomento.substring(kokoKomento.lastIndexOf("&") + 1);
                     String viesti = kokoKomento.substring(0 ,kokoKomento.lastIndexOf("&"))
                           .split(" ", 3)[2];
                     alue.vastaaViestiin(tunnus, viesti, tiedostoNimi);
                  } else {
                     alue.vastaaViestiin(tunnus, kokoKomento.split(" ", 3)[2]);
                  }
               } catch (Exception e) {
                  System.out.println("Error!");
               }
            }
         } else if (komento.equals("tree")) {
            try {
               System.out.print(alue.haeTulostettavaPuu());
            } catch (Exception e) {
               System.out.println("Error!");
            }
         } else if (komento.equals("list")) {
            try {
               System.out.print(alue.haeTulostettavaLista());
            } catch (Exception e) {
               System.out.println("Error!");
            }
         } else if (komento.equals("head")) {
            try {
               int määrä = Integer.parseInt(arg1);
               System.out.print(alue.haeUusimmatViestit(määrä));
            } catch (Exception e) {
               System.out.println("Error!");
            }
         } else if (komento.equals("tail")) {
            try {
               int määrä = Integer.parseInt(arg1);
               System.out.print(alue.haeVanhatViestit(määrä));
            } catch (Exception e) {
               System.out.println("Error!");
            }
         } else if (komento.equals("empty")) {
            try {
               int viestiTunnus = Integer.parseInt(arg1);
               alue.poistaViesti(viestiTunnus);
            } catch (Exception e) {
               System.out.println("Error!");
            }
         } else if (komento.equals("find")) {
            System.out.print(alue.haeViesteistä(arg1));
         } else if (komento.equals("exit")) {
            System.out.println("Bye! See you soon.");
            käynnissä = false;
         } else {
            System.out.println("Error!");
         }
      }
   }
}
