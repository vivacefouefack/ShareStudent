# Projet MOBG5

Ce dépôt contient les sources du projet "share student".

## Description

share student est une application de partage entre etudiants, le principe est le suivant:
les étudiants disposant d'un compte d'utilisateur et ayant des objets à partagés ou non peuvent après la connexion
créer des publications pour ces objets ou consulté la liste de tous les publications, s'il est intéressé par une publication, il a
la possibilité d'envoyer un message à l'auteur de la publication.
l'application contient:
-un écran de connection: il est affiché lorsque l'application est lancé et permet à l'utilisateur soit créer un compte,soit se connecter.

-un écran de création de compte: dispose d'un formulaire permettant de récolté les informations nécessaires à la création du compte

-un écran d'accueil :il s'affiche lorsque les introduites pour la connexion sont correctes.il contient toutes les publication, un tiroir et un menu option.
la pression sur une publication ouvre une nouvelle interface qui affiche le détail de la publication.

-un tiroir: contient plusieurs rubriques(messages,publication,déconnexion)

-un écran de détail pour chaque application: cet écran contient une image de l'objet, une description et un bouton qui permet de rediriger l'utilisateur vers le service 
de messagerie(gmail)

## Persistance des données

Les données sont persistées via firebase.

## Service rest


## Auteur
**Vivace Fouefack** g54490

## TODO (QHB)
bug : les messages d'erreur ne sont pas visibles dans l'écran d'enregistrement--
bug : après que l'utilisateur ait autorisé la caméra, l'écran de connexion s'affiche à nouveau. Au lieu de cela, la caméra devrait apparaître. 
bug : Dans l'écran "créer un publication", le fait de taper enter après un mot efface le champ de texte.-- 
bug : L'application devrait revenir à l'écran home après un clic sur "Publier".--
new bug: après avoir fait la publication d'une nouvelle annonce,la page home s'affiche mais ne contient aucune publication.
bug : le bouton back ne devrait pas revenir à l'écran login. 
bug : vérifier que la liste dans l'écran home n'est jamais vide. //test sans data
bug : quand l'utilisateur entre un mauvais user/password, puis va dans l'écran register, l'enregistrement ne fonctionne plus.--
amélioration : l'utilisateur doit pouvoir éditer une anonce publiée
amélioration : refaire les layouts
amélioration : les titres des ecrans doivent tous être en francais ou en anglais, mais pas les deux.-- 
amélioration : après un enregistrement réussi, c'est le fragment "home" qui doit être affiché et non le fragment "login".
amélioration : possibilité de trier la liste dans l'écran d'accueil par ordre alphabétique et par date
amélioration : l'application gmail devrait s'ouvrir directement en appuyant sur le bouton "ecrire à l'auteur" et le nom de l'article devrait y être mentionné. 
amélioration : l'auteur d'une annonce devrait pouvoir l'effacer


