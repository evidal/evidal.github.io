---
layout: post
title: "Cloud Synth&egrave;se"
date: 2011-07-07
comments: true
categories: cloud
---

Le 9 et 10 juin j'ai eu la chance de participer à synthèse sur les technologies du Cloud.

![source http://commons.wikimedia.org/](http://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Cirrocumulus_to_Altocumulus.JPG/800px-Cirrocumulus_to_Altocumulus.JPG)

Voilà mes notes.
<!--more-->
 
## Concepts

Le Cloud est un terme à la mode. Il faut se méfier des imitations. Le Cloud ne veut pas dire
Hosting ou logiciel en ligne. L'hébergement ou les logiciels accessiblesne sont pas nouveau et
existe depuis longtemps. Le Coud est plus l'arrivé à maturité de certaines technologies et d'acteurs IT:

* La virtualisation est massivement utilisée dans le Cloud
* Les applications sont dévenues des application WEB
* Les Architecures SOA sont utilisées
* Des sociétés IT suffisemment grosse pour inspirer la confiance existe
* Les start-up des années 2000 ont eu besoin des technologies Cloud pour exister et grossir

Pour résumer les points clés d'un/e déploiement/applciation Cloud sont:

* controlable par une API pour couplage au SI
* Multi-Tenancy, ou capacité à cohabiter utilisateur du Cloud sans interférence.
* Reliability, c'est à dire pour un coup identique ou moindre, augmenter sa résistence aux aléas d'un système (composants qui crashent, datacenter qui brulent, etc...).
* Scalability (in and out), être capable de muscler sa solution si besoin ou au contraire diminiuer sa puissance si trop grosse (et payer ce qu'on utilise).
* Performance, la puissance est potentiellement infinie du point de vue de notre utlisation.

3 types d'usages existent:

* IaaS – Infrastructure as a Service, pour disposer d'une machine virtuelle dans le Cloud 
* PaaS – Platform as a Service, Middleware utilisant une infrastructure de type Cloud pour fonctionner et utilisable via une API
* SaaS – Software as a Service, un logiciel utilisant une infrastructure de type Cloud pour fonctionner (et intégrable dans son SI via des APIs)
 
### IaaS
Les offres IaaS (Infrastructure as a Service) proposent de se construire infrastructure dans le Cloud (serveur, réseau). On peut utiliser cette infrastructure comme une extension de son propre système ou comme une infrastructure prête à l'emploi: on utilise exactement ce dont on a besoin, avec l'OS qu'on veut. Si plus (ou moins) de puissance est nécéssaire, on définit des règles pour adapter la puissance dont on a besoin, et donc son coût.
 
### PaaS
Le PaaS (Platfom as a Service) vous proposent toute une gamme de middleware prêts à être utilisé. Toutes la partie matériel, système d'exploitation est complètement masqué, n'est visible que le middleware. On trouvera dans cette gamme de service des solutions très variées: Base de données (SQL ou non), Serveur d'Application (Java ou non), des Messages Broker, des solutions de paiements, etc. Comme dans le cas de l'IaaS ces plateformes sont virtuellement illimitées en terme de puissance et on paye uniquement ce que l'on consomme.
 
### SaaS
Le Software as a Service existe depuis longtemps mais ne doit pas confondu avec des solutions "hostées". Les  principales différences avec un service hosté sont les suivantes :

* le logiciel disponible en mode SaaS est virtuellement illimité en terme de nombre d'utilisateurs.
* des APIs doivent permettre une interconnexion avec d'autres systèmes (comme le SI)
* l'utilisateur paye pour ce qu'il utilise
Dans cette gamme de service, la variété de solutions est pléthorique : Suites Bureautiques, Messageries, Wiki d'entreprise, logiciel de gestion ultra scpécialisé (gestion commerciale), etc.
 
### Sécurité et Cloud
On voit donc que migrer tout ou partie de ces applications vers le Cloud est très attractif, mais comme pour toute décision, on doit soigneusement réfléchir aux conséquences, notamment en terme de sécurité. Car migrer vers le Cloud ça veut dire donner l'accès à un tiers à des données qui nous appartiennent, ce qui peut dans certains cas présenter des problèmes légaux ou de confidentialités.

Du coté des points positifs, on retiendra les éléments suivants:

* Les Administrateurs de ce genre de solutions sont très bons, souvent meilleurs que les personnes dont nous disposons en interne.
* Les plateformes (et leurs administrateurs) sont disponibles en 365/7/24 : 365 jours par an, 7 jours sur 7, 24 heures sur 24. Peu d'entreprises peuvent se permettre une telle disponibilités.
* Les infrastructures des ces solutions sont bien mieux protégées des attaques car tous les patchs de sécurité sont appliqués très rapidement et de manière transparente pour les utilisateurs.
* Toute l'infrastructure est redondante donc les pannes matériels sont correctement gérées.
* PaaS, IaaS ou SaaS tournent dans des VM, ce qui isole des autres clients et de leur défaillance potentielles (ex. mémoire saturée).
* Les données sont distribuées ce qui assure plus de sécurité quand à leur conservation.
 
Mais tous n'est pas rose, et pour tempérer tous ces points positifs, voila une liste d'éléments auxquels ont doit réfléchi avant de basculer:

* Toutes ces solution sont opaques: on ne sait pas comment ça fonctionne, on ne sait pas qui gère notre plateforme, on ne sait pas où se trouve le datacenter, etc.
* On doit avoir complètement confiance dans notre fournisseur.
* Les possesseurs de la plateforme peuvent accéder à mes données, comment s'assurer de la confidentialités des données sensibles
* Une fois qu'on utilise plus la plateforme, que deviennent mes données ? Sont-elles détruites ou conservées par mon fournisseur ?
* La plupart des administration publique ne peuvent pas utiliser de tels services pour des raisons légales (localisation géographique des serveurs, agrément pour la gestion des données médicales, etc.)
* Rien n’empêche le fournisseur de faire de faire du Datamining sur mes données (ex. Google Mail et les publicités)
 
### Cas d'utilisation
Venons-en au cas d'utilisation, c'est à dire comment utiliser cette technologie dans mon entreprise.

* Externaliser son IT. Dans ce cas la, je confie à un tiers la gestion des comptes emails, des calendriers partagés, de l'Intranet, etc... (Google Apps)
* Externaliser sa solution de CRM (Sales Force)
* Optimiser ses ressources IT : migrer tous les serveurs dans le Cloud et utiliser la puissance machine uniquement quand c'est nécessaire.
* Application WEB a très forte audience pour être capable de gérer les très grosses montées en charge et être capable de relacher cette puissance par la suite
* "Business Continuity" conserver une partie de ses machines en interne et prévoir un système complet de Fail-Over dans le Cloud.

Un des points fort des applications Cloud sont les SLA (Service Level Agreement ou Qualité de Service), mais tout n'est pas parfait. En effet les plateforme Cloud ont des  SLA très importante (99.999999999 pour Amazon S3) mais il arrive qu'il y ait des pannes. Tout le monde ce souvient de la grosse panne Amazon en avril 2011 qui a paralysé certains gros site pendant quelques jours. Il faut donc se tenir prêt à ce que le Cloud soit défaillant, voilà quelques idées pour réduire ce risque. Ces idées ne sont pas nouvelles et doivent déjà être mise en oeuvre sur les très gros projets à hébergement classique:

* Varier les solutions d'hébergement
    * serveurs internes/externes, 2 fournisseurs Cloud différents, 
    * différents fournisseurs Cloud (Amazon, Google Microsoft, ...)
    * différentes localisations de datacenter (Amazon Europe, Amazon US East#
* Monitorer les applications pour détecter/prévenir les problèmes et réduire les temps d'indisponibilités.
* Etre capable d'utiliser les API de son fournisseur Cloud pour réagir rapidement
* Avoir une plate-forme suffisamment générique pour changer de fournisseur en cas de besoin.
 
## Les offres Cloud
Il y a 4 "gros" fournisseurs (Cloud Service Providers) dans le domaines du Cloud:

* [Amazon](http://aws.amazon.com)
* [Google Apps](http://www.google.com/Apps) et [Google App Engine](https://appengine.google.com/)
* [Force](http://www.force.com/) et [Salesforce](www.salesforce.com)
* [Microsoft](http://msdn.microsoft.com/fr-fr/windowsazure) et [Office 365](http://www.microsoft.com/fr-fr/office365/online-software.aspx)
 
### Amazon
[Amazon](http://aws.amazon.com) est l'un des tous premiers fournisseurs Cloud. La légende (rumeur) veut qu'ils aient acheté trop de machine à leur début et qui'ils aient cherché un moyen de les utiliser grâce à de nouveaux services. Amazon est positionné sur les solutions de type IaaS et PaaS avec notamment 

* EC2, Elastic Compute Cloud
    * Location de serveur (avec choix dymanique de la puissance)
    * Location de Serveur avec HADOOP (pour faire du [map reduce](http://fr.wikipedia.org/wiki/MapReduce))
* S3, Simple Storage Service,
    * On peut considérer S3 comme le disque dur d'Internet. De nombreux services à très forte volumétrie utilisent S3 (comme dropbox)/
    * SLA : 99,999999999%
    * La SLA est tellement énorme, qu'une nouvelle solution moins chère avec des SLA moins élevés est à l'étude. 
* et aussi
    * EBS, Elastic Block Store (Disques pour EC2)
    * SQS, Simple Queue Service (Système de queue)
    * SNS, Simple Notification System (Messaging en mode push)
    * Simple DB (Stockage clé/valeur)
    * RDS, Relational Database Service (MySQL)
    * Elastic Load Balancing, Elastic IP, Autoscale
    * VPC, Virtual Private Cloud
    * ...

Pour les développeurs JAVA, une solution est intéressante : Elastic Beanstalk. C'est la combinaison de tout un tas de service Amazon pour fournir un serveur d'application JEE prêt à l'emploi. Point intéressant, si vous utilisez Beanstalk, la première année est gratuite à condition de ne pas dépasser des quotas.

### Google
Google est positionné sur les SaaS avec [Google Apps](http://www.google.com/Apps) et le PaaS avec [Google App Engine](https://appengine.google.com/)

* pour [Google Apps](http://www.google.com/Apps) on aura à notre disposition
    * Google Docs (Suite Bureautique)
    * Google Sites (Pour réaliser des sites WEB)
    * Gmail & Calendar (Email et Calendrier)
    * Google Groups (Groupe de discussion)
    * …
* et pour [Google App Engine](https://appengine.google.com/) on va trouver les services suivants
    * héberger des applications JEE/Python/Go
    * A noter pour Java seule une partie de l'API est implémentée. Il faut utiliser les classes de la Google White List
    * Les quotas gratuits sont assez élevés (moins de 5 millions de pages vues par mois)
    * Base de données orientée colonnes et un "Blobstore" pour les fichiers
    * Et tout un tas de service Google  (Accounts, URL Fetching, MemCache, XMPP, CRON, TaskQueue, Image Processing, Emails)

Petit retour d’expérience personnel avec Google App Engine, le principe est vraiment bien par contre j'ai 2 remarques importantes:

* d'une version à l'autre du SDK, j'ai certaine fonctionnalité de ce blog qui change.
* le comportement du SDK n'est pas identique au comportement du vrai Google App Engine.
 
### Force.com
Force.com était un acteur que je ne connaissais pas (bien que ma boite utilise Salesforce sans que je le sache). Ils ont commencé avec le SaaS [Salesforce](www.salesforce.com) puis ils on peu à peu créé une offre Paas avec [Force.com](http://www.force.com/).
Voila un aperçu de leurs solutions:

* SaaS
    * [Sales Force](http://www.salesforce.com), outil CRM
    * JigSaw, Customer Service
    * [RemedyForce](http://www.salesforce.com/remedyforce/), Gestion de Helpdesk
    * [Chatter](http://www.salesforce.com/fr/chatter/), (Twitter, Facebook et chat pour Entreprise)
    * [Radian 6](http://www.radian6.com/), Social Network Listener
    * ...
* PaaS avec Force.com
    * [Heroku](http://www.heroku.com/) qui est un hébergeur Node.js et Ruby
    * [Database.com](http://database.com) une base de donnée Cloud
    * [AppForce](http://www.salesforce.com/platform/appforce/) pour écrire des applications avec un L4G pour Force.com
    * [SiteForce](http://www.force.com/products/index.jsp?a#1&slide#1) pour créer des sites WEB
 
### Azure
[Azure](http://msdn.microsoft.com/fr-fr/windowsazure) est la solution de Microsoft. D'après le formateur, c'est des meilleurs infrastructure technique qu'il connaisse. La ou le bas blesse pour le formatteur, c'est au niveau des offres. Pour lui quand on prend un service Cloud chez Microsoft, on s'attendrait à avoir un Exchange prêt à l'emploi ce qui n'est pas directement possible apparemment. L'offre de Microsoft est en fait beaucoup plus orientée vers les développeurs avec:

* intégration dans Visual Studio
* Instanciation de de Serveur Windows
* Service Bus
* SQL Server dans le Cloud
* Blob Storage (stockage binaire)
* Workers (genre de traitement batch)
* Web

L'autre offre de Microsoft est [Office 365](http://www.microsoft.com/fr-fr/office365/online-software.aspx). C'est une offre qui permet d'utiliser les outiles bureautique de la suite Office en mode Web.
 
### Autres Offres
Il existe tout un tas d'autres offres

* IaaS
    * [GoGrid](http://www.gogrid.com/)
    * [Rackspace](http://www.rackspace.com/)
    * [Joyent](http://www.joyent.com/)
    * [Orange Flexible Computing](http://www.orange-business.com/fr/entreprise/portfolio/catalogue/toutes-solutions/flexible-computing.html)
    * [OVH](http://www.ovh.com)
* PaaS
    * [GridGrain](http://www.gridgain.com/)
    * [GigaSpaces](http://www.gigaspaces.com/)
    * [Cloud Bees](http://www.cloudbees.com/)
    * [Cloud Foundry](http://www.cloudfoundry.com/)

D'autres offres se créent tous les jours. Il est fort à parié qu'après cette explosion, il va y avoir de la concentration dans les années à venir. 

Notons également qu'une norme Cloud est en train de voir le jour : la norme Open Data Center.
 
## Conclusion
On voit que les possibilités du Cloud sont immenses et que les fournisseurs et leurs services sont nombreux. Aussi il me parait important avant de se lancer dans n'importe quel projet Cloud de faire un étude sérieuse et de ne pas promettre à ses clients/décideurs que le Cloud est la réponse à tous leurs problèmes.
