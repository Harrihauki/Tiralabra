*Yleinen rakenne:*

Ohjelman toteuksessa on päätetty ilmentää kumpaakin vertailtavaa algoritmiä - A* ja Dijkstra - omalla luokallaan, jotka sisältävät algoritmin suoritukseen tarvittavat metodit. Puhtaasti koordinaateilla toimimisen sijaan on verkon solmuja päätetty mallintaa käytännöllisyyssyistä luokalla Solmu ja A*:ssa sen aliluokka ASolmu. ASolmusta luodut oliot toimivat muuten kuten Solmu-luokasta luodut oliot, mutta sen compareTo-metodi ottaa huomioon myös ASolmu-luokassa olevan etäisyysarvion haluttuun maalisolmuun. Vertailussa apuna käytetään myös Sokkelogeneraattori-luokkaa, joka luo halutun kokoisen satunnaisen verkon. Näin syötettä ja sen kokoa on helppo muuttaa. Noin 1/10 verkon solmuista on pääsemättömiä, noin 2/5 solmuista hankalapääsyisempiä "kolmen pituisia" solmuja ja loput tavallisia, helppokulkuisia solmuja. Syötteeksi annetaan halutun verkon leveys ja korkeus, sekä maali- ja lähtösolmun koordinaatit.

*Tila- ja aikavaativuus:*

Koska kumpikin algoritmi on rakenteeltaan olennaisesti sama, voidaan algoritmeille laatia yhtenevä pseudokoodi ja siten yhtenevä O-analyysi. Analyysiä voi toki seurata myös ohjelman koodista.

Algoritmien aikavaativuus on olennaisesti ratkaise-metodin aikavaativuus. Metodin aloittava initialiseSingleSource-metodissa alustetaan apuna käytettävä Solmu-luokan ilmentymistä koostuva, käytetyn verkon kokoinen kaksiulotteinen taulukko, johon liitetään lähtösolmu. Aikavaativuus on Java-kielellä selvästi O(n), koska taulukon kaikki paikat on alustettava. Samaten booleaneista koostuvan, kaksiulotteisen onkoNostettu-taulukon, kuin myös Heapin alustaminen ovat kertaluokaltaan O(n). Heap kun alustaa konstruktorissaan syötteen suhteen vakiokertoimisen taulukon sekin.

etsiPolut-metodissa on toistolause, joka suoritetaan pahimmillaan V + D kertaa, missä V on verkon solmujen lukumäärä ja D mahdollisten kekoon päätyvien solmujen duplikaattien lukumäärä. Mikäli keosta nostetaan duplikaatti, on yksittäinen suoritus aikavaativuudeltaan O(log(V + D)), koska keon korkeus on log(n), missä n on keossa olevien alkioiden lukumäärä ja kekoehdon ylläpitäminen nostamisen jälkeen vie aikaa O(log(n)). Mikäli taas ei nosteta duplikaattia, suoritetaan kaikille solmun vierussolmuille relax-operaatio, toisin sanoen tutkitaan, onko nostetun solmun kautta kulkeva reitti vierussolmuun lyhin tähän asti löydetyistä reiteistä. Mikäli näin on, vierussolmu lisätään kekoon. Myös kekoon lisääminen on aikavaativuudeltaan - kuten kaikki keko-operaatiot - O(log(n)). Näin ollen koko metodin aikavaativuus on O(Elog(V + D) + Dlog(V + D) + Vlog(V + D)), missä E on kaarien lukumäärä. Tämä sievenee muotoon O((V + E + D)log(V + D)). Lisäksi huomaamme, että koska yksittäiseen solmuun u liittyvien kaarien e lukumäärä on ylhäältä rajattu s.e. e <= 4, mistä selvästi seuraa, että E <= 4*V. Samasta havainnosta seuraa myös se, että yksittäisestä solmusta voidaan lisätä maksimissaan neljä duplikaattia, joten selvästi myös D <= 4*V. Näin ollen (V + E + D)log(V + D) <= 9Vlog(5V), ja siten voidaan peräti merkitä aikavaativuudeksi O(VlogV), joka selvästi hallitsee koko algoritmiä ja on siten sen aikavaativuus ja jopa alittaa algoritmien teoreettisen aikavaativuuden. Tämä tietysti pätee vain kyseisten verkkojen kaltaisissa erityistapauksissa, joissa kaarien määrä on selvästi ylhäältä rajoitettu.

Algoritmit luovat kolme vakiokertoimella syötteeseen suhteutettavaa taulukkoa, joten algoritmien tilavaativuus on selvästi O(n).

Koska algoritmit ovat yhtä tehokkaita O-notaation kannalta, ei sen suhteen tehty vaativuusvertailu ole mielekästä, eikä oikeastaan edes mahdollista.

*Suorituskykyvertailu:*

Tämä osio on pääosiltaan siirretty omaan dokumenttiinsa luettavuuden vuoksi. Yhteenvetona voidaan todeta Astarin päihittävän Dijkstran testien perusteella millä tahansa syötekoolla.

*Puutteet ja parannusehdotukset:*

Loppujen lopuksi ohjelman olisi voinut toteuttaa vain yhdellä algoritmi-luokalla. Tämä siksi, että olennaista eroa on vain siinä, mikä solmu keosta vedetään, ja tämän hoidan Solmun ja ASolmu compareTo-metodien eroilla. Lisäksi tällaisenaan ohjelma toimii vain kaksiulotteisella taulukolla ja rajatuilla ascii-merkeillä. Siten vertailu on varsin teoreettispohjaista ja soveltuu ehkä korkeintaan joihinkin yksinkertaisiin peleihin sovellettavaksi suoraan. Joku graafinen käyttöliittymä olisi myös ollut kiva, mutta sille ei jäänyt aikaa.

*Lähteet:*

Lähteinä käytetty syksyn 2015 Helsingin yliopiston Tietorakenteet ja algoritmit -kurssin materiaalin kekoa, Dijkstraa ja Astaria käsitteleviä kappaleita (https://www.cs.helsinki.fi/u/floreen/tira2015syksy/sivut351-638.pdf), sekä Astaria käsittelevää wikipedia-sivua (https://en.wikipedia.org/wiki/A*_search_algorithm)
