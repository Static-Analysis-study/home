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

    csv_original = load_config("csv1")
    csv_trans = load_config("csv2")
    output_file = load_config('reportPath')+'/Diff_sonarqube.md'
    path_origin = load_config('testCasePath')

    # Read the original table & transformation table
    table1 = pd.read_csv(csv_original)
    table2 = pd.read_csv(csv_trans)

    grouped1 = table1.groupby('project')
    grouped2 = table2.groupby('project')

    markdown = ''

    # get project
    for project, group1 in grouped1:
        dirs = project.split("_")[0]
        javaname = project.replace(dirs+'_','')+'.java'
        file_origin = path_origin + '/' + dirs + '/' + javaname
        # file_trans = path_trans + '/' + dirs + '/' + javaname
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
                # Here to filter the rules
                if(rule == 'java:S125' or rule == 'java:S1186' or rule == 'java:S1854' or rule == 'java:S1481'): # comment
                    continue
                # if(rule == 'java:S1604' or rule == 'java:S1602'  or rule == 'java:S4065'):  # for lambda
                #     continue
                count2 = rule_counts2[rule]
                if count1 != count2:
                    diff_rows_info.append('- '+rule +' --- table1: '+str(count1)+', table2: '+str(count2)+ '\n')
                    rows1 = group1[group1['rule'] == rule]
                    for _, row in rows1.iterrows():
                        diff_rows.append(('table1', row))
                        append_text = " // @ "+row['rule']+ " --- "+ row["message"]
                        # add_text_to_line(file_origin,row["line"],append_text)
                    rows2 = group2[group2['rule'] == rule]
                    for _, row in rows2.iterrows():
                        diff_rows.append(('table2', row))
                        append_text = " // @ "+row['rule']+ " --- "+ row["message"]
                        # add_text_to_line(file_trans,row["line"],append_text)
                
            # find diff
            if diff_rows:
                markdown += f'# {project}\n\n'
                for item in diff_rows_info:
                    markdown += f'{item}\n'
                markdown += '| Source | Rule | Message | Line |\n'
                markdown += '| ------ | ---- | ------- | ---- |\n'
                for source, row in diff_rows:
                    markdown += f'| {source} | {row["rule"]} | {row["message"]} | {row["line"]} |\n'

    with open(output_file, 'w+') as f:
        f.write(markdown)
