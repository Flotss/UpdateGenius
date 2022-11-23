@ECHO off

REM Update an program with winget and argument
REM @title Update an program with winget and argument

winget upgrade %1

src\main\java\com\flotss\updateallyourprograms\script\update.bat

SET PATHTEMP=%TEMP%
$existe = (findstr  %1 %PATHTEMP%\winget.tmp).count

REM Si il ligne existe alors renvoie 1 sinon 0
IF [ $existe -eq 0 ] (
    goto :exitNormaly
) else (
    goto :exitError
)



:exitNormaly
exit /b 0

:exitError
exit /b 1