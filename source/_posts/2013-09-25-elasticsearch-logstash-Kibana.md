---
layout: post
title: "Combo Logstash, Elastic Search & Kibana, l'arme absolue des logs"
date: 2013-09-25
comments: true
categories: elastic search
---

L'exploitation des logs sur les applications complexes est assez compliquée. Les applications que mon entreprise développe sont morcelées en plusieurs
couches: Front-End, Back-Enf, Back-Office, Front-Office, Connecteurs, etc... Chaque application génère ses lots de logs, qu'ils soient purement techniques
ou bien à but "business", dans mon cas des CDR ayant une certaine valeur pour la facturation de nos clients.

Comment avec tous ces logs donner une vision cohérente et syntétique de ce qu'il se passe sur la plateforme ? Comment archiver ces logs efficacement
pour les 5 prochaines années tout en générant des centaines de milliers de logs ?

La réponse est en utilisant le combo [Elastic Search](http://www.elasticsearch.org/), [Logstash](http://logstash.net/) et [kibana 3](http://www.elasticsearch.org/overview/kibana/). [Logstash](http://logstash.net/) est une sorte de super ETL permettant de spécifier des entrées, des transformations
et des sorties. La configuration est assez simple et en deux temps 3 mouvements on est capable de configurer un ETL capable de lire tous les fichiers 
d'un repertoire, de les parser intelligemment et de les injecter dans un index [Elastic Search](http://www.elasticsearch.org/). Globalement pour
une ligne de log, on va se retrouver avec un document Elastic Search. La ligne de texte originale est conservée, et plusieurs champs sont créés,
correspondant aux directives que vous configurez dans le fichier de conf Logstash.

Dans mon cas j'ai utilisé une entrée de type file permettant d'explorer un repertoire et tout ses sous-repertoires. J'ai chainé cette entrée avec un
filtre grok qui permet de parser la ligne de log. J'ai d'ailleurs utilisé [Grok Debugger](http://grokdebug.herokuapp.com/) un outil permettant de tester
des directives sur des logs réels. Si les patterns préprogrammés de GRok sont insuffisant, on peut facielement définir des expressions régulières
permettant d'étendre les possibilités de Grok. Enfin les logs sont indéxés par Elastic Search, on peut choisir d'utiliser l'Elastic Search embarqué
dans Logstash ou de pointer vers un Elastic Search distant (la solution que j'ai choisi).

On va configurer Elastic Search pour créer un index par mois (ou par jour pour les très gros producteurs de logs). Cela permet de supprimer les documents par lot le moment venu sans "casser" les index Elastic Search existant. [Elastic Search](http://www.elasticsearch.org/) de par son design permet un accès ultra-rapide à n'importe quelle ligne de log avec la puissance de son module de requêtage et de filtrage.

Enfin [kibana 3](http://www.elasticsearch.org/overview/kibana/) (récemment intégré à Elastic Search) permet d'avoir une visualisation des données de log
et de composer des "Dashboards" super complet. Par exemple, j'ai réalisé ce dashboard très facilement à partir des logs de nos applications.

![Dashboard Kibana MMG](/images/posts/2013-09-25-elasticsearch-logstash-Kibana/kibana.png)

Et voilà ! On quelque chose de complet et complétement présentable très rapidement. Le temps de découvrir Kibana et de comprendre comment cela
fonctionnait, j'ai mis 1 heure. Et tout est connecté, c'est à dire qu'on peut modifier les dates en temps réel (zoomer, dé-zoomer, passer en mode temps-réel, etc...) et tous les graphes se mettent à jour instantanément. C'est très bluffant.

Bref je recommande vivement l'utilisation de ce combo qu'on peut considérer comme l'arme absolue pour la gestion des logs d'applications.
