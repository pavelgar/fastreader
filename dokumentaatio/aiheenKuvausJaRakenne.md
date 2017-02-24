# Fastreader

### Aihe

#### Nopealukuohjelma

Ohjelman tarkoituksena on auttaa käyttäjää lukemaan syöttämä teksti tavallista nopeammin näyttämällä teksti sana kerralla jopa 500 sanaa/min nopeudella. Tämä auttaa sekä keskittymään tekstin sisältöön, että nopeuttaa tekstin lukua. 

Ohjelma tulee tarjoamaan:
* Esittelykierroksen (tutorial) 
* Huippunopeuden valitsimen 
* Näppäimet pysäyttämiselle, taakse- ja eteenpäin 
* Tilastotietoa käyttäjästä, kuten luettujen sanojen määrä, tehtyjen taukojen määrä ja esim. useimmiten luettujen sanojen määrä

#### Käyttöohjeet
Ohjelman käyttö on hyvin yksinkertaista:
**Tekstirivi: ** Näyttää tekstin sanoja järjestyksessä.
**Napit:**
1. Palaa alkuun: Palaa tekstin alkuu.
2. Lause taaksepäin: Siirtyy yhden lauseen taaksepäin.
3. Play: Aloittaa sanojen näyttämisen järjestyksessä valitulla nopeudella.
4. Lause eteenpäin: Siirtyy yhden lauseen eteenpäin.

**Valikot:**
* Open File: Avaa ponnahdusikkunan, johon voi kirjoittaa absoluuttisen tai relatiivisen polun, jossa haluttu tiedosto sijaitsee.
* Paste text: Avaa ponnahdusikkunan, johon voi liittää suoraan tekstiä.
* Statistics: Avaa ponnahdusikkunan, joka näyttää tallenetun statistiikan.

**Speed:** Tähän voi kirjoittaa haluamansa lukunopeuden. Nopeus on yksikössä sanoja/minuutti.

### Käyttäjät

Käyttäjä (lukija)

#### Lukijan toiminnot

* Tekstin tai tiedoston syöttäminen
* Nopeuden valitseminen
* Tekstiluvun pysäyttäminen
* Sanoissa eteen/taakse siirtyminen
  * Jos tekstinluku on käynnissa, se pysäytetään
* Tilastojen tarkastelu

### Luokkakaavio
![Luokkakaavio](luokkakaavio.png)

#### Rakennekuvaus
Main -luokka luo oliot WordSequencer ja UserInterface.
UserInterface saa WordSequencer-olion käyttöönsä.
Tietyissä WordSequencerin metodikutsuissa kutsutaan StatisticsRecorder-olion metodeja, jotka tallentavat statistiikkaa.
Jos WordSequenceria pyydetään avamaan tiedosto, kutsuu se omaa FileOpener-oliota, joka palauttaa WordSequencerille listan sanoja, joita se pystyy käyttämään.

### Sekvenssikaaviot
![](nayta_statistiikka.png)
![](seuraava_lause.png)

### Mukavia lukuhetkiä!
