import csv
import requests
import os
database_url = os.environ.get('DATABASE_URL', 'python_database_scripts/data.csv')
csvurl = os.environ.get('CSV_URL', 'http://74.64.123.44:8080/v1/users')

with open(database_url) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    url = csvurl
    for row in csv_reader:
        if line_count == 0:
            line_count += 1
            continue;
        else:
            new_id = int(row[0]);
            name = row[1];
            payload = {"id" : new_id, "name": name}

            #post call
            x = requests.post(url, json = payload);
    print(x)
