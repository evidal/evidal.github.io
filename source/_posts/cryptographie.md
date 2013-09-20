---
layout: post
title: "Cryptographie"
date: 2013-08-24
comments: true
categories: cryptographie
---

Cet article a pour but de présenter quelques éléments concernant la cryptographie, et notamment tous les mécanismes qui sont mis en place dans les cartes à puces et les sytèmes qui y sont connectés.

## Terminologie
Parlons un peu terminologie. Dans le monde de la cryptographie, on utilise les termes suivants:

- Chiffrement (encryption)
- Déchiffrement (decryption)

Attention, l'encodage n'est pas une opération de chiffrement.

La science associée à la cryptographie est la cryptanalyse (cryptanalysis).
On dit du que message originial qu'il est un text brut (plain text).
Le message obtenu suite à une opération de cryptographie est le texte chiffré (cyphertext).
Pour réaliser le chiffrement d'un message en text brut, on utilise une clé de chiffrement (key).
L'ensemble des clés (Key Space) est l'ensemble de toutes les clés possibles pour un algorithme de chiffrement donné. Par exemple, pour une clé de chiffrage 10 bits, la taille de l'ensemble des clés est de 2 puissance 10.
Un vecteur d'initialisation (initialization vector) est une valeur aléatoire combiner avec des portions du message orginal permettant aux algorithmes de chiffrage d'éviter les répétitions (et donc d'être plus facile à casser). On peut parler aussi de Nonce.
Un système de cryptographie (Crypto System) est une combinaison d'algorithmes permettant de chiffrer/déchiffrer un message.
Un processeur de cryptographie (Crypto Processor) est un processeur permettant de supporter materiellement le processus de chiffrage/déchiffrage.

## Buts d'un système de Cryptage

On met rarement en place un système de de cryptographie par plaisir, 

- Assurer Confidentialité
    - si quelqu'un intercepte le message il ne peut rien en faire
- Intégrité (complément de la confidentialié (chiffré signé))
    - Hash
    - != signature éléectronique
- Authentification (certificate)
    - Password
    - Digital Signature
- nonrepudiation (Identity)
    - usurpation d'identité
    - garantir l'identité

##Vulnerabilités

- Book : Secrets and Lies
- principe de kerkov
- ne pas faire soit même un algo de cryptage (sauf si c'est son métier)
- toujours utiliser un algo publié, la sécurité est dans la clé
- exemple de la carte avec le if avec surcharge pour passer à l'étape suivante
- c'est  pour ça que les cartes blue sont chnagées aussi souvent (car patché poue résister à certaine attaque)
- il faut toujours faire attention au système qui héberge le système de cryptage car si l'algo est puissant, le système peut être fabile
- clé 256 bits c'est assez pour la vie de l'univers
- c'est le maillon le plus faible de la chaine qui fixe la sécurité globale du système
- Actuellement il est plus facile de péter le système cible pour récupérer un message que d'intercepter un message pour le déchiffrer (introduire plutot qu'intercepter)
- en france on peut utiliser DES, 3DES, AES. si autre on doit déclarer. on doit déclarer à l'export.


## Symmetric Algorithm

- confidentiality
- Data integrity
- même clé de chiffrage et déchiffrage
- DES, 3DES, AES
    - DES 1970 IBM, Cryptopathe l'ont fait avec 10 avances sur les Hackers
    - quand AES est sorti, on peut imaginer que les concepteurs ont de l'avance sur les Hackers
- Application
    - chiffrement par bloc, le plus utilisé
    -- Taille block = taille clé
    -- pattern de fin pour compléter la fin du meesage
    -- ECB (each block encrypted separately and independant of the other block, de moins en moins utilisé, car on peut retrouver des patterns
    -- CBC (Cipher Block Chaining) chaque block dépent du block d'avant (en mode stream c'est compliqué, il faut de la resynchro) chffré du block d'avant et Xor avec le block couratnt. Le denier block peut être utilisé comme signature.
    -- c'est simple -> donc ça tourne sur n'importe quel processeur
    -- autress block OFB et CTR
    -    - OFB, c'est sur la clé qu'on fait un XOR
    -    - CTR, nombre aléatoire pris au début, et on recalcule la clé a chaque chiffrement
- 3 système
    - DES (56 bits)
    -- niqué en brute force, trop vieux
    - 3DES
    -- Double DES ne sert à rien
    -- DES, DES -1, DES (56 bits)
    -- très utilisé en bancaire
    -- cassé mais compliqué à faire
    -- AES de plus en plus utilisé
    - AES (126 à 256 bits) 2004-2005
D'autres alfo moins utilisés
- IDEA (très similaire à DES), Blowfish, RC4, RC5, CAST, SAFER, Twofish
- Le problème, c'est qu'il faut distribuer la clé à tout le monde
    - 5 users -> 10 clés
    - 10 users -> 45 clés
    - d'ou la naissance de la clé assymetrique

## Asymmetric Algorithm
Besoin de départ pour limiter le nombre de clé à s'échanger
1 clé publique, 1 clé privée (et différente) et on diffuse la clé publique à tout le monde (la privé reste secrète)
Algo très compliqué, Trés très lent par rapport à symlétrique
e.g RSA 100 fois plus lent que DES
RSA 128 bits est déjà cassé
RSA 1024 limite
Aujourd'hui il faut aller sur du au moins 2048 bits
La faiblesse est sur la longueur des clés, l'algo est sur.
L'avenir du RSA est difficlé à prédire (pour l'instant il reste sur)

