import os
import sys
import subprocess
import requests

from loadConfig import load_config
                
def run_sonarqube(projectName,sourcePath,link,token):
    # SonarScanner command
    cmd = ['sonar-scanner', '-Dsonar.projectKey='+projectName, '-Dsonar.sources='+sourcePath,'-Dsonar.projectBaseDir='+sourcePath, '-Dsonar.host.url='+link,'-Dsonar.login='+token]
    result = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    print(result.stdout.decode('utf-8'))
    print(result.stderr.decode('utf-8'))
    return 0

def create_project(projectName,projectKey,link,token):
    server_url = link
    api_path = '/api/projects/create'
    myToken = token
    session = requests.Session()
    session.auth = myToken, ''
    # project information
    params = {'name': projectName, 'project': projectKey}
    call = getattr(session, 'post')
    response = call(server_url + api_path,params=params)
    if response.status_code == 200:
        return 1
        print(' Project created successfully')
    else:
        return 0
        print(' Failed to create project: ' + response.text)

def delete_project(projectKey,link,token):
    server_url = link
    api_path = '/api/projects/delete'
    myToken = token
    session = requests.Session()
    session.auth = myToken, ''

    params = {'project': projectKey}
    call = getattr(session, 'post')
    response = call(server_url + api_path,params=params)
    print(response)
    if response.status_code == 200 or response.status_code == 204:
        print('Project deleted successfully')
    else:
        print('Failed to delete project: ' + response.text)

    