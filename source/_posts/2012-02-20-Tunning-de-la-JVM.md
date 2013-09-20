---
layout: post
title: "Tunning de la JVM"
date: 2012-02-20
comments: true
categories: [java, perf]
---

Le 17 janvier 2012 avait lieu une soirée au Lyon JUG ayant pour thème la performance des serveurs en java. Le speaker Ludovic Poitou est un ancien de chez Sun qui a quitté la société suite au rachat par Oracle. 

Sa présentation était originale, car contrairement à beaucoup d’entre nous, son logiciel [Open DJ](http://forgerock.com/opendj.html) ne tourne pas sur un serveur d’application mais en standalone. 

Après une présentation détaillée de sa société, j’ai beaucoup aimé l’explication de la gestion de la mémoire de la JVM. J’avoue que c’est un sujet sur lequel je me suis peu penché car les applications sur lesquelles je travaille n’ont pas le même niveau d’exigence que c’est fixé l’équipe d’Open DJ. L’application la plus “chargée” sur laquelle j’ai travaillé répondait sans broncher à 400 requêtes/seconde HTTP en pointe sur un seul serveur, ce qui est déjà un score honorable sur un serveur d’application d’il y a 5 ans. Mais je ne joue pas dans la même cour, le serveur Open DJ monte à plus de 80000 requêtes/seconde, 200 fois plus de charge.

Pour arriver à ce niveau de performance, il faut avoir une très bonne connaissance du fonctionnement de la JVM, et notamment de la gestion de sa mémoire. Open DJ utilise également une base donnée simple et embarquée : [Berkeley DB](http://www.oracle.com/technetwork/database/berkeleydb/overview/index.html)

Je vous invite à relire la présentation disponible via le site du Lyon JUG ([ici](http://www.lyonjug.org/evenements/perf-serveur)).

Suite à cette présentation je me suis penché sur les paramètres qu’on utilisait pour faire tourner notre JBoss en production. On avait optimisé les options de la JVM de manière un peu empirique, en s’appuyant sur des morceaux de configuration qu’on avait récupéré à travers différents blogs et aussi suite à de nombreuses campagnes de test.
Voilà ce que nous utilisons en production:

* -d64 : pour faire fonctionner la JVM en 64 bits, et donc adresser au delà de 4Go
* -Xms8192m, pour réserver au minimum 8Go de Mémoire vive. C’est une stratégie défensive pour être sur que notre JVM ne se fera pas “piquer” la mémoire par d’autres applications.
* -Xmx8192m, pour ne pas dépasser ces fameux 8Go et donc laisser de l’espace aux autres applications tournant sur le serveur.
* -XX:MaxPermSize=512m, l’espace la mémoire de type Permanent Generation contient toute les données statiques de la JVM. Il faut qu’il soit suffisamment grand mais pas démesuré. 512 Go est un bon chiffre pour les applications allouant au delà de 8Go de mémoire, il peut être réduit à moins pour les applications plus petite. Il est extrêmement rare d’allouer au delà de 512Mo (jamais vu de mon coté)
* -Djava.awt.headless=true, l’option classique pour les serveurs Unix n’ayant pas de serveur X installé
* -Dorg.jboss.resolver.warning=true, c’est une option qui était déjà présente. On l’a laissée.
* -Dsun.rmi.dgc.client.gcInterval=3600000, c’est une option qui permet de donner la fréquence de garbage collection. En l'occurrence ça ne sert à rien car nous lui avons donné la valeur par défaut.
* -XX:+UseParNewGC, cette option permet de gérer la copie des objets dans la portion de mémoire à plus long terme de manière “multi-threadée” en profitant de la puissance des machines multi-cpu (ce qui est notre cas)
* -XX:+AggressiveOpts, je pense que cette option est aussi inutile dans le sens ou elle est devenue activé par défaut depuis la version du JRE 1.5_06. Elle permet d’activer des flags de compilation plus performant.
* -XX:+DoEscapeAnalysis, c’est un flag qui permet à la JVM d’optimiser les locks sur l’application, plus d’explication [ici](http://blog.xebia.com/2007/12/21/did-escape-analysis-escape-from-java-6/)
* -XX:+UseLargePages, option permettant d’allouer de gros espaces mémoire, pour plus de détail aller voir [ici](http://www.oracle.com/technetwork/java/javase/tech/largememory-jsp-137182.html)
* -XX:+UseTLAB, option permettant à chaque thread d’allouer sa propre mémoire. C’est une option à activer sur les systèmes multi-processeurs.
* -XX:TLABSize=64k, cette option spécifie la taille de la young generation pour chaque thread.

Pour compléter ce petit billet, voilà 2 liens utiles

* la [documentation](http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html) officielle de la commande java
* un [blog](http://www.datadisk.co.uk/html_docs/java_app/jboss5/jboss5_tuning.htm) qui nous avait bien aidé
