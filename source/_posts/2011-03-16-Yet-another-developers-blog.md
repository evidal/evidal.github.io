---
layout: post
title: "Yet another developer's blog"
date: 2011-03-16
comments: true
categories: blog
---


Tout est dans le titre... encore un blog de développeur.

Ce blog est d'abord le résultat (non fini) d'une petite séance d’essai du framework Gaelyk. Ben oui quoi, à quoi ça sert d'être développeur si on développe (mal) pas son propre blog.
Mine de rien ce premier essai m'a permis de jeter un coup d’œil sur le fonctionnement et "l’écosystème" Google App Engine. J'ai donc essayé pour ce petit essai:

* [Groovy](http://groovy.codehaus.org/), un célèbre langage compatible avec le JRE 
* [Gaelyk](http://gaelyk.appspot.com/), un petit framework WEB Groovy simple et efficace (à part l'outillage)
* [Obgaektify]([http://obgaektify.appspot.com), un binding en Groovy de la librairie Objectify qui est une sorte de JPA light

J'en ai profité aussi pour mieux connaître [JQuery](http://jquery.com/) et mettre les mains dans HTML5. A faire tout le temps du JSF et Richfaces, on oublie parfois ce qu'il y a dessous.
Le résultat est loin dêtre parfait, mais utilisable. Il comprends cette partie visible, mais aussi un back office avec éditeur WIKI et tout et tout.
Par contre il me reste notamment à ajouter la gestion des commentaires, des tags et des liens. Vu que je ne vais pas rendre public ce site tout de suite, ça me laisse un peu de temps pour ajouter tout ça.

Quelques petits conseils et retours immédiats à propos de Google App Engine et des libs utilisées:

- Vous utilisez un JVM "sandboxé" et avec une "white-list" du SDK java. Du coup n'hésitez pas à mettre en prod le plus rapidement possible sur GAE, sous peine de surprise. J'ai par exemple perdu du temps car mon Logger déclaré en static dans une classe n'est pas du goût de monsieur.
- L'API du datastore est assez simple et utilisable telle quelle, je l'ai agrémentée de Obgaektify "pour la science" et surtout pour écrire un code plus concis.
- J'aime beaucoup le système de routage de Gaelyk, ça permet d'avoir de "belles" URL simplement.
- Quand tout sera à peu près en place, je ferais (du moins j’essayerai de faire un article plus long).
