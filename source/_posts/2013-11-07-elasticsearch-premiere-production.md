---
layout: post
title: "Elastic Search en production"
date: 2013-11-05
comments: true
categories: elastic search
---

Ça y'est, j'ai mis mon premier cluster Elastic Search en production : plus d'un milliards de documents  et chaque jour environ 500000 nouveaux documents ajoutés. Ça tourne sur 3 nœuds identiques, chacune contient une instance Elastic Search et les applications l'alimentant et l'exploitant.

Avoir plusieurs instances d'un même système facilite la montée en charge car le système supporte les "scale out" pour absorber plus de données ou plus de charge.

Le paramétrage d'un Elastic Search en production doit être optimisé sous peine d'avoir de gros problèmes. Je recommande fortement le visionnage de ce [webinar](http://www.elasticsearch.org/webinars/elasticsearch-pre-flight-checklist/) qui permet d'avoir une idée de tout ce qu'il faut faire (et de comprendre) pour configurer un Elastic Search en production.

Ce qu'il faut retenir:

- Bien dimensionner la mémoire, 1 Go (le réglage par défaut) n'est pas suffisant
- Ne pas allouer plus de 32 Go de Heap Size, le format des pointeurs de la JVM change après cette valeur. Il vaut mieux ajouter un serveur si on est limite au niveau mémoire
- Penser à prévoir le double de mémoire physique par rapport au Heap alloué (e.g. si on met 32 Go de Heap, le serveur va en consommer 64 Go physique). Cela est dû à Elastic Search qui utilise massivement le cache du système de fichier.
- Prévoir des disques rapides, les "disk arrays" ne sont pas vraiment utiles.
- Préparer le système pour que l'utilisateur qui lance le process Elastic Search puisse ouvrir le maximum de fichier.
    - Editer */etc/security/limits.conf* pour ajouter 
``` 
	# Ensure ElasticSearch can open files and lock memory!
	elasticsearch   soft    nofile          65536
	elasticsearch   hard    nofile          65536
	elasticsearch   -       memlock         unlimited
``` 


- 
    - Ensuite dans le .bash_profile de l'utilisateur ajouter la ligne 


```
ulimit –n 65536
```


- Toujours installer la dernière version de Java et d'Elastic Search, de gros porblèmes sont survenus avec des versions pas à jour de Java.
- Attention avec le service Wrapper fourni [ici](https://github.com/elasticsearch/elasticsearch-servicewrapper). Son usage est réservé aux personnes payant la licence pour le composant [Service Wrapper de Tanuki Software](http://wrapper.tanukisoftware.com/doc/english/download.jsp).
- Dans conf/elasticsearch.yml
    - Décommentez *cluster.name* et fixez la même valeur sur l'ensemble des serveurs,
    - Décommentez *node.name* et fixez une valeur différente pour chaque nœud,
    - Mettre à jour les chemins *path.data* et *path.logs* pour les faire pointer en dehors du repertoire d'installation d'Elastic Search. Cela permet de bien gérer les montées de versions et de créer des partitions adaptées.
    - Décommentez *discovery.zen.ping.multicast.enabled* et mettre à *false*
    - Décommentez *discovery.zen.ping.unicast.hosts* et mettre la liste des membres du cluster
    - Fixez *gateway.recover_after_nodes* au nombre de noeuds du cluster
    - Fixez *discovery.zen.minimum_master_nodes* à (nombre de nodes/2)+1
- Mettez en variable d'environnement de l'utilisateur la variable *ES_HEAP_SIZE* et fixez la à la taille coulue (e.g. export ES_HEAP_SIZE=16g)

Avec tout ça vous devriez avoir le début d'un cluster qui fonctionne bien.






