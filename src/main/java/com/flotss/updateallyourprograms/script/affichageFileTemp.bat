@ECHO off

SET PATHTEMP=%TEMP%
WINGET UPGRADE --include-unknown > %PATHTEMP%\winget.tmp
exit /b