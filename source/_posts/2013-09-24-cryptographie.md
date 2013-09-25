---
layout: post
title: "Dossier Cryptographie"
date: 2013-09-24
comments: true
categories: cryptographie
---

Cet article a pour but de présenter quelques éléments concernant la cryptographie, et notamment tous les mécanismes qui sont mis en place dans les cartes à puces et les systèmes qui y sont connectés. L'article est un peu long, la suite en suivant le lien.

<!--more-->

## Livres
Tout d'abord quelques bons livres pour s'initier à la cryptographie. L'[Histoire des codes secrets](http://www.amazon.fr/Histoire-codes-secrets-Simon-Singh/dp/2253150975) de Simon Singh est un super bouquin de vulgarisation. L'auteur retrace de l'antiquité à nos jours  l'histoire des codes secrets.
C'est passionnant et ça se lit très facilement (comme un roman).

On m'a fortement recommandé de lire [Secrets and Lies: Digital Security in a Networked World](http://www.amazon.fr/Secrets-Lies-Digital-Security-Networked/dp/0471453803) qui est un livre plus sérieux présentant les différentes solutions qu'on peut mettre en place pour protéger un système.

## Terminologie
Parlons un peu terminologie. Dans le monde de la cryptographie, il y a un vocabulaire précis à utiliser.
Tout d'abord on parle d'opération de **Chiffrement (encryption)** et **Déchiffrement (decryption)**. L'encodage et le décodage (par exemple passer une information en BASE64) ne sont pas des opérations cryptographiques.

La science associée à la cryptographie est la **cryptanalyse (cryptanalysis)**. On dit du message original (celui à chiffrer) qu'il est un **texte brut (plain text)**. Le message obtenu suite à une opération de cryptographie est le texte chiffré (cyphertext). Pour réaliser le chiffrement d'un message en texte brut, on utilise une **clé de chiffrement (key)**. **L'ensemble des clés (Key Space)** est l'ensemble de toutes les clés possibles pour un algorithme de chiffrement donné. Par exemple, pour une clé de chiffrage 10 bits, la taille de l'ensemble des clés est de 2 puissances 10.

