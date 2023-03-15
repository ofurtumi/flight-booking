import os

os.system('mkdir -p src/db')

cmd = ""
if os.name == 'nt':
    cmd = 'cmd /c "sqlite3 -init db/flugdb.sql"'
else:
    cmd = "cd db && echo '.save ../src/db/flugdb.db' | sqlite3 -init flugdb.sql"

os.system(cmd)
