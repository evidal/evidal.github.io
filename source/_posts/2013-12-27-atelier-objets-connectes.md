---
layout: post
title: "Atelier des Objets Connect&eacute;s"
date: 2013-12-27
comments: true
categories: [conf, objets]
published: true
---

Le 12, 19 et 26 novembre j'ai un suivi une serie d'[atelier sur les objets connectés](http://www.atelier-objets-connectes.org/) proposée par le [LIRIS](http://liris.cnrs.fr/?set_language=fr) et le [Fablab de lyon](http://www.fablab-lyon.fr/). Ça fait longtemps que j'ai envie de manipuler des [arduino](http://arduino.cc/), des [lego mindstoroms](http://www.lego.com/fr-fr/mindstorms/) ou de travailler sur un robot. Je m'étais d'ailleurs acheté un [Raspberry Pi](http://www.raspberrypi.org/) dans ce but, mais faute de temps le Raspberry Pi est resté globalement dans sa boite.
Cet atelier était donc la promesse de manipuler et de pratiquer sur ce genre de materiel.

![Atelier Objets Connectés](/images/posts/2013-12-27-atelier-objets-connectes/AtelierObjetsConnectes.jpg)

Le compte rendu officiel est disponible [ici](http://liris.cnrs.fr/actualites/generales/le-liris-organise-un-atelier-objets-connectes-avec-le-fablab-de-lyon). Un [groupe](https://www.facebook.com/groups/368433689954636/?fref=ts) Facebook a également était créé.

Pour un compte-rendu plus détaillé, cliquez sur "Lire la suite" <!--more-->

## Première soirée
La première soirée a eu lieu à la [Cordée Liberté](http://www.la-cordee.net/la-cordee-liberte-guillotiere/), un espace de coworking qui nous était prété pour l'occasion. Le but de cette soirée était de faire connaissance avec les différentes personnes, les profils étaient en effet assez différents : des développeurs, des designers, des électroniciens et quelques curieux. Nous avons pu commencer à faire connaissance autour d'un petit apéro.

Les organisateurs ont ensuite présenté le but de ces ateliers et le materiel à disposition. Il y avait une dizaine de [kit Lego Mindstorm Eduction](http://education.lego.com/en-gb/lego-education-product-database/mindstorms-ev3/45544-lego-mindstorms-education-ev3-core-set) :

![Kit Lego](/images/posts/2013-12-27-atelier-objets-connectes/kit_lego.jpg)

et de la même manière une dizaine de kits [Arduino Uno](http://arduino.cc) :

![Arduino Uno](/images/posts/2013-12-27-atelier-objets-connectes/ArduinoUno.jpg)

Une imprimante 3D était égelement disponible durant toutes les sessions. Mais je ne crois pas que quelqu'un l'ai utilisée, le problème étant le temps assez limité que nous avions pour réaliser nos idées et le temps que prend l'impression d'une pièce 3D.

Les organisateurs ont demandé à l'assemblée si des personnes avaient des idées, quelques personnes ce sont manifestées et les groupes (une dizaine) ont commencé à se former. De mon coté pas d'idée précise si ce n'est que je voulais faire un robot. Au bout du compte en discutant avec d'autres participant, on a formé une équipe pour faire un robot facteur. L'idée est de développer un système à la Twitter mais de délivrer les messages de manière réelle. Le nom est trouvé RoboPost.

Au niveau solution technique on est parti sur une solution en Lego. L'avantage c'est que tous les catpeurs et actionneurs dont nous avions besoin sont disponibles dans la boite. La seule complexité est de créer une connexion entre une application Web qui va servir pour poster les messages et le robot qui va les distribuer. Pour faciliter la distribution, nous choisissons également de faire simple en décidant que le robot va suivre un itinéraire determiné à l'avance (un circuit materialisé par une ligne). Les utilisateurs seront identifiés par des stations colorées sur le chemin du robot. Le message sera délivré sur l'écran de la brique EV3.

![Kit Lego](/images/posts/2013-12-27-atelier-objets-connectes/CircuitMindstorm.png)

Du coup, notre expérimentation fonctionnera avec 4 personnes au plus. L'idée de départ est de mettre ça en place par exemple autour d'une table. Et pouruoi pas d'ajouter également la distribution d'objets physiques une fois que la premère étape fonctionne.

## Deuxième soirée

Les équipes étant constituées, notre equipe c'est retrouvée sur le campus de Lyon 1 dans le batiment Nautibus. Les soirées étant assez courte (2 heures), nous avons du simplifier au maximum tout le fonctionnel qu'on avait pu imaginer. J'ai développé le site Web, 2 membres de l'équipe ont monté le robot et 2 autres se sont occupés de la communication entre le serveur Web et la brique Lego EV3, le cerveau du robot. C'est d'ailleurs cette dernière partie qui a le plus posée de problème puisque cela a été le point non-implémenté du projet. Notre équipe c'est séparée en ayant pas mal de boulot à faire à la maison pour avoir une version démontrable.

## Troisième soirée

La troisème soirée, toujours sur le campus de Lyon 1, était dédiée à la finalisation des projets et à leur presentation. Notre équipe c'est concentrée sur la finalisation du robot, les points critiques étant d'avoir 2 capteurs optiques: l'un pour suivre le circuit sur et l'autre pour identifier les stations de couleurs. Au bout du compte nous avons réussi à mettre au point à la dernière minute un robot suiveur de ligne, capable de s'arréter aux stations colorées, d'afficher la couleur sur l'écran et d'émettre un son (la couleur). Nous n'étions pas peu fier ;-) La communication entre le serveur Web et la brique EV3 est elle restée théorique, faute de temps.

<iframe width="560" height="315" src="//www.youtube.com/embed/rthzKnlowL4" frameborder="0" allowfullscreen></iframe>

Les équipes ont ensuite été invitées à présenter à l'assemblée les différents projets. Voila une petite liste des différents projets:

- Projet Smart Doudou
	- C'est un doudou connecté ayant la forme d'un nounours permettant d'entendre et communiquer avec son enfant et de déclencher différentes actions comme allumer une veilleuse, déclencher des vibrations. Il y avait une application smartphone permettant d'assurer tout cette communication. Le tout était piloté par un Raspberry Pi car le Mindstorm a une API trop protégée et l'Arduino ne permettait pas assez de chose. La seule partie manquante était la communication audio avec le doudou via le Smartphone. C'était une belle réalisation.

- Projet Robot Musical
	- C'était un robot Mindstorm piloté avec une tablette pour avancer/reculer. Les notes étaient lues via 2 feuilles de papiers colorées placées sur les cotés du robot. La première donnait le rythme, l'autre la hauteur des notes.

<iframe width="560" height="315" src="//www.youtube.com/embed/UOxhIVVLBQ4" frameborder="0" allowfullscreen></iframe>

- Notre projet RoboPost présenté dans cet article

- Projet de Robot Tourneur de Page
	- Un mécanisme en Lego permettant de tourner les pages

- Projet de Compteur connecté
	- Un système en Lego Mindstorm permettant de compter des gens entrant dans une boite de nuit (modélisés pour la démo par des bonhommes Lego). Quand la jauge de la boite de nuit est atteinte, la porte se ferme automatiquement. Elle se rouvre quand les gens partent.

<iframe width="560" height="315" src="//www.youtube.com/embed/2ZhSBHE3o68" frameborder="0" allowfullscreen></iframe>

- Projet de Prise intelligente
	- Une réalisation à base d'Arduino et de Raspberry Pi. L'équipe avait commandé des pièces pour le projet mais n'avait rien reçu à temps (émeteur/récepteur 433 Mhz). La démo était donc faite avec un câble réseau. Le Raspberry Pi jouait le rôle de la centrale de la maison. Il commandait l'allumage d'une LED pilotée par l'Arduino distant. L'équipe souhaitait également brancher sur le même système des capteurs de température, de luminiosité, etc... Leur "Reste à faire" était assez important (ajouter des relais, émeteurs/récepteurs radio, Interface Web) mais la base était fonctionnelle.

- Projet ANA
	- Un système permettant de mesurer son exposition aux émissions électromagnétiques et restituer de manière visuelle ce qui est reçu par l'utilisateur. Le système était basé sur un Arduino.

- Projet Musicolor
	- Un circuit Arduino déclenchant des notes en fonction des couleurs détectées (rouge, vert, bleu). Comme la plupart des projets (Lego ou Arduino) basés sur la reconnaissance de couleurs, celle-ci est loin d'être parfaite.

<iframe width="560" height="315" src="//www.youtube.com/embed/cMz3axH-ld0" frameborder="0" allowfullscreen></iframe>

## La suite

C'était une série d'ateliers assez intéréssante bien que le temps ait globalement manqué. Nous aurions pu avoirdes projets plus aboutis ou de profiter des imprimantes 3D avec un peu plus de temps.

Le LIRIS prévoie de relancer une série d'aterlier en 2014 sur un format plus long, j'espère avoir le temps d'y participer.

Les sources de notre proto sont disponibles sur ce [repo](https://github.com/titimoby/RoboPoste).

