# SPL - Machine Learning Toolbox, Ex. 2 (Laufzeitvaribilität)

## Verfügbare Laufzeitparameter

* --cli: Starte command line interface. Ohne dieses Attribut wird die GUI gestartet. GUI ist allerdings noch nicht benutzbar.
* --logging[=file|=console]: Logging in eine Datei, auf der Konsole, oder beidem
* --debug: Momentan automatisch an, da die Applikation im development-Modus gestartet wird (develop bedingt debug).
* --surpress-debug: Verlässt debug- und development-Modus. (Keine Exceptions, humane Hinweise)
* --arff: Aktiviert feature zum Laden von *.arff-Dateiformaten.

Ohne das Laden des arff features lässt sich die Klassifizierung nicht benutzen, da Standardformate wie dat oder csv 
noch nicht implementiert sind.	

## Fortschritt

Da ich die Applikation von grundauf in den letzten zwei Wochen erstellt habe, ist sie noch nicht sehr weit fortgeschritten.
Ebenso ist das Softwaredesign ausbaufähig. 

GUI ist noch nicht weit fortgeschritten.

Bisher lassen sich lediglich *.arff-Dateien laden und mittels ZeroR-Algorithmus "klassifizieren".
Genaugenommen macht ZeroR nichts anderes, als den Mittelwert der Instanzen über die Klassen zu bilden, und damit eine Aussage zu treffen,
wie wahrscheinlich es ist, dass ein Datenpunkt einer Klasse angehört.

Beispielsweise liegt die iris.arff unter data/ bei. Es gibt drei Klassen: Iris-setosa, Iris-versicolor, Iris-virginica.
Insgesamt befinden sich 150 Datenpunkte in der Datei und jeweils 50 Datenpunkte sind einer der drei Klassen zugeordnet.

ZeroR trifft also die folgende logische Aussage:

```
Overall probability: 
	iris-setosa: 0.33333333333333326
	iris-versicolor: 0.3333333333333333
	iris-virginica: 0.33333333333333326
```

   
Also die Erfolgswahrscheinlichkeit, dass ein neuer Datenpunkt richtig klassifiziert wird, liegt bei 33%.
ZeroR ist also mehr eine Art Dummy-Algorithmus, der mit dem eigentlichen Klassizieren noch nichts zu tun hat. 

Die Implementierung versucht sich noch an einer Kreuzvalidierung mit 10 Folds, das macht aber nur bedingt Sinn mit ZeroR.
Idee ist, dass man alle Instanzen in 10 Grupen einteilt (Immer die nächsten 15 zusammen, beispielsweise) und dann jedes Mal den 
Klassifizierer mit 9 Gruppen davon trainiert und gegen die übergebliebene testet.
Das ganze dann 10 Mal, damit jede Gruppe mal gegen den Rest getestet wird.
ZeroR unterstützt aber in dieser Implementierung kein Testen von Datenpunkten.

