---
layout: post
title: "Sophisme et Spring"
date: 2012-07-24
comments: true
categories: [java, jee]
---


Une petite note en passant suite aux commentaires d'un fournisseur (qui m'énerve).

Ce fournisseur donc aime bien travailler avec Spring, ils sont à l'aise avec et travaille plutôt bien dessus.
Ces gens là donc sont en train de récupérer un logiciel pour en assurer sa maintenance et sa roadmap.
Une de leur mission et d'améliorer les tests unitaires qui sont pour l'instant faible, et la c'est le drame (avec le ton d'Enquêtes exclusives ).

Voila leur argumentaire:

* Spring c'est super testable
* Votre application utilise des EJB (NB: JEE 1.5, EJB 3.1)
* Votre application n'est pas testable, il faut tout injecter à la main, c'est trop compliqué.
* Vous auriez dû utiliser Spring


AAaaaaah mais que ça me fatigue/énerve.
 
Disclaimer : Je considère que le système d'injection de Spring ne sert à rien dans un serveur d'application depuis JEE 1.5.
Après pour les autres éléments "Springuiens", c'est une autre histoire. 
D'autant plus que l'utilisation de JEE ou Spring n'est pas exclusive !
 
Quand j'entends des arguments du genre Spring c'est mieux parce que c'est plus simple à tester, ça me fait juste bondir. C'est un argument moisi.
Il serait beaucoup plus honnête et acceptable de dire "je suis plus à l'aise avec Spring qu'avec JEE 1.5, alors s'il te plait laisse moi utiliser Spring".
Ça je pourrais l'entendre. Le reste, c'est juste de l'incompétence.
 
Pour mémoire -> http://www.adam-bien.com/roller/abien/entry/unit_testing_ejb_3_1

ou alors

http://www.jboss.org/arquillian.html
 