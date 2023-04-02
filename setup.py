import os

path="flightBooking.db"
testpath = "test.db"

# execute SQLite commands
if os.name == 'nt':
    path="db/flightBooking.db"
    testpath = "db/test.db"
    cmd = f'cmd /c "sqlite3 {path} < db/schema.sql && sqlite3 ${path} < db/insert.sql"'
    test = f'cmd /c "sqlite3 {testpath} < db/schema.sql && sqlite3 {testpath} < db/insert.sql"'

else:
    path="flightBooking.db"
    testpath = "test.db"
    cmd = f"cd db && sqlite3 {path} < schema.sql && sqlite3 {path} < insert.sql"
    test = f"cd db && sqlite3 {testpath} < schema.sql && sqlite3 {testpath} < insert.sql"

os.system(cmd)
os.system(test)
