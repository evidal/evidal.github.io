---
layout: post
title: "Logstash, Kibana, ElasticSearch : le How to"
date: 2014-03-14
comments: true
categories: elasticsearch
---
Alors que son contenu est plutot simple, mon article de l'an dernier "[Combo Logstash, Elastic Search & Kibana, l'arme absolue des logs](blog/2013/09/25/elasticsearch-logstash-Kibana/)" a eu un grand succès. Je vais dans cet article essayer d'avoir une approche plus pratique
pour celui ou celle qui souhaite essayer ce fameux combo.

## Installer Elastic Search

Même si Elasticsearch est fourni dans Logstash, je préfère utiliser un Elasticsearh externe pour mutualiser ce service.
Pour rappel, Elasticsearch est un moteur de recherche distribué basé sur Lucene très facile d'accès et puissant.
Pour installer un Elasticsearch, rien de plus simple.

```
$ mkdir /opt/elasticsearch
$ cd /opt/elasticsearch
$ wget https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.0.1.zip
$ unzip elasticsearch-1.0.1.zip
$ cd /opt/elasticsearch/elasticsearch-1.0.1/bin/
$ ./plugin -install mobz/elasticsearch-head
$ ./elasticsearch &
```

Attention cette installation est suffisante pour tester la solution, mais ne doit pas être considérer comme une installation type production.
J'ajoute systématique le plugin [head](http://mobz.github.io/elasticsearch-head/) qui est bien utile pour administrer son cluster.
Si tout va bien vous devez avoir le comportement suivant si vous appellez l'URL de base d'Elastisearch:

```
$ curl localhost:9200
.{
  "status" : 200,
  "name" : "Omega Red",
  "version" : {
    "number" : "1.0.1",
    "build_hash" : "5c03844e1978e5cc924dab2a423dc63ce881c42b",
    "build_timestamp" : "2014-02-25T15:52:53Z",
    "build_snapshot" : false,
    "lucene_version" : "4.6"
  },
  "tagline" : "You Know, for Search"
}
```

Et si vous allez sur (http://localhost/_plugin/head/)[http://localhost/_plugin/head/], vous devez avoir l'interface HEAD d'Elasticsearch.
La partie Elasticsearch est terminée, continuons par la partie Logstash.

## Installer Log Stach

Logstash est donc un "super ETL" configurable permettant de prendre des données en entrée, de les transformer et de les pousser ailleurs.
Dans notre cas, on va prendre des fichiers de logs en entrée, parser ces lignes et les insérer comme des documents Elasticsearch.
Attention d'une version à l'autre les configurations Logstash peuvent changer. Les indications ques je donne aujourd'hui sont valable pour la version 1.3.3 de Logstash.

```
$ mkdir /opt/logstash
$ mkdir /opt/logstash/logstash-1.3.3
$ cd /opt/logstash/logstash-1.3.3
$ wget https://download.elasticsearch.org/logstash/logstash/logstash-1.3.3-flatjar.jar
```

Ensuite il va vous falloir des logs à traiter, par exemple ceux de votre application. Il faut créer un fichier de configuration qui va lire, parser et
écrire ces logs dans Elasticsearch. Vous pouvez créer un fichier de configuration:

```
$ cd /opt/logstash/logstash-1.3.3
$ touch logstash.conf
```

Un fichier de configuration Logstash comporte 3 parties:

- une partie "input" qui décrit les points d'entrées
- une partie "output" qui décrit ou on écrit la sortie
- une partie "filter" décrivant comment les données sont transformées/interprétées. A noter que les transformations sont appliquées dans l'ordre ou elles sont déclarées


```
input {
  file {
    path => ["/root/SICAP/TuTn/var/opt/mmstat/current/log/history/**/*"]
    type => "messagingservertype"
    start_position => "beginning"
    format => "plain"
  }
}

output {

  elasticsearch_http {
      host => "localhost"
      port => 9200
      type => "messagingservertype"
      flush_size => 10
  }
}

filter {
	grok {
	  type => "messagingservertype"	  
	  pattern => "%{MSGMOSHIPTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:MsgId};%{NUMBER:Type};%{DATA:Account};%{NUMBER:ApplicationId};%{NUMBER:ShippingStatus};%{NUMBER:Done};%{WORD:Persisted}"
	  pattern => "%{MSGRECEIVEDTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:MsgId};%{NUMBER:Type};%{NUMBER:Subtype};%{DATA:Account};%{NUMBER:ApplicationId};%{DATA:ShortCode};%{WORD:Mcc};%{WORD:Mnc};%{DATA:keyword};%{NUMBER:Charging Type}"
	  pattern => "%{MSGSENTTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:MsgId};%{NUMBER:Type};%{NUMBER:Subtype};%{DATA:Account};%{NUMBER:ShippingStatus};%{DATESTAMP:TimeToSend};%{DATESTAMP:TimeShipped};%{DATESTAMP:TimeCreated};%{NUMBER:ApplicationId};%{DATA:ShortCode};%{WORD:Mcc};%{WORD:Mnc};%{DATA:ConnectorName};%{NUMBER:ChargingType}"
	  pattern => "%{MSGSHIPTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:MsgId};%{NUMBER:Subtype};%{DATA:Account};%{NUMBER:ApplicationId};%{NUMBER:ShippingStatus};%{NUMBER:Done};%{WORD:Persisted}"
	  pattern => "%{MSGTRACKTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:MsgId};%{NUMBER:Type};%{NUMBER:TrackingStatus};%{DATESTAMP:TimeToSend};%{DATESTAMP:TimeShipped};%{DATESTAMP:TimeLastTracked};%{NUMBER:Subtype};%{DATA:Account};%{NUMBER:ApplicationId};%{WORD:Persisted}"	  
	  pattern => "%{CHARGINGREQUESTTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{DATA:ChargingOperation};%{DATA:ChargingParameters};%{DATA:PluginName};%{NUMBER:TransactionId}"	  
	  pattern => "%{CHARGINGRESPONSETRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{DATA:ChargingOperation};%{DATA:PluginStatus};%{NUMBER:TransactionId}"	  
	  pattern => "%{MSGTRACKAGGREGATIONTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:ApplicationId};%{DATA:Account};%{NUMBER:TrackingStatus};%{NUMBER:NbSlices};%{DATESTAMP:TimeToSend};%{DATESTAMP:TimeLastTracked}"
	  pattern => "%{MSGSENTAGGREGATIONTRACE:trace}.0;%{DATESTAMP:Timestamp};%{DATA:Owner};%{DATA:Author};%{NUMBER:Bearer};%{NUMBER:Device};%{NUMBER:ApplicationId};%{DATA:Account}"
	}

	date {
	  type => "messagingservertype"
	  match => [ "Timestamp", "yyyy-MM-dd HH:mm:ss.SSS" ]
	}
}
```

## Installer Kibana et créer son premier Dashboard
## Production