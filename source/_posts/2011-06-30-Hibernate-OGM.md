---
layout: post
title: "Hibernate OGM"
date: 2011-06-30
comments: true
categories: java
---

[Emmanuel Bernard](http://blog.emmanuelbernard.com/) était au Lyon JUG ce jeudi 23 juin, il nous a présenté Hibernate OGM (pour Object Grid Mapping), un nouveau sous-projet de l'équipe Hibernate dédié au NoSQL. En deuxième partie, un dialogue permettait de mieux connaître les solutions Hibernate.

<!--more-->

## Hibernate OGM

Le but d'Hibernate OGM et d'offrir une API standard pour fédérer les différents solution NoSQL.

Premier problème, chaque solution NoSQL a son propre modèle de stockage de données:

- Stockage Clé-Valeur (Hashmap), on stocke une clé et une valeur [Voldemort](http://project-voldemort.com/), [Infinispan](http://www.jboss.org/infinispan)
- base de données orientées graphe [Neo4j](http://neo4j.org/)
- base de données orientée document [Couch DB](http://couchdb.apache.org/), [Mongo DB](http://www.mongodb.org/)
- base de données orientée Colonne  [Cassandra](http://cassandra.apache.org/), [Google Datastore](http://code.google.com/intl/fr/appengine/docs/python/gettingstarted/usingdatastore.html)

![Technos NoSQL](http://blog.xebia.fr/wp-content/uploads/2010/04/nosql.png)

Source [Xebia](http://blog.xebia.fr/2010/04/21/nosql-europe-tour-dhorizon-des-bases-de-donnees-nosql/)

Deuxième problème, comme avant l'avènement de SQL, aucune solution NoSQL n'a le 
même système de requêtage, ni les mêmes capacités.

Hibernate OGM a commencé il y a environ un an et Emmanuel y a bosser à temps partiel
cette année, le temps de laisser mûrir les choses. Emmanuel c'est beaucoup appuyé
sur Hibernate Core et Hibernate Search pour implémenter Hibernate OGM.
Pour l'instant Hibernate OGM supporte:

- [Infinispan](http://www.jboss.org/infinispan)
- CRUD d'objets
- requêtes Hibernate Search

Il ne supporte pas encore:

- JP-QL
- autre chose qu'[Infinispan](http://www.jboss.org/infinispan)

Une démo a été montrée, Emmanuel faisait tournée une série de tests en utilisant
la base de données H2. Il faisait tournée la même démo mais en changeant la conf
de son persistence.xml pour utiliser Hibernate OGM.
Emmanuel a lancer un appel à tous les développeurs qui souhaitent proposer des
améliorations ou des implémentations pour d'autre solution NoSQL.
Le projet n'en est qu'à son début mais est à surveiller par toutes les entreprises
qui souhaitent tester une solution NoSQL mais sans trop dépendre d'une solution
particulière. Cela leur permettra éventuellement de revenir à du SQL standard.
Pour l'instant je pense que l'approche est à surveiller pour voir comment elle va
évoluer. Je me deamnde juste si dans le cadre de NoSQL, la persistence objet a du
sens. En effet, vu la volumétrie des données qu'on est censée gérer dans des BBD
de type NoSQL, est-ce quel le modèle JPA est le meilleur ? 
Je n'ai pas la réponse pour l'instant.

Les slides de la présentation sont disponible [ici](http://emmanuelbernard.com/various/presentations/hibernate-ogm-concepts-1.1.pdf)

## Hibernate
La deuxième partie de la conférence était un dialogue entre Emmanuel et le public
à propose des différentes solutions Hibernate. J'ai découvert un module d'Hibernate
que je ne connaissais pas Hibernate Envers et qui permet de versionné des entités.
Très bon !

Merci au Lyon JUG, c'était vraiment une bonne soirée.
