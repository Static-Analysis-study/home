import os
import subprocess
from Sonarqubeapi import create_project
from Sonarqubeapi import delete_project
from Sonarqubeapi import run_sonarqube
from getSonarqubeResult import get_result

from loadConfig import load_config
                
def get_sonarqube_testfile():
    sonarqube_case_Path = load_config("testCasePath")
    token = load_config("sonarqubeToken")
    link = load_config("sonarqubeLink")
    result_filename = "sonarqubeResult"
    
    # os.walk(0) for every DIR and FILE
    for maindir, subdir, file_name_list in os.walk(sonarqube_case_Path):
        for filename in file_name_list:
            # print(filename)
            if '.java' in filename:
                apath = os.path.join(maindir, filename)
                title = filename.split(".")[0]
                info_path = os.path.join(maindir, title + ".txt")
                print(title,'---',apath,'---',maindir)
                parentDir = maindir.split('/')[-1]
                projectName = parentDir+"_"+title
                projectKey = projectName+"_key"
                print(projectName,maindir)
                run_sonarqube(projectName,maindir,link,token)
                createStatus = create_project(projectName,projectKey,link,token)
                if createStatus == 1:
                    print(projectName + "project created!")
                    
                # delete_project(projectKey,link,token)
                get_result(projectName,link,token,result_filename)
                

def main():
    get_sonarqube_testfile()
    return 0

if __name__ == '__main__':  
    main()
