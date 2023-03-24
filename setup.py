import os

# create directory if it doesn't exist
if not os.path.exists("src/db"):
    os.makedirs("src/db")

path="src/main/java/is/hi/flight_booking/database/flightBooking.db"

# execute SQLite commands
if os.name == 'nt':
    cmd = f'cmd /c "sqlite3 {path} < db/schema.sql && sqlite3 ${path} < db/insert.sql"'
else:
    cmd = f"cd db && sqlite3 ../{path} < schema.sql && sqlite3 ../{path} < insert.sql"

os.system(cmd)
