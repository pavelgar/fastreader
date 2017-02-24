# Fastreader

### Aihe

#### Nopealukuohjelma

Ohjelman tarkoituksena on auttaa käyttäjää lukemaan syöttämä teksti tavallista nopeammin näyttämällä teksti sana kerralla jopa 500 sanaa/min nopeudella. Tämä auttaa sekä keskittymään tekstin sisältöön, että nopeuttaa tekstin lukua. 

Ohjelma tulee tarjoamaan:
* Esittelykierroksen (tutorial) 
* Huippunopeuden valitsimen 
* Näppäimet pysäyttämiselle, taakse- ja eteenpäin 
* Tilastotietoa käyttäjästä, kuten luettujen sanojen määrä, tehtyjen taukojen määrä ja esim. useimmiten luettujen sanojen määrä

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

Main -luokka luo oliot WordSequencer ja UserInterface.
UserInterface saa WordSequencer-olion käyttöönsä.
Tietyissä WordSequencerin metodikutsuissa kutsutaan StatisticsRecorder-olion metodeja, jotka tallentavat statistiikkaa.
Jos WordSequenceria pyydetään avamaan tiedosto, kutsuu se omaa FileOpener-oliota, joka palauttaa WordSequencerille listan sanoja, joita se pystyy käyttämään.

### Sekvenssikaaviot
![](nayta_statistiikka.png)
![](seuraava_lause.png)

### Mukavia lukuhetkiä!
