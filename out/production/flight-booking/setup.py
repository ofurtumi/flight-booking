import os

os.system('mkdir -p src/db')

cmd = ""
if os.name == 'nt':
    cmd = 'cmd /c "sqlite3 -init db/schema.sql"'
else:
    cmd = "cd db && echo '.save ../src/db/schema.db' | sqlite3 -init schema.sql"

os.system(cmd)
