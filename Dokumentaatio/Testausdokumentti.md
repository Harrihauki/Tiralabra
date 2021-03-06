*JUnit-testaus:*

JUnit-testeillä on testattu luokkia Sokkelogeneraattori, Astar, Dijkstra, ASolmu, Solmu ja Heap.

Sokkelugeneraattorissa on testattu, että

    - Generaattori asettaa lähtö- ja maalipisteet oikein
    - Generaattori asettaa sokkelon reunoille seinät, jotta ei erikseen tarvitse tarkistaa, lähtevätkö algoritmit ulos sokkelosta

Astarista on testattu, että

    - initialiseSingleSource-metodi luo oikeanlaisen taulukon
    - Ratkaisun pituus on oikea käsintehdyssä testisokkelossa
    - Ratkaisun polku on myös oikea testisokkelossa

Dijkstrassa on testattu samat ominaisuudet.

Solmu- ja ASolmu-luokissa on testattu, että niiden vertailut toimivat oikein, eli tarkemmin tulevat oikeassa järjestyksessä keosta.

Heapissä on testattu, että

    - add-metodi toimii oikein ja pitää keko-ehdon yllä pienillä ja suurilla syötteillä
    - poll-metodi toimii samaten pienillä ja suurilla syötteillä
    
Mukaan liitetty pit antaa rivikattavuudeksi mainituille luokille >90% ja mutaatiotestaukseksi 64%.

Lisäksi algoritmien toimivuutta on testattu ohjelman ominaisuudella, jossa generoidaan 100 satunnaista 500*500-kokoista 
sokkeloa molemmat algoritmit etsivät lyhimmän reitin kussakin sokkelossa. Löydettyjen reittien pituuksia vertaillaan keskenään. 
Mikäli kaikissa sokkeloissa algoritmit löytävät yhtä pitkän reitin, tulostetaan 'true'. Jos taas jossakin reitin pituudessa 
on eroa, tulostetaan 'false'. Ottaen huomioon, että kumpikin algoritmi löysi käsin generoidusta testisokkelosta sekä oikean 
reitin, sekä sen, että jokaisella ajolla algoritmit löysivät yhtä pitkän reitin jokaisessa sokkelossa, on oletettavaa, että 
algoritmit löytävät lyhimmän reitin. Mikäli jompi kumpi algoritmi ei löytäisi, pituuksissa olisi eroja. Vastaavasti mikäli 
kumpikaan ei toimisi, olisi erikoista, että molemmat sattumalta näillä otannoilla löytäisivät aina yhtä pitkän väärän reitin.

Kaikki testit on helppo toisintaa, sillä ne ovat joko projektiin kuuluvia JUnit-testejä tai osa ohjelman toiminnallisuutta.
