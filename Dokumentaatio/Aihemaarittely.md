Aiheena on kahden tai kolmen polunetsintäalgoritmin - A* ja Dijkstra rakentaminen ja niiden ominaisuuksien vertailu. Kumpi on nopeampi? Millaisia polkuja algoritmit rakentavat? Polkualgoritmit olivat Tietorakenteet ja algoritmit -kurssin luultavasti mielenkiintoisin osa-alue, joten niiden rakentaminen ja tutkiminen tuntui mielenkiintoiselta ajatukselta harkkatyön aiheeksi.

Ohjalman on tarkoitus sisältää jonkin verran valmiita verkkomalleja, joita voi ratkoa, sekä mahdollisesti sisältää mahdollisuus käyttäjän itse piirtää käsiteltäviä verkkoja. Myös satunnaisesti luotu verkko voisi olla mielenkiintoinen ominaisuus.

Tietorakenteena toteutettavana on keko, eli Heap. Ja vielä tarkemmin, toteutetaan minikeko.

Koska Heapin metodit toimivat aina suhteessa muutoksen kokeneeseen alipuuhun, ja keon korkeus on O(logN), ovat teoreettiset aikavaativuudet Heapin kaikille metodeille O(logN).

Teoreettiset aikavaativuudet A*:lle ja Dijkstralle ovat O((V + E)logV.

Lähteinä käytetty syksyn 2015 Helsingin yliopiston Tietorakenteet ja algoritmit -kurssin materiaalin kekoa, Dijkstraa ja Astaria käsitteleviä kappaleita (https://www.cs.helsinki.fi/u/floreen/tira2015syksy/sivut351-638.pdf), sekä Astaria käsittelevää wikipedia-sivua (https://en.wikipedia.org/wiki/A*_search_algorithm)
