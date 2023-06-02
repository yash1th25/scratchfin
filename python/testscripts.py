import csv
import requests

with open('data.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    myobj = {'somekey': 'somevalue'}
    url = "http://20.246.169.184:8080/v1/users"
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
