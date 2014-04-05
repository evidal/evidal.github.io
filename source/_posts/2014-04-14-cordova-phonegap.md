---
layout: post
title: "Cordova & Phonegap"
date: 2014-04-03
comments: true
categories: [mobile, html5]
published: true
---

Dans le cadre d'un projet, j'ai développé une petite appli mobile en utilisant [Cordova](http://cordova.apache.org/).
C'est un outil permettant de créer des applications mobiles multi-support en HTML5 et Javascript.

Le résultat est packagé comme une application mobile standard, par exemple un .apk pour une application Android.
Une fois lancée Cordova fourni un conteneur Web et un "bridge" qui abstrait les fonctionnalités de l'OS mobile (Caméra, Contacts, Accéléromètre, etc.)
De nombreux plugins existent et permettent d'étendre les fonctionnalités de base du framework.

Cordova est issu du Projet [PhoneGap](http://phonegap.com/) initié par Adobe. Adobe a ensuite donné la base de code PhoneGap à la fondation Apache qui a créé Cordova. PhoneGap existe encore comme une distribution de Cordova et comme support d'outils Adobe générant des applications mobiles.

Voilà un petit How-to sur comment mettre en place son environnement de développement Cordova/Android.
Attention, tout environnement pas complétement mal installé sera non fonctionnel et les messages d'erreurs ne sont pas forcément très explicites.


## Java Development Kit
Ma configuration utilise le JDK 7, je n'ai pas testé avec le 8.

* Téléchargez le pack adapté [ici](le http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
* Positionnez les variables d'environement
    * JAVA_HOME
        * sous Linux, `export JAVA_HOME="/path/to/jdk"`
        * sous Windows, positionnez JAVA_HOME="c:\path\to\jdk"
            * dans les variables d'environnement "Start Menu > Computer > Advanced System Settings > Environment Variable"
            * ou dans un cmd.exe, `set JAVA_HOME="c:\path\to\jdk"`
    * PATH
        * Sous Linux, `export PATH=$PATH:$JAVA_HOME/bin`
        * sous Windows, ajoutez %JAVA_HOME%\bin au Path
            * dans les variables d'environnement "Start Menu > Computer > Advanced System Settings > Environment Variable"
            * ou dans un cmd.exe, `set Path=%Path%;%JAVA_HOME\bin`


## Ant
Ant est une des dépendances

* Téléchargez la dernière version de ant [ici](https://ant.apache.org/bindownload.cgi)
* Mettez en place la variable d'environnement ANT_HOME
    * sous Linux, `export ANT_HOME="/path/to/ant"`
    * sous Windows, positionnez ANT_HOME="c:\path\to\ant"
        * dans les variables d'environnement "Start Menu > Computer > Advanced System Settings > Environment Variable"
        * ou dans un cmd.exe, `set JAVA_HOME="c:\path\to\ant"`
* PATH
    * Sous Linux, `export PATH=$PATH:$ANT_HOME/bin`
    * sous Windows, ajoutez %ANT_HOME%\bin au Path
        * dans les variables d'environnement "Start Menu > Computer > Advanced System Settings > Environment Variable"
        * ou dans un cmd.exe, `set Path=%Path%;%ANT_HOME\bin`


## Android SDK
Mon but était de faire des applications Android. Si vous avez besoin de générer des applications pour d'autres plateformes, vous devez installer le SDK pour chaque plateforme visée.

* Téléchargez la dernière version du SDK [ici](https://developer.android.com/sdk/index.html)
* Mettez en place ANDROID_HOME, attention c'est bien le SDK qu'il faut référencer et pas le répertoire contenant Eclipse et le SDK
    * sous Linux, `export ANDROID_HOME="/path/to/android/sdk"`
    * sous Windows dans les variables d'environnement "Démarrer>Computer>Advanced System Settings>Environment Variable" ou `set ANDROID_HOME="c:\path\to\android\sdk"`
* PATH
    * Sous Linux,
        * ```
        export PATH=$PATH:$ANDROID_HOME/tools
        ```
        ```
        export PATH=$PATH:$ANDROID_HOME/platform-tools
        ```
    * sous Windows, ajoutez %ANDROID_HOME%\tools et %ANDROID_HOME%\platform-tools au Path
        * Vous connaissez la manip maintenant :)


## Node.js
Il faut la toute dernière version disponible de node.js. Si vous l'avez déjà, il faut impérativement le mettre à jour.

* Installation de node JS.
    * sous Windows, il faut télécharger un _installer_ (ici)[http://nodejs.org/download/]
    * Sous un Linux, selon votre distrib, faites
        *  sous Ubuntu, `sudo apt-get install nodejs`
        * Sous Fedora/Red Hat/CentOS, `sudo yum install nodejs npm`


## Cordova

### Installation
* Installation de Cordova (enlever sudo sous Windows :) )
```
sudo npm install -g cordova
```

### Utilisation
Créez une application

```
cordova create myapp vidal.myapp MyApp
cd myapp
```

Le projet généré est organisé comme ceci:

* __config.xml__ contient la configuration,
* __hooks__ est un répertoire pour stocker des commandes customisées Cordova (je ne l'utilise pas),
* __merges__ est un répertoire que je ne l'utilise pas,
* __platforms__ est le répertoire qui contient la partie générée spécifique à chaque plateforme. Il faudra aller y faire un tour pour mettre en place les éléments relatifs à un OS (e.g. authorization, rotation lock, ...),
    * _nom de la plateforme_, il y a un sous répertoire par plateforme,
* __plugins__ est le répertoire contenant les plugins installés,
    * _nom du plugin_, il y a un sous répertoire par plugin,
* __www__ est le répertoire qui nous intéresse le plus. C'est lui qui est considéré comme la racine de votre application. Il contient donc un _index.html_ et toutes les ressources nécessaires à votre application (autres pages html, javascript, images, ...).


Comme indiqué plus haut, plusieurs plateformes sont disponibles. Pour avoir la liste, lancez la commande suivante :

```
cordova platform list
```

Pour faire un projet Android, on va ajouter la plateforme Android au projet.

```
cordova platform add android
```

Pour accéder aux informations du téléphone comme son identifiant, on peut par exemple utiliser le plugin Device:
```
cordova plugin add org.apache.cordova.device
```
Un fois ce plugin installé, on aura accès au runtime à un objet javascript _device_ contenant les informations du téléphone.
De nombreux plugins existent au sein du projet Cordova:

* Accelerometer
* Camera
* Capture
* Compass
* Connection
* Contacts
* Device
* Events
* File
* Geolocation
* Globalization
* Media
* Notification
* Splashscreen
* Storage


Pendant le développement, on peut mocker certains appels à la couche physique comme ceci:

```
if (navigator.userAgent.match(/(iPhone|iPod|iPad|Android|BlackBerry)/)) {
    document.addEventListener('deviceready', this.splash, false);
} else {
    this.splash();
}
```
Pour faire des tests durant le développement de l'application j'ai essayé plusieurs techniques:

* Utiliser le site local
    * C'est finalement la manière la plus pratique de fonctionner. Par contre tous les appels à la couche physique ne fonctionnent pas. Il faut donc les mocker.
* Utiliser la commande `cordova serve`
    * ça ne fonctionne pas. Le fichier cordova.js par en boucle infini dans le navigateur.
* Utiliser l'émulateur [Ripple](http://ripple.incubator.apache.org/)
    * ça n'a pas fonctionné mais le projet semble prometteur
* Utiliser la commande `cordova run android` avec l'émulateur de l'Android SDK
    * ça marche très mal, pas spécifiquement à cause de Cordova mais plutôt à cause des ressources consommées par l'émulateur Android
* Utiliser la commande `cordova run android` avec un téléphone branché sur le port USB
    * Finalement c'est une des solutions les plus rapides à mettre en place.
    * à noter la commande `adb logcat` à lancer pour avoir les logs du téléphone et comprendre ce qui se passe.


Pour réaliser mon application, j'étais parti sur l'utilisation de Boostrap mais le résultat graphique ne faisait pas "application mobile". J'ai donc utilisé [Jquery Mobile](http://jquerymobile.com/) qui a un rendu très satisfaisant.

Le résultat final est très bon, rapide et facile à faire par rapport à un développement Android classique.
Avec cette méthode, nous pensons sortir plus facilement des applications au sein de mon entreprise.

