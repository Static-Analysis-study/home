import pandas as pd

from loadConfig import load_config

def add_text_to_line(filename, line_number, text):
    line_number = int(line_number)
    with open(filename, 'r') as f:
        lines = f.readlines()
    for i, line in enumerate(lines):
        if i == line_number:
            lines[i] = line.rstrip() + text + '\n'
    with open(filename + '_new', 'w') as f:
        f.writelines(lines)
        
def main():
    # need to define
    csv_original = load_config("csv1")
    csv_trans = load_config("csv2")
    folder_path = load_config('testCasePath')
    output_file = load_config('reportPath')+'/Diff_pmd.md'

    table1 = pd.read_csv(csv_original)
    table2 = pd.read_csv(csv_trans)

    grouped1 = table1.groupby('project')
    grouped2 = table2.groupby('project')

    markdown = ''

    for project, group1 in grouped1:
        if project in grouped2.groups:
            group2 = grouped2.get_group(project)
            rules = set(group1['rule']).union(set(group2['rule']))
            rule_counts1 = {rule: 0 for rule in rules}
            rule_counts2 = {rule: 0 for rule in rules}
            
            for rule in group1['rule']:
                rule_counts1[rule] += 1
            for rule in group2['rule']:
                rule_counts2[rule] += 1
                
            diff_rows = []
            diff_rows_info = []
            for rule, count1 in rule_counts1.items():
                if rule == "AvoidFieldNameMatchingMethodName":
                    continue
                count2 = rule_counts2[rule]
                if count1 != count2:
                    diff_rows_info.append('- '+rule +' --- table1: '+str(count1)+', table2: '+str(count2)+ '\n')
                    rows1 = group1[group1['rule'] == rule]
                    for _, row in rows1.iterrows():
                        diff_rows.append(('table1', row))
                    rows2 = group2[group2['rule'] == rule]
                    for _, row in rows2.iterrows():
                        diff_rows.append(('table2', row))

            if diff_rows:
                markdown += f'# {project}\n\n'
                for item in diff_rows_info:
                    markdown += f'{item}\n'
                markdown += '| Source | Rule | description | beginline | endline |\n'
                markdown += '| ------ | ---- | ----------- | --------- | ------- |\n'
                for source, row in diff_rows:
                    markdown += f'| {source} | {row["rule"]} | {row["description"]} | {row["beginline"]} | {row["endline"]} |\n'

    with open(output_file, 'w+') as f:
        f.write(markdown)
