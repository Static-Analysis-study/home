import json , requests, pprint
import csv
import os

from loadConfig import load_config
def append_to_csv(file_path, data):
    if not os.path.exists(file_path):
        with open(file_path, 'w', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(['project', 'component', 'rule', 'message', 'type', 'line'])

    with open(file_path, 'a', newline='') as f:
        writer = csv.writer(f)
        if 'line' in data:
            line = data['line']
        else:
            line = ''
        writer.writerow([data['project'], data['component'], data['rule'], data['message'], data['type'], line])

def get_result(projectKey,link,token,filename):
    load_config("result_sonarqube_path")
    csv_path = './sonarqube_report_csv/'+filename+'.csv'
    url = link + '/api/issues/search?componentKeys=' + projectKey
    myToken = token

    session = requests.Session()
    session.auth = myToken, ''

    call = getattr(session, 'get')
    res = call(url)
    print(res.status_code)

    binary = res.content
    output = json.loads(binary)
    # pprint.pprint(output)

    issues = output['issues']
    for item in issues:
        project = item['project']
        component = item['component']
        rule = item['rule']
        message = item['message']
        theType = item['type']
        # print("-------------------")
        # print(project)
        # # print(component)
        # print(rule)
        # print(message)
        # print(theType)
        append_to_csv(csv_path,item)
        
        
# def main():
#     get_result()
