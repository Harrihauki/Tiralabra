Ohjelman toteuksessa on päätetty ilmentää kumpaakin vertailtavaa algoritmiä - A* ja Dijkstra - omalla luokallaan, jotka sisältävät
algoritmin suoritukseen tarvittavat metodit. Puhtaasti koordinaateilla toimimisen sijaan on verkon solmuja päätetty mallintaa
käytännöllisyyssyistä luokalla Solmu ja A*:ssa sen aliluokka ASolmu. ASolmusta luodut oliot toimivat muuten kuten Solmu-luokasta
luodut oliot, mutta sen compareTo-metodi ottaa huomioon myös ASolmu-luokassa olevan etäisyysarvion haluttuun maalisolmuun.
Vertailussa apuna käytetään myös Sokkelogeneraattori-luokkaa, joka luo halutun kokoisen satunnaisen verkon. Näin syötettä ja sen
kokoa on helppo muuttaa. Noin 1/10 verkon solmuista on pääsemättömiä, noin 2/5 solmuista hankalapääsyisempiä "kolmen pituisia" solmuja ja loput tavallisia, helppokulkuisia solmuja.

(Tänne myöhemmin mm. O-analyysit ja vertailut "vedettynä yhteen" toisesta dokumentista.)
