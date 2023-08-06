import os
import pandas as pd

from loadConfig import load_config

def compare_csv_files(csv_file1, csv_file2):
    table1 = pd.read_csv(csv_file1)
    table2 = pd.read_csv(csv_file2)

    if len(table1) == len(table2):
        return None

    rule_counts1 = table1['rule'].value_counts().to_dict()
    rule_counts2 = table2['rule'].value_counts().to_dict()

    diff_rules = []
    for rule, count1 in rule_counts1.items():
        count2 = rule_counts2.get(rule, 0)
        if count1 != count2:
            diff_rules.append(rule)

    if diff_rules:
        diff_rows1 = table1[table1['rule'].isin(diff_rules)]
        diff_rows2 = table2[table2['rule'].isin(diff_rules)]
        return diff_rows1, diff_rows2

    return None
    
folder_path1 = load_config('spotbugsCSV1')
folder_path2 = load_config('spotbugsCSV2')
markdown_path = load_config('reportPath')+'/Diff_spotbugs.md'

folder_path = load_config('testCasePath')

markdown = ''

for folder_name in os.listdir(folder_path1):
    print(folder_name)
    if not folder_name.isdigit():
        continue

    csv_file1 = os.path.join(folder_path1, folder_name, 'result.csv')
    csv_file2 = os.path.join(folder_path2, folder_name, 'result.csv')

    if not os.path.isfile(csv_file1) or not os.path.isfile(csv_file2):
        continue

    diff_rows = compare_csv_files(csv_file1, csv_file2)
    if diff_rows:
        markdown += f'# {folder_name}\n\n'
        markdown += '## Table1\n\n'
        markdown += diff_rows[0].to_markdown(index=False) + '\n\n'
        markdown += '## Table2\n\n'
        markdown += diff_rows[1].to_markdown(index=False) + '\n\n'

if markdown:
    with open(markdown_path, 'w') as f:
        f.write(markdown)
        

