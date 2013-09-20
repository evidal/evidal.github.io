---
layout: post
title: "Gestion des performances"
date: 2012-01-12
comments: true
categories: [java, perf]
---

Je travaille depuis maintenant 2 ans sur un très gros projet qui gère des dizaines de millions d'utilisateurs. Notre logiciel est donc très sensible à la charge et à toutes améliorations de performance. Voilà une petite liste d'actions qui permettent de traquer ou de prévenir les problèmes de performances en général.

{{http://media-cdn.tripadvisor.com/media/photo-s/01/72/97/46/gas-works-park.jpg|source Trip Advisor}}
 
## Prévention
### Simplification
De manière général plus un traitement est simple, plus il est performant. Il faut donc rechercher dans le design la façon de faire la plus simple.
En bonne pratique il faut:

* ne pas multiplier les couches d'abstraction. Plus on multiplie les intermédiaires, moins on est réactif.
* ne pas multiplier l'utilisation de librairies techniques (construire de gros échafaudages de librairie). On prend le risque de rendre une fonctionnalité simple beaucoup plus compliquée.
* utiliser des environnements maitrisés. Utiliser les dernières technologies c'est super, mais si on ne maitrise pas tous les impacts, on peut construire de vraies bombes à retardement.  
 
### Faire des tests de performance
Sur notre projet nous avons utilisé JMeter pour simuler du trafic. Attention on ne doit pas seulement faire un hit sur de 2-3 pages mais réaliser un scénario complet et dynamisé.
Par la suite les tests doivent se dérouler sur plusieurs heures ou jours pour voir si la plateforme est stable dans le temps.
Pendant ces tests il ne faut pas forcément essayer de trouver les limites de l'application mais la soumettre à un stress nominal.
Les tests aux limites sont intéressants mais uniquement si la plate-forme répond correctement au stress nominal.
 
### Dimensionner l'infrastructure, limiter la charge  
A partir des tests établis précédemment on peut en déduire des infrastructures cibles.
On ne va pas mettre la même infrastructure chez un client ayant 2 millions de comptes que chez un client ayant 80 millions de comptes.
Il est important de définir ces infrastructures dans les contrats quand on vend le logiciel.
De plus il est important pour les applications critiques de mettre en place des mécanismes de protection pour éviter la saturation de la plate-forme.
On peut par exemple mettre en place un système de licence qui limite le nombre d'utilisateurs à 10 millions et/ou à un certain nombre de requêtes HTTP par seconde.
Avec cela on garantit le service pour la charge que l'on a défini contractuellement. Il faudra bien sur penser à ce système dès la conception du logiciel.
 
## Corrections
Au niveau développement voilà un certain nombre d'éléments permettant de traquer les problèmes.
 
### Optimisation SQL
De manière générale le tout premier élément limitant d'un logiciel sur un serveur est la base de données mal utilisée. Il peut y avoir plusieurs causes:

* mauvais modèle de données
* pas d'index
* trop de requêtes SQL
* requêtes SQL non optimisées
* non maitrise du Framework technique accédant à la base
 
Dans un premier temps, il va falloir tracer les requêtes SQL générées par l'application. Si vous utilisez Hibernate, vous pouvez par exemple mettre à true le paramètre hibernate.show_sql.
Par la suite vous allez exécuter un scenario de test unitaire sur votre application. Cela va vous permettre d'identifier les requêtes SQL exécutées à chaque étape de votre scénario.
Des actions doivent être prises si:

- une même requête est exécutée plusieurs fois pour une étape donnée
- une ou plusieurs requêtes inattendues apparaissent (par exemple un delete et des insert alors qu'on fait un simple select, si si c'est possible voir [[www.yonita.com/2011_11_16_PERFORMANCE_ANTIPATTERNS_DEVOXX.pdf|ici]])
Ils faut impérativement rester maitre des requêtes faites vers la base de données.

Dans un deuxième temps il fait traquer les requêtes consommatrices de ressources. Sur Oracle, on peut utiliser l'outil Oracle Enterprise Manager qui est une console d'administration Web de la base de données.
Cet outil permet de monitorer en temps réel la charge de la base de donnée mais aussi d'avoir la liste des requêtes les plus consommatrices. Un fois ces requêtes identifiées on pourra les optimiser en:

* ajoutant des index
* ajoutant des hints pour l'optimiseur SQL (les hints sont tout à fait compatible avec des outils comme Hibernate)
* en repensant une partie du modèle de base de données pour l'optimiser 

Enfin une des dernières optimisations au niveau base de données est de bien préparer la base.
Si le modèle a un impact très fort sur les performances, il ne faut pas négliger la configuration de la base.
Il faut notamment travailler avec un DBA pour optimiser (pour Oracle) la taille des blocs, des redo-logs, des archives-logs.
Attention toutefois à ne pas faire n'importe quoi, si vous ne maitrisez  pas ces paramètres, n'y touchez pas.
 
### Optimisation des Objets/Mémoire/Threads
Ce paragraphe s'intéresse à l'étude et l'optimisation du code java.

L'utilitaire jmap permet d'avoir plus d'information sur l'utilisation de la mémoire et des objets. Cet outil est utile pour monitorer la JVM quand elle est stressée de manière normale.

* jmap -d64 -heap <pid> > heap.txt
	* cette commande permet d'avoir un état détaillé de la mémoire. Cela vous permettra d'ajuster les paramètres mémoires de la JVM.
* jmap -d64 -histo:live <pid> > histo.txt
	* cette commande permet d'avoir la liste de toutes les instances tournant dans la JVM. On pourra détecter un trop grand nombre d'instance pour un type d'objet.   

Les applications JEE sont multi-threadés, les threads sont cachés par les APIs de haut niveau de la norme JEE (Servlet, EJB).
Si c'est API sont mal utilisées, des problèmes de performance apparaissent (notamment des cas d'inter-blocage).
Pour s'assurer que notre application n'est pas dans ce cas, il faut la soumettre à sa charge nominale grâce à des injecteurs ou des outils comme JMeter.
Une fois que l'application est en cours de fonctionnement, on va faire des Threads Dump de manière régulière pour vérifier qu'aucun thread n'est bloqué.
La résolution des cas d'inter-blocage en changeant l'implémentation d'une méthode a souvent des résultats spectaculaires.
A noter que les threads dump en Java se font en envoyant SIGQUIT au process à l'aide de la commande kill -3 <process_id>.
Sous JBoss on peut faire ça via la console JMX sur le bean ServerInfo.

Une des dernières techniques pour voir plus claire dans le fonctionnement d'une application. Personnellement j'ai rarement progressé en profilant une application.
De nombreux outils existent pour profiler une application, cependant un profiler est livré avec le JDK: HPROF. Il peut fonctionner en mode classique (on enregistre tous les évènements) ou en mode sampling (on prend un photo de la JVM toutes les minutes par exemple).
Si on travaille sur environnement stressé, il est préférable d'utiliser le mode Sampling.
 
Voilà, c'était un petit aperçu des différentes techniques que j'utilise pour améliorer les performances de mes applications. Il en existe certainement bien d'autres mais celles-ci nous ont bien aidé pour avoir une application performante en production.
