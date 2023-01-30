@ECHO off

TITLE Projet CMI ‚quipe QI

COLOR 80

MODE 120,30

CD Programme/java

JAVAC -d ../cls *.java

CD ../cls

JAVA AppICS

PAUSE