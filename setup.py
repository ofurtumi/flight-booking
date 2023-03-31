import os

# create directory if it doesn't exist
if not os.path.exists("src/db"):
    os.makedirs("src/db")

path="flightBooking.db"
testpath = "test.db"

# execute SQLite commands
if os.name == 'nt':
    cmd = f'cmd /c "sqlite3 {path} < db/schema.sql && sqlite3 ${path} < db/insert.sql"'
else:
    cmd = f"cd db && sqlite3 {path} < schema.sql && sqlite3 {path} < insert.sql"
    test = f"cd db && sqlite3 {testpath} < schema.sql && sqlite3 {testpath} < insert.sql"

os.system(cmd)
os.system(test)
