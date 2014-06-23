---
layout: post
title: "Introduction &agrave; Hadoop"
date: 2014-06-23
comments: true
categories: 'big data'
published: true
---

[Hadoop](http://hadoop.apache.org/) est un système permettant de gérer des très gros volumes de données aussi bien au niveau de leur
stockage qu'au niveau de leur traitement. Pour mettre les choses au clair tout de suite, Hadoop est une solution à oublier en 
dessous d'une grosse dizaine de serveurs. Hadoop est un système taillé pour gérer des centaines voir des milliers de serveurs. Il faut donc dégainer cette solution quand c'est nécéssaire. Google utilise par exemple ce type de technologie pour gérer les gros 
traitements sur son cluster de serveurs. C'est d'ailleurs Google qui est à l'origine des concepts fondateurs d'Hadoop.

<!--more-->

Hadoop n'est pas monobloc, c'est plutot un ensemble de composants spécialisés.

# HDFS

Hadoop Distributed File System (HDFS) est l'implémentation open source des concepts introduits par le Google File System.
C'est un système de fichier ditribué, taillé pour les gros volumes de données. HDFS est capable de gérer des volumes de 
plusieurs péta octets. Les blocs de données sont proportionnelles à la volumétrie potentielle et sont fixés par défaut à 
64 méga octets.  

![HDFS Architecture](/images/posts/2014-06-15-hadoop-big-data/hdfsarchitecture.gif)
(source http://hadoop.apache.org/docs/r1.2.1/hdfs_design.html)  

Le Namenode est le serveur du cluster Hadoop s'occupant des méta-données du file system (Nom, réplication, emplacement, etc...)

Les serveurs de données eux stockent les blocs des fichiers.

# Map Reduce

Map Reduce est un modèle de programmation permettant de distribuer l'éxécution d'une tache sur plusieurs serveurs (e.g. un tri, une
indexation, un calcul, etc.) Le "travail" de Map Reduce va être de découper, trier et rassembler les données à différents moment de
l'éxécution d'une tâche.

On peut schématiser l'éxection d'un batch Map Reduce avec le schéma suivant:

![HDFS Architecture](/images/posts/2014-06-15-hadoop-big-data/MapReduce.png)

- Les données stockées dans des blocs HDFS vont être tronçonnées en InputSplit.
    - e.g. des lignes d'un fichier de log
- Les records readers vont assigner une clé k1 et une valeur v1 pour toute donnée lue.
    - e.g. le numéro de la ligne comme clé, la ligne compléte en valeur
- Le fonction de Mapping va lire et transformer cette première entrée en une autre paire clé-valeur (k2, v2).
	- e.g. La ligne de log est analysée, on met en clé le nom d'un utilisateur et en valeur 1, représentant un login par exemple
- La fonction de partition va déterminer en fonction de la clé k2 vers quel Reducer la clé va être routée
- La fonction de Shuffle and Sort rassemble par clé k2 toutes les valeurs v2 émises pas les Mappers
	- e.g. On se retrouve à cette étape avec le nom d'un utilisateur et une liste de 1 correspondant à toutes les occurences de connections
- Le Reducer va transformer une clé k2 et sa liste de valeur v2 en une nouvelle clé k3 et une nouvelle valeur v3.
    - e.g. Le reducer va sommer toutes les valeurs pour un utilisateur en comptant toutes les valeurs de la liste. Par exemple evidal c'est connecté 3 fois.
- Le traitement étant terminé, on peut procéder à l'écriture des fichiers.  

Implémentez un job Map Reduce pour Hadoop consiste en l'assemblage de Mapper, Reducer, Reader, Writer, Partionner.

Des implémentations de certaines de ces classes sont déjà fournies pour des tâches simples (e.g. identités).

L'assemblage se fait via une une classe Java qui va positionner via des setters les éléménts à utiliser.
On livre sur le cluster un jar contenant les classes nécéssaires à l'éxécution d'un job.

Pour l'éxécution, Hadoop va instancier une JVM sur les différents noeuds et éxécuter le job qu'on a livré.  
En développement, un mode classique (lancé de manière transparente) permet d'éxécuter un job Map Reduce en dehors d'un cluster Hadoop.  
Pour les test unitaire, on peut utiliser des outils comme [MRUnit](http://mrunit.apache.org/).  

# Hive, Impala, Pig & Oozie

Map Reduce, c'est bien mais ça peut devenir un peu fastidieux s'il faut écrire des jointures. D'autant plus que ce n'est pas vraiment passionnant à coder. Des outils de plus haut niveau ont été développés pour travailler plus facilement qu'avec Map Reduce.

* [Hive](http://hive.apache.org/) est un langage très similaire au SQL. Tout d'abord on mappe les données d'un fichier pour exposer son contenu comme une table. Une fois que l'on a déclaré plusieurs tables, on peut écrire des requêtes pour interroger le cluster Hadoop comme un base de données relationnelle. Ca semble trivial, mais il faut toujours penser que ce système est fait pour gérer de très gros volumes de données, pas des volumétries standards.
```
SELECT stock.product, SUM(orders.purchases)
FROM stock JOIN orders
ON (stock.id = orders.stock_id)
WHERE orders.quarter = 'Q1'
GROUP BY stock.product;
```
* [Pig](http://pig.apache.org/) est un autre système permettant d'écrire des jobs Map Reduce de manière plus simple. Pig prend plus la forme d'un script, on indique quelles données charger et les opérations à faire avec.
```
stock = LOAD '/user/fred/stock' AS (id, item);
orders = LOAD '/user/fred/orders' AS (id, cost);
grpd = GROUP orders BY id;
totals = FOREACH grpd GENERATE group,
SUM(orders.cost) AS t;
result = JOIN stock BY id, totals BY group;
DUMP result;
```
* [Impala](http://www.cloudera.com/content/cloudera/en/products-and-services/cdh/impala.html) ressemble à Hive du point de vue syntaxique. La grosse différence entre les 2 est qu'Impala ne va pas générer un job Map Reduce, mais va utiliser d'autres algorithmes pour éxécuter la requête et donner une réponse très rapidement.

Oozie est un système permettant d'écrire plusieurs tâches qui vont s'enchainer. Par exemlple commencer par un job Hive, suivi d'un job Map Reduce classique et enfin d'un autre job Hive. Cela permet de créer des chaines de traitement plus complexes.

# Distribution

Si Hadoop est disponible sur le site du projet Apache, des distributions sont également disponibles chez des éditeurs:

* [Cloudera](http://www.cloudera.com/content/cloudera/en/home.html)
* [Horton Works](Horton Works)
* [MapR](http://www.mapr.com/)

Le gros avantage de passer par une distribution commerciale est que l'on peut acheter du support. Parfois, ces distributions assemblent des versions différentes des composants core pour avoir un fonctionnement plus cohérent.

Enfin, sur leurs sites, des machines virtuelles (e.g. chez [Cloudera](http://www.cloudera.com/content/support/en/downloads/quickstart_vms/cdh-5-0-x.html)) sont disponibles pour tester, ce qui est un gros gain de temps.

# Conclusion

Hadoop n'est pas la solution que l'on va sortir à tout bout de champ. Le but est de manipuler de très très gros volumes de données.
C'est clairement un outil qui va devenir de plus en plus utile avec l'explosion des données récoltées par les objets connectées ou toutes les traces que nous laissons sur Internet.

