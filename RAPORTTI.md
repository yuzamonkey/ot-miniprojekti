# Loppuraportti

### Projektiin osallistuneet
* Karita Kivi
* Vesa Louhelainen
* Jarkko Mäenrinta
* Henri Tarkiainen
* Jussi-Jaakko Mankki

## Sprinttien aikana kohdatut ongelmat

### Sprintti 1

Kaikille ryhmäläisille ohjelmistoprojektin teko ryhmässä oli uutta, eikä ryhmällä ollut varmuutta mitä oltiin tekemässä. User storyjen keksiminen ja muotoilu oli hankalaa, ja ne veivät paljon aikaa. Päätimme että projektissa käytetään Javaa, Gradlea ja Cucumberia, mutta emme sopineet mitä versioita. Siksi joitain ongelmia tuli vastaan pushauksien yhteydessä. Yksi ryhmäläisistä jätti projektin kesken omien kiireidensä takia, ja Vesa puolusti kotimaan metsiä ensimmäisen sprintin ajan. Vesa kuitenkin ehti hoitaa suurimmat konfiguraatiot. Ensimmäisen sprintin jälkeen totesimme retrospektiivissä, että tulevat sprintit tulee suunnitella paremmin.

### Sprintti 2

Toisessa sprintissä ongelmat kohdistuivat backlogin käyttöön ja ohjelman sisäiseen rakenteeseen. Käytimme valmista pohjaa backlogina, mutta emme päivittäneet burndown-kaaviota oikein. Korjasimme kaavion myöhemmin oikeaksi. Sovimme, että käytämme ui-, domain- ja dao-paketteja, mutta alussa emme hyödyntäneet niitä. Ongelma ei ollut kuitenkaan suuri, sillä ohjelma toimi ja pakettien välisiä riippuvuuksia pystyi helposti päivittämään. Testausta ei juurikaan tehty, ja cucumber-testejä ei kirjoitettu niille tarkoitetulla tavalla. Rivikattavuus oli n.20%, josta tuli noottia viikkotapaamisessa. Ongelmia oli myös jar-tiedoston luonnissa releasea varten, asia hoitui Telegram-keskustelussa.

### Sprintti 3

Tietokantaskeema päivitettiin käytännössä kokonaan, koska aikasempi rakenne oli sekava. User storyissä oli story "Käyttäjä voi poistaa vinkin", ja sille task "Vinkin poistaminen poistaa sen tietokannasta". Toteutus suunniteltiin uuden tietokantaskeeman takia niin, että vinkkiä ei poisteta vaan se piilotetaan. Siis task ja toteutus eivät vastanneet toisiaan, josta aiheutui, että vinkki ja siihen kohdistuvat rivit muissa tietokantatauluissa poistettiin tietokannasta kokonaan. Tämä tuli kuitenkin nopeasti esille Telegramissa, ja asia käsiteltiin nopeasti.

### Yleisiä ongelmia

Koodaaminen ryhmässä oli tosiaan uusi asia, joten yleiseen ihmettelyyn ja koodin tutkimiseen meni välillä paljon aikaa. Jos ei ollut muutamaan päivään projektin parissa, oli tutustuttava koodiin ja committeihin huolellisesti. Ajattelimme, että daily scrumit auttaisivat tähän ongelmaan suuremmissa projekteissa.

Testit otettiin taskeiksi vasta kolmannessa sprintissä, joka luonnollisesti lisäsi tunteja työmääräarvioon. Testaaminen olisi kannattanut kirjoittaa taskeina jo toisessa sprintissä.

Branchien käyttö olisi saattanut helpottaa joidenkin merge-konfliktien hallintaa. Niitä oli kuitenkin vähän tämän kokoluokan projektissa.

## Mikä sujui projektissa hyvin, mitä pitäisi parantaa seuraavaa kertaa varten

### Hyvää

Ryhmätyöskentely toimi hyvin. Sprintteihin valittiin sopiva määrä user storyja, jotta kaikille ryhmäläisille oli tekemistä. Backlogista oli helppo katsoa, mitä kannattaa tehdä seuraavaksi. Oli myös hyvä, että backlogille oli valmis pohja, ettei excel-velhoilua vaadittu. Ajankäyttö oli järkevää. Kukaan ryhmän jäsenistä ei tehnyt pitkiä pätkiä kerrallaan, vaan projekti rakentui tasaisesti sprintin aikana. Hyvä ajankäyttö lienee myös syy sille, että päällekkäisyyksiä tai ristiriitoja ei kirjoitettu koodiin. Vaaditut ominaisuudet saatiin toteutettua, mitään kriittistä ei jäänyt uupumaan. Lisäksi Telegram toimi hyvin ryhmän keskustelukanavana. Telegramiin usein päivitettiin pienimmätkin muutokset projektiin, jonka ansiosta kaikki ryhmässä oli kartalla hyvin.

Ryhmän yleinen fiilis on, että kurssin aikataulun puitteissa projekti meni hyvin.

### Parannettavaa

Työnjakoa olisi voinut selkeyttää. Tosin kurssin aikataulu ei sellaista juurikaan olisi sallinut, ja backlogista pystyi priorisoimaan mitä kannattaa tehdä seuraavaksi.

Tietokantaskeeman olisi voinut suunnitella paremmaksi aikaisemmin. Pohdimme kuitenkin, olisiko se johtanut vesiputousmallimaiseen ajatteluun. 

Cucumber-testejä ei juurikaan tehty. Lisäksi ne testit mitä cucumberilla testattiin, ovat testattu yksikkötesteinä.

## Mitä asioita opitte, mitä asioita olisitte halunneet oppia, mikä tuntui turhalta

### Mitä opimme
* Backlogien toiminta ja sen tärkeys. Backlogin avulla pysyi kartalla miten projekti etenee.
* Scrum ja ketteryys käytännössä
* Ryhmätyöskentely ja ryhmässä ohjelmointi
* Gitin käyttö ryhmäprojektissa

### Mitä olisimme halunneet oppia
* Parikoodaus ja koodaus samassa paikassa
* API:n käyttö

### Mikä oli turhaa
Projekti oli pieni ja lyhyt, emme keksineet turhuuksia.
