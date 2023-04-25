@ECHO off

@title Update your programs
SetLocal EnableDelayedExpansion
Setlocal EnableExtensions 

:debut
WINGET UPGRADE --include-unknown


:MiseAjour
ECHO Voulez-vous mettre a jour tous vos programmes ?
set /p YNUpdate=(Yes/No): 


IF !YNUpdate! == Yes (
	call :affichageCouleurVert "Mise a jour des programmes..."
	WINGET UPGRADE --ALL --include-unknown
	ECHO.
	call :affichageCouleurBleu "Mise a jour des programmes terminee"
) else (
	IF !YNUpdate! == No (
		call :affichageCouleurVert "Mise a jour des programmes annulee"
		goto :exit
	) else (
		call :affichageCouleurRouge "Saisie incorrecte veuillez ressaisir votre reponse"
		ECHO.
		goto :MiseAjour
	)
)

:verif
ECHO Voulez-vous faire une verification ?
set /p YNVERIF=(Yes/No): 

IF !YNVERIF! == Yes (
	call :affichageCouleurVert "Verification en cours..."
	goto :debut
) else (
	IF !YNVERIF! == No (
		call :affichageCouleurVert "Verification annulee"
		goto :exit
	) else (
		call :affichageCouleurRouge "Saisie incorrecte veuillez ressaisir votre reponse"
		ECHO.
		goto :verif
	)
)

:exit
ECHO Done.
timeout 5
exit /b



:affichageCouleurVert
ECHO [32m%~1 [97m
EXIT /B 0

:affichageCouleurRouge
ECHO [31m%~1 [97m
EXIT /B 0

:affichageCouleurBleu
ECHO [94m%~1 [97m
EXIT /B 0