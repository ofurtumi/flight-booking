import os

# create directory if it doesn't exist
if not os.path.exists("src/db"):
    os.makedirs("src/db")

# execute SQLite commands
if os.name == 'nt':
    cmd = 'cmd /c "sqlite3 src/db/flightBooking.db < db/schema.sql && sqlite3 src/db/flightBooking.db < db/insert.sql"'
else:
    cmd = "cd db && sqlite3 ../src/db/flightBooking.db < schema.sql && sqlite3 ../src/db/flightBooking.db < insert.sql"

os.system(cmd)
