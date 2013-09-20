---
layout: post
title: "Elastic Search"
date: 2012-10-18
comments: true
categories: [conf, elastic search]
---

Elastic Search est un moteur de recherche basé sur Lucene expérimenté par la société
dans laquelle je travaille depuis un certain temps. Ce moteur était présenté
dans le cadre du Lyon JUG par [David Pilato](http://dev.david.pilato.fr/) et [Tanguy Leroux](https://twitter.com/tlrx).

L'interview pre-JUG par les Duchess est disponible [ici](http://www.duchess-france.org/rencontre-avec-david-pilato-sur-elastic-search/).
La présentation elle même est disponible [ici](http://t.co/piqUOkTE).

Je ne vais pas réexpliquer le fonctionnement d'Elastic Search car de nombreux articles le font déjà (et notamment la présentation de David Pilato). 
De notre coté nous avons sélectionner Elastic Serach pour plusierus fonctionnalités importantes pour nous:

* Nos logicielles sont déployés sur des clusters pour avoir un fonctionnement Actif-Actif. C'est une des fonctionnalités de base d'Elastic Search.
* Nous manipulons des données très volumineuses en terme de nombre (plusieurs dizaines, voir centaines de millions). Elastic Serach est "taillé" pour ce volume.
* La navigation par "Facet" qui nous est nécéssaire


Cependant nous en sommes encore au stade de l'étude et nous n'avons pas encore de vrai déploiement utilisant cette technologie.  

Voilà quelques points importants que j'ai noté pour mémoire:

* Sécurisation,
    * pour l’instant Elastic Search n’est pas sécurisé. Il est possible qu’il le devienne mais pas dans les toutes prochaines versions.
    * 1ère stratégie : mettre un apache devant et « jouer » avec les règles d'un firewall (DMZ)
    ** ajouter une couche http authent pour l'accès
    ** utiliser des filtres pour restreindre l’utilisation de certaines commande (comme DELETE, PUT)
    ** en séparant les index, on peut spécifier des chemins d’accès à des données et gérer des profils
    * 2ème stratégie, utiliser le plugin suivant https://github.com/sonian/elasticsearch-jetty (solution recommandé uniquement par 1 des speakers)
* Cluster et déploiement
    * Les préconisation de déploiement sont de 1 shard par serveur (1 shard = 1 instance de Lucene) + 1 replica
    * /!\ on ne peut pas changer le nombre de shards une fois qu’un index est crée
    * Les intervenants recommandent de bien dimensionner son cluster dès le départ, rajouter un nouveau membre au cluster est assez lourd en terme de consommation sur des indexes volumineux
    * Il est préférable d’effectuer les montées de version à froid (même si des procédures à chaud existent)
    * D’après le concepteur d’Elastic Search si une requête met plus de 100ms, c’est que la plate-forme est sous dimmensionné.
* Il existe une JDBC River qui permet automatiquement d’indexer une table au fil de l’eau
    * https://github.com/jprante/elasticsearch-river-jdbc
* Un projet d’indexation de Log existe déjà
    * http://logstash.net/
* Déploiement de références
    * Des déploiements existent avec 25 noeuds indexant plus d’1 milliard de documents
    * Le premier intervenant (David Pilato) a travaillé sur un projet indexant 500.000 documents des douanes françaises. Le projet tournait sur 3 serveurs low end (CPU 2 cores, mémoire 4GB)
    * Le deuxième (Tanguy Leroux) a travaillé pour un assureur pour indexer 20.000.000 documents (chaque document était l’équivalent d’une page word). Le projet tournait sur 4 serveurs low end (CPU 2 cores, mémoire 4GB)
* Utiliser Elastic Search comme un entrepot de données
    * le créateur le déconseille pour l'instant mais ce sera peut-être possible à l'avenir.
    * Un des intervenant a indiqué qu'il connaissait un projet utilisant Elastic Search comme base de données principale. Mais dans ce cas il fallait faire une croix sur les transactions et autres fonctionnalités d'une base de données. 


Session très intéréssante et speakers disponibles pour répondre aux questions !

Merci le Lyon JUG. 