Un nouveau système est en train d'apparaitre c'est les courbes elliptiques qui permet de réduire la taille des clés (courbes elliptiques 256 === RSA 4096)

## PKI (Publi Key Infrastrucutre)
Comment être sur que la clé est bien distribuée à la bonne personne. (Attaque Man in the Middle)
D'ou les authorités de certification -> je peux utiliser une clé d'un site de manière sure.
La plus connu Verisign.
De manière générale, c'est un système arboresecnt de certification, la NSA est haut dans la plupart des cas.

Methods for sharing keys (including Diffie-Hellman)
Comment faire quand personne ne connait rien l'un de l'autre pour partagé une clé
C'est du RSA pour créer un clé symetrique le temps d'une transaction

Methods for ensuring data integrity (hash algorithms)
Le Hash est fait sur le clair. On peut utiliser SHA1 pour faire un Hash.
On peut aussi utiliser un algo de chiffrement pour calculer un HASH et chiffrer le message.
Le HASH ne doit pas être bijectif !!!
plusieurs methode :

- Message Digest -> SHA1 (monde assymetrique)
- Message Authentication Code (MAC) (monde symetrique)

Les plus connu sont
SHA-1
MD5
HAVAL
RIPEMD-160

## Methods for authentication (digital signatures)
Clé Privé -> j'ai moyen de m'identifié de manière unique -> d'ou la notion de signature
La personne ayant signé avec sa clé privé ne peut pas répudié sa décision.
La signature électronique est lié à ce qu'on signe. La signature varie en fonction de ce qu'on signe.
On peut signer l'intégralité d'un document, ou on peut signer son hash.
On doit pouvoir être sur qu'une signature ne puisse pas être imitée (forcée) pour qu'elle ait de la valeur.
Principe de sequestre pour stocker les documents signés ainsi que les clés publiques.
Les algos:

- DSA
    - éviter le RSA
    - 320 bits

## Methods to build PKI infrastructure (Certificates)
Système x509
Pour résumlé un PKI c'est:

- Sequestre (notarization)
- Time Stamping
- non repudiation
- Privilege management
Un certificat est un moyen de faire confiance à une clé publique.
Le certificat n'est pas une signature.
Le certificat n'est pas un système d'authentification
Les certificas sont static (ils servent à prouver la provenance d'une clé publique)

e.g. HTTPS (SSL)
La faibless de SSL est dans le fait qu'on identifie pas le client. Du coup on peut multiplier les attaques coté client pour récupérer les clés.

## Attacks
- Internal Attack. En éléctronique pure. En testant de manière electrique un composant, jadis on pouvait péter des cartes en injectant des signaux. Typiquement sur les pates de tests. Maintenant les pates de test on disparue. Mais des gens sont capables de faire du FIB (acronyme) pour strapper des pistes sciés pour casser des cartes. Des mafias très riches sont capable de le faire. De la RAM est lisible au microscope si c'est bien aligné, du coup les fabriquant distribuent les éléments pour que rien ne soit alignés.
pour se prémnuir de tou ça

    - on met des capteurs (lumière, active grid),
    - on va cacher les éléments (couches,, taille, pistes compliquées, bouclier, le bus de transport lui même sera chiffré)
    - on rend les chose pas simple (logique redondante,)

- Side Channel Attacks
    - Injection de signal  (lumière, courant) e.g. pique de courant de 50 volts pour faire passez au code d'après. Les composants sont parfois sensible à certaine longueur d'onde de lumière.
    - en fonction de la consommation on peut par exemple en déduire quel opération fait le processeur

## Solutions

- réduire le signal du processeur
- ajouter du bruit
- supprimer le timing
- modifier l'ordre des opérationsy
- mettre des cpateurs de lumière, de tension, de température
- faire plsuieurs fois les choses pour être sur qu'on c'est pas fait injecter des signaux 2 fois
- détruiure la puce si problème.
