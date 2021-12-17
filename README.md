# Ohtuprojekti
![GitHub Actions](https://github.com/yuzamonkey/ot-miniprojekti/actions/workflows/gradle.yml/badge.svg)
[![codecov](https://codecov.io/gh/yuzamonkey/ot-miniprojekti/branch/main/graph/badge.svg?token=FzoGfVPtB8)](https://codecov.io/gh/yuzamonkey/ot-miniprojekti)

[Loppuraportti](https://github.com/yuzamonkey/ot-miniprojekti/blob/main/RAPORTTI.md)

## Definition of done
- Testikattavuus on vähintään 80%
- Uusilla ominaisuuksilla on testit
- Uudet ominaisuudet ovat onnistuneesti integroitu vanhaan
- Ohjelma käynnistyy ja toimii kaatumatta
- Käyttöjärjestelmä ilmoittaa virhetilanteesta tulostamalla virheen
- Demoon päätyy vain valmiit osat (sprintit)

## Backlog
[Projektin backlog](https://docs.google.com/spreadsheets/d/1_9Cdh7jeI5BNhq2ehOIAos2kMRqyZfJPkbvNDel6HRg/edit#gid=0)

## Asennus- ja käyttöohje
Ohjelma toimii komentoriviltä. Ohjeet on kirjoitettu Linux-käyttöjärjestelmälle. 

### Asennus
Mene kansioon johon haluat ohjelman luoda. 
Anna komento:
```
git clone git@github.com:yuzamonkey/ot-miniprojekti.git
```
Komento luo nykyiseen kansioosi uuden kansion **ot-miniprojekti**.

### Käyttö
Mene kansioon **ot-miniprojekti**, jonka loit asennuksen yhteydessä. 

Ohjelma käynnistyy komennolla
```
./gradlew -q --console plain run
```
Voit valita aukeavasta valikosta toimintoja syöttämällä ensin haluamasi toiminnon numeron ja painamalla *enter*. Ohjelma sulkeutuu syötteellä *x*, tai  virhetilanteessa painamalla näppäimiä Ctrl+c.

### Jar tiedosto

Ohjelmasta voi generoida suoritettavan jar-tiedoston. Sen voi tehdä kansiossa **ot-miniprojekti** komennolla
```
./gradlew shadowJar
```

Jar tiedosto tallentuu kansioon **build/libs/**. Sen voi käynnistää kansiossa **ot-miniprojekti** komennolla
```
java -jar build/libs/ot-miniprojekti-all.jar
```