Un **vecteur d'initialisation (initialization vector)** est une valeur aléatoire combinée avec des portions du message original permettant aux algorithmes de chiffrage d'éviter les répétitions (et donc d'être plus facile à casser). On peut parler aussi de **Nonce**.

Un **système cryptographique (Crypto System)** est une combinaison d'algorithmes permettant de chiffrer/déchiffrer un message. Un **processeur cryptographique (Crypto Processor)** est un processeur permettant de supporter matériellement le processus de chiffrage/déchiffrage.

## Buts d'un système de Cryptage

On met rarement en place un système de de cryptographie par plaisir. Un système de cryptage est nécessaire quand on veut implémenter un ou plus plusieurs des points suivants:

- la **Confidentialité**
    - si quelqu'un intercepte le message il ne peut rien en faire
- L'**Intégrité**
    - On s'assure que le message reçu est conforme à l'aide d'un Hash
    - Attention, le concept d'intégrité ne vaut pas une signature électronique
- l'**Authentification (certificate)**
    - Le message est protégé par une mot de passe
    - Le message est signé (Digital Signature)
- La **Non Répudiation (Identity)**
    - On peut certifier la provenance du message et protéger des usurpations d'identités
    - L'identité est garantie.

## Vulnérabilités

Un système de cryptographie doit être bâti d'après le **[principe de Kerckhoffs](http://fr.wikipedia.org/wiki/Principe_de_Kerckhoffs)**,
c'est à dire que la protection ne doit pas résider dans le secret de l'algorithme, mais plutôt dans la clé. Pour être réellement garanti,
l'algorithme d'un système de cryptographie doit être public. La sécurité doit résider dans la clé plutôt que dans le secret de l'algorithme.

De manière générale, développer son propre algorithme est une très mauvaise idée, et cela pour plusieurs raison. D'une part, on ne peut
garantir à un tiers l’invulnérabilité  de l'algorithme. D'autre part si cela n'est pas son métier, il y a un risque réel pour créer un système
inefficace.

Il faut également faire attention à l'ensemble du système, pas seulement la clé et le choix d'un algorithme. C'est en effet le maillon le plus faible
de la chaine qui fixe la sécurité globale du système. Si la carte qui implémente le décodage est sensible (par exemple aux surcharges électriques), on 
peut arriver a induire des comportements inattendus permettant de déchiffrer le message. Pour pallier à ces faiblesses techniques (qui sont découvertes
au fil de l'eau), la meilleure solution est de changer régulièrement le système de cryptage. C'est pour cela par exemple qu'on change régulièrement de 
carte bleue.

Actuellement on considère qu'une clé de 256 bits est suffisamment complexe pour être protégé pour la durée de vie de m'univers. En revanche des
failles sont régulièrement découvertes sur les systèmes utilisant ces clés. Donc les "pirates" ont plus tendances à s'introduire sur le système recevant
ou émettant le message plutôt que de l'intercepter.

En France on peut utiliser les algorithmes symétriques DES, 3DES, AES. On est en revanche tenu de déclarer tout importation ou exportation de système cryptographique.

## Les algorithmes symétriques

Ces algorithmes sont utilisés pour assurer la confidentialité d'un message et la vérification de l'intégrité des données. Le principe de ce genre
d'algorithme est que la même clé est utilisé pour chiffrer et déchiffrer.

Les 3 principaux algorithmes symétriques sont les suivants:

- [DES](http://fr.wikipedia.org/wiki/Data_Encryption_Standard) : Il a été créé dans les années 70 par IBM, il était prévu pour tenir 10 ans face aux Hackers. C'est un système de chiffrage 56 bits. Actuellement ce système de chiffrage est complétement dépassé et ce casse facilement avec des attaques de force brute (Brute Force, on essaye toutes les combinaisons)
- [3DES](http://fr.wikipedia.org/wiki/3DES) : C'est une variante de DES ou on applique 3 fois le chiffrage DES de différentes façon. Pourquoi 3 fois ? parce qu’apparemment  2 fois ne sert à rien. Ce type de chiffrage est très utilisé dans le monde bancaire. Ce système est actuellement cassé, mais l'opération reste compliquée à effectuer.
- [AES](http://fr.wikipedia.org/wiki/Advanced_Encryption_Standard) : il a était mis au point en 2000 et se base sur une clé pouvant aller de 128 à 256 bits.

D'autres algorithmes existent mais sont moins utilisés: IDEA (très similaire à DES), Blowfish, RC4, RC5, CAST, SAFER, Twofish.

Ce type d'algorithmes est utilisé pour chiffrer des messages par bloc. En général on prend un bloc faisant la taille de la clé et on complète
avec un motif final pour compléter la fin du message. Il y a plusieurs façons d'appliquer le chiffrement:

- ECB (Electronic Code Book), chaque block est chiffré de manière indépendante, ce type d'application est de moins en moins utilisé, car on peut retrouver des patterns et donc plus facilement cassé le chiffrage
- CBC (Cipher Block Chaining) chaque block dépend du bloc précédent, un ou exclusif est appliqué entre le bloc courant et le bloc précédent. C'est plus sûr mais cela comporte pose problème en streaming. Si on perd un bloc, on ne peut plus déchiffrer le message, il faut donc mettre en place des trames de resynchronisation.
    - L'avantage de ce mode d'application, c'est qu'il est simple. Cela peut donc fonctionner sur des processeurs très faibles.
- OFB (Output Feedback), c'est sur la clé qu'on fait un ou exclusif pour ajouter de la "variabilité".
- CTR (Counter), on prend un nombre aléatoire qui permet de faire varier la clé à chaque chiffrement de bloc.

Les algorithmes sont simples et facile à mettre en place mais ils comportent des problèmes quand on veut les utiliser à grand échelle.
En effet pour 5 utilisateurs, on va avoir besoin de 10 clés pour assurer la confidentialité entre chaque personne. Mais si on passe à 10 utilisateurs,
on va avoir besoin de 45 clés et ainsi de suite ( N * (N-1) / 2).

Si on veut garantir un niveau de sécurité élevé tout évitant la génération d'un grand nombre de clé, il est nécessaire d'utiliser des algorithmes de
chiffrement asymétrique.

## Les algorithmes asymétriques

Ce type d'algorithme a donc été créé au départ pour limiter le nombre de clé à s'échanger entre utilisateurs. Il est basé sur une clé privée permettant
de chiffrer un message et une clé publique (différente de la clé privée) que l'on donne aux autres utilisateurs. Cette clé publique permet uniquement de 
déchiffrer un message chiffré avec la clé privée de l'émetteur du message. La clé privée n'est jamais communiqué aux autres utilisateurs, elle reste secrète.

Ce genre d'algorithmes est très compliqué et donc très lent. Par exemple un chiffrage RSA est 100 fois plus lents qu'un DES.
De plus la sécurité de ce genre de chiffrage est basée sur la longueur de la clé. Actuellement RSA 128 bits est cassé et RSA 1024 est sur le point de
l'être. Il faut donc viser au moins une clé 2048 bits pour être certain d'assures la confidentialité d'un message.

L'avenir de RSA, le principal algorithme de chiffrage asymétrique est difficile à prédire même si pour l'instant il reste sûr. Un nouveau système est en train d'apparaitre. Il est basé sur des [courbes elliptiques](http://fr.wikipedia.org/wiki/Cryptographie_sur_les_courbes_elliptiques) et il permet de réduire la taille des clés (courbes elliptiques 256 serait équivalent à RSA 4096).

## PKI (Public Key Infrastructure)

Les PKI sont des systèmes permettant de s'assurer qu'une clé est bien distribué à la bonne personne, permettant d'éviter ainsi une
attaque de type "Man in the Middle" Pour garantir la distribution des clés, on passe par des autorités de certification. Quand on
reçoit une clé en provenance d'une autorité, on a la garantie que le message et la clé sont sûrs. La plus connue de ces autorités de
certification est Verisign. De manière générale les autorités de certification sont basées sur un système de certification arborescent,
et dans la plupart des cas, la NSA est haut.

## Echanges de clés

Pour échanger de manière sûre des clés, des méthodes ont été définie, comme celle de [Diffie-Hellman](http://fr.wikipedia.org/wiki/Diffie-Hellman).
Comment faire quand personne ne connait rien l'un de l'autre pour partager une clé. On va par exemple utiliser le RSA pour envoyer une clé symétrique
le temps d'une transaction.

## Intégrité (hash algorithms)

Pour vérifier l'intégrité des données, on utilise des fonctions de hachage. Ces fonctions sont appliquées sur le message en clair, cela permet 
de vérifier que pendant la transmission du message, le contenu n'a pas été altéré. On peut utiliser des algorithmes comme [SHA1](http://fr.wikipedia.org/wiki/SHA-1) pour faire ce hachage. On peut aussi utiliser un algorithme de chiffrement pour calculer un HASH et chiffrer le message.

Attention la fonction de HASH ne doit pas être bijective, sinon on peut retrouver le message originel. Il existe plusieurs méthodes, utilisable selon
les cas :

- Message Digest avec SHA1 dans le mode asymétrique
- Message Authentication Code (MAC) dans le mode symétrique

Les fonctions de hachage les plus connus sont

- [SHA1](http://fr.wikipedia.org/wiki/SHA-1)
- [MD5](http://fr.wikipedia.org/wiki/MD5), des dictionnaires sont disponibles depuis longtemps pour celle-ci
- [HAVAL](http://fr.wikipedia.org/wiki/HAVAL)
- [RIPEMD-160](http://fr.wikipedia.org/wiki/RIPEMD-160)


## Méthodes d'autentification (Signature Digitale)

Les fonctions de chiffrement asymétrique sont utilisées également pour signer des documents. La clé privé étant unique, on peut la
considérer comme un signature. Une personne ayant signé un document de sa clé, ne peut pas répudier sa décision.
La signature électronique étant lié à ce qu'on signe, elle varie en fonction de ce qu'on signe.
De manière pratique on va signer un document, ou simplement son HASH.

Pour qu'une signature ait une valeur et qu'elle ne puisse pas être imitée, on va passer par un organisme de séquestre pour stocker
les documents signés ainsi que les clés publiques. Les signatures électroniques sont basé sur l'algorithmes [DSA](http://fr.wikipedia.org/wiki/Digital_Signature_Algorithm) qui a été introduit quand RSA était encore breveté.


## Infrastructure des PKI (Certificates)

Les organismes de certifications sont décrits par la norme [X509](http://fr.wikipedia.org/wiki/X.509).
Pour résumlé un PKI c'est:

- Séquestre (notarization)
- Time Stamping
- Non répudiation
- Privilege management

Un certificat est un moyen de faire confiance à une clé publique. Le certificat n'est pas une signature. Le certificat n'est pas un système d'authentification. Les certificats sont statiques (ils servent à prouver la provenance d'une clé publique).

e.g. HTTPS (SSL)
La faiblesse de SSL est dans le fait qu'on identifie pas le client. Du coup on peut multiplier les attaques coté client pour récupérer les clés.

## Les attaques

On peut attaquer un système en interne (e.g. une carte bleue, une carte SIM, etc.) en électronique pure. Il suffit de tester un composant en injectant différents signaux et voir comment le système se comporte. C'est pour cela que sur les puces assurant le chiffrement les pates ou les pistes de test ont disparues.
Cela rend plus difficile l'envoie de signaux (rien n'empêche par la suite de "strapper" des pistes sciées sur une carte). 
Ces études sont assez compliquées à faire, seul des gouvernements ou des mafias très riches ont les moyens de se payer ce genre d'étude. Ces groupes
d'études sont très ingénieux est ont de gros moyens, ils sont capables par exemple de lire la RAM au microscope électronique. C'est pour cela que
l'élctronique d'un système de chiffrement est rendue compliquée:

- les éléments à l'intérieur des puces ne sont pas alignés pour éviter une lecture trop facile.
- on met des capteurs (lumière, active grid),
- on va cacher les éléments (couches, taille, pistes compliquées, bouclier, le bus de transport lui-même sera chiffré)
- on rend les choses compliquées (logique redondante)

De manière externe on peut injecter des signaux pour provoquer un état imprévu sur le système. Par exemple, certaines puces sont sensibles à certaines
longueurs d'onde de lumière. On peut aussi injecter des pics de voltage, sur certains vieux systèmes en envoyant des piques de 50V, on pouvait faire
sauter des instructions aux puces. On peut également étudier la consommation électrique d'une puce pour savoir quelle opération elle est en train d'effectuer. Pour se protéger de ce genre d'attaque il faut:

- réduire le signal du processeur pour que les signaux soit difficile à prélever,
- ajouter du bruit pour rendre encore plus difficile le prélèvement des signaux,
- supprimer le timing pour être moins sensible à l'injection de signaux,
- modifier l'ordre des opérations pour une même action,
- mettre des capteurs de lumière, de tension, de température pour détecter les attaques,
- répéter les opérations pour éviter de se faire injecter des signaux 2 fois,
- détruire la puce si problème.

J'espère que cet article aura été instructif et bon point d'entrée dans le monde de la cryptographie.
