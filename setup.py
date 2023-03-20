import os

# create directory if it doesn't exist
if not os.path.exists("src/db"):
    os.makedirs("src/db")

# execute SQLite commands
if os.name == 'nt':
    cmd = 'cmd /c "sqlite3 src/db/flightBooking.db < db/Schema.sql && sqlite3 src/db/flightBooking.db < db/Insert.sql"'
else:
    cmd = "cd db && sqlite3 ../src/db/flightBooking.db < Schema.sql && sqlite3 ../src/db/flightBooking.db < db/Insert.sql"

os.system(cmd)
