Flugbókunarkerfi
---
> Þróun hugbúnaðar - 2023 - hópur 3f

# Uppsetning á gagnagrunni
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

# testing
fara í rót og keyra eftirfarandi 
> gert er ráð fyrir því að maven sé sétt upp á vél
```bash
mvn clean test
```

# Uppbygging skráa

![Visualization of the codebase](./diagram.svg)
