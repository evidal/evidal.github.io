---
layout: post
title: "Ma&icirc;triser sa production"
date: 2012-02-21
comments: true
categories: [java, perf]
---

Un blog un peu court pour expliquer à quel point il est important que les équipes de production soient formées aux environnements d'exécution qu’elles utilisent. La plupart des administrateurs avec qui j’ai travaillé sont vraiment très bon dans le domaine système, il est beaucoup plus rare qu’ils connaissent aussi nos environnements d'exécution comme JBoss par exemple. 

Bien sur il arrive que les équipes de développement assurent des formations sur l’application que les administrateurs vont déployer. Mais si tous les aspects purement “administratif” sont au mieux passés rapidement, ils sont souvent ignorés.

Et quand on donne des outils aussi puissant qu’un serveur d’application à des administrateurs mal formés, on peut aboutir à des catastrophes. J’ai un exemple récent dans une société dont je tairai le nom d’un serveur JBoss ayant une adresse IP public. Ce serveur avait été installé pour une démo brut de décoffrage, sans paramétrage. Ce qui fait que la console JMX était accessible sans mot de passe, ainsi que la console d’admin qui avait les mots de passe par défaut. Ce serveur c’est fait attaqué. L’attaque a été construite comme cela:

* scan des ports de la machine
* repérage et identification d’un serveur JBoss
* repérage que la faille décrite [ici](http://eromang.zataz.com/2011/10/25/jboss-worm-analysis-in-details/) fonctionnait bien

Et voila un serveur ultra puissant à la merci de pirates.

Ce qu’il faut retenir de cette histoire, c’est qu’un serveur d'application n’est pas un simple outil qu’on dé-zippe quelque part et qu’on laisser tel quel. De réels efforts doivent être fait pour former les équipes de productions à l’utilisation et à la configuration de tels outils. Une bonne solution est de les faire participer aux tests de performances de l’application. On complétera cette prise en main par une formation adaptée aux problématiques de production (Déploiement, Monitoring, Sécurité, Backup, Restore, Logs, Tunning, etc...)
