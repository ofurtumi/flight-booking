# flight bookin
## þróun hugbúnaðar - 2023 - hópur 3f

### uppsetning á gagnagrunni
gert er ráð fyrir því að búið sé að setja upp sqlite3 á vél og að skipunin sé í path

**linux**
```bash
python3 setup.py
# eða
python setup.py
```

**windows**
ekki komið, þarf að testa á windows vél en prófið
```cmd
python3 setup.py
```

### keyrsla
inn í src er hægt að keyra  
```bash
javac *.java && java -cp .:sqlite-jdbc-3.39.3.0.jar User <id á user>
```

til þess að fá einstakan user með id
> sama hér og fyrir ofan, á eftir að testa á windows vél
