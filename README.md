# SWE2022_KG_MH_SL
SWE Projekt von Kevin G., Matthias H. und Samuel L.

### Installation
1. Git Repository klonen
```
git clone https://github.com/DHBW-INF2020/SWE2022_KG_MH_SL.git
```

2. Als Projekt in der eigenen IDE öffnen
3. Libaries einbinden. Für das Projekt werden die Bibliotheken "gson-2.9.0", "org.json", "junit-jupiter-5.8.1" und "junit-jupiter-api-5.8.1" verwendet. Zu finden im Ordner "lib"
4. Projekt ausführen
   1. Eine der drei möglichen Varianten zur Ausführung verwenden
      1. Aufruf ohne Parameter => standard-debugger der IDE verwendbar
      2. Aufruf mit Config File (e.g. ["D:\Dateien Schnell\Java\SWE2022_KG_MH_SL\input\program_configuration_2.json"])
      3. Aufruf mit Aggregatart, Ausgabeformat, und Eingabedatei (e.g. [sta json "D:\Dateien Schnell\Java\SWE2022_KG_MH_SL\res\Aufgabe_3_satellites.json"])
   2. Die Ausgabe ist im Ordner "output" zu finden


## Dokumentation
### Bekannte Fehler
Unter Windows failt ein Test, da die Endlines von Linux abweichen
>Workaround:
>Programm einmal durchlaufen lassen und dann die output.json je nach Aggregat kopieren und in "test\resources" einfügen und umbenennen


### Diagramme
1. Übersichtsdiagramm
2. Klassendiagramme
3. Kontrollflussgraphen