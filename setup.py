import os

path="flightBooking.db"
testpath = "test.db"

# execute SQLite commands
if os.name == 'nt':
    cmd = f'cmd /c "sqlite3 db/{path} < db/schema.sql && sqlite3 db/{path} < db/insert.sql"'
    test = f'cmd /c "sqlite3 db/{testpath} < db/schema.sql && sqlite3 db/{testpath} < db/testInsert.sql"'

else:
    cmd = f"cd db && sqlite3 {path} < schema.sql && sqlite3 {path} < insert.sql"
    test = f"cd db && sqlite3 {testpath} < schema.sql && sqlite3 {testpath} < testInsert.sql"

os.system(cmd)
os.system(test)
