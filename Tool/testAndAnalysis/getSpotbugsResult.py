import os
import csv
import xml.etree.ElementTree as ET

from loadConfig import load_config

def process_xml_file(xml_file, csv_file):
    tree = ET.parse(xml_file)
    root = tree.getroot()

    bug_instances = root.findall(".//BugInstance")
    if bug_instances is None:
        writer = csv.writer(file)
        writer.writerow(["id", "rule", "category", "LongMessage", "ShortMessage", "start", "end"])

    with open(csv_file, mode='w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(["id", "rule", "category", "LongMessage", "ShortMessage", "start", "end"])

        for idx, bug_instance in enumerate(bug_instances, start=1):
            rule = bug_instance.get("type")
            category = bug_instance.get("category")
            long_message = bug_instance.find("LongMessage").text
            short_message = bug_instance.find("ShortMessage").text

            start_line = -1
            end_line = -1

            source_lines = bug_instance.findall(".//SourceLine")
            if source_lines is not None and len(source_lines) > 0:
                for source_line in source_lines:
                    if source_line.get("start") is not None:
                        start_line = source_line.get("start")
                    if source_line.get("end") is not None:
                        end_line = source_line.get("end")

            writer.writerow([idx, rule, category, long_message, short_message, start_line, end_line])
        print("Finished processing {}.".format(xml_file))

def process_folder(folder_path):
    for root, dirs, files in os.walk(folder_path):
        for file in files:
            if file.endswith(".xml"):
                xml_file = os.path.join(root, file)
                csv_file = os.path.join(root, "result.csv")
                print("Processing {}...".format(xml_file))
                process_xml_file(xml_file, csv_file)


folder_path = "/data/xml-exp/project/SA_code_and_report/testCase/case_Spotbugs/spotbugs_addThis"
process_folder(folder_path)
