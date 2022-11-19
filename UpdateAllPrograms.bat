@echo off

@title Update your programmes
SetLocal EnableDelayedExpansion
Setlocal EnableExtensions 

:debut
WINGET UPGRADE



:while
ECHO Voulez vous mettre a jour tout vos programmes?
set /p YNUpdate=(Yes/No): 


IF !YNUpdate! == Yes (
	WINGET UPGRADE --ALL
	ECHO "Je me mets a jour"
) else (
	IF !YNUpdate! == No (
		ECHO "Aucune mise a jour a ete faite"
	) else (
		echo "Saisie incorrecter veuiller resaisir votre reponse"
		goto :while
	)
)


IF !YNUpdate! == No (
	echo Done.
	timeout 5
	exit /b
)

:verif
ECHO Voulez vous faire une verification ?
set /p YNVERIF=(Yes/No): 

IF !YNVERIF! == Yes (
	ECHO "Verification en cours"
	goto :debut
) else (
	IF !YNVERIF! == No (
		ECHO Done.
		timeout 5
		exit /b
	) else (
		echo "Saisie incorrecter veuiller resaisir votre reponse"
		goto :verif
	)
)