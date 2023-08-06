import glob
import json
import csv
import os

from loadConfig import load_config

def extract_violations_to_csv(folder_path, csv_file_name):
    csv_header = ["project", "rule", "ruleset", "description", "beginline", "endline", "externalInfoUrl"]

    with open(csv_file_name, 'w', newline='') as csv_file:
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(csv_header)

        for file_path in glob.glob(os.path.join(folder_path, "*.json")):
            with open(file_path, 'r') as json_file:
                data = json.load(json_file)
                project = os.path.basename(file_path)

                for file in data["files"]:
                    for violation in file["violations"]:
                        row = [project, violation["rule"], violation["ruleset"], violation["description"],
                               violation["beginline"], violation["endline"], violation["externalInfoUrl"]]
                        csv_writer.writerow(row)
    print("CSV out successfuly！")
    

def main():
    folder_path = load_config("result_pmd_path")
    csv_file_name = folder_path +"/../PMD_csv/result.csv"
    extract_violations_to_csv(folder_path, csv_file_name)

if __name__ == '__main__':  
    main()
    