import os
import subprocess

from loadConfig import load_config

def run_pmd(title,file_path,info_path,maindir):
    # run.sh pmd -f text -R category/java/performance.xml -d bufferAppend1.java
    print("-"*40)
    jsonPath = load_config("testCasePath") + '/../JSON/' + str(maindir.split("/")[-1]) + ".json"
    print(jsonPath)
    command = 'pmd check -f json -r %s -R pmd_test_rules.xml -d %s'%(jsonPath,file_path)
    print("Now testing pmd command: %s"%command)
    os.system(command)
    return 0

def get_testfile(path):
    # os.walk(0) for every DIR and FILE
    for maindir, subdir, file_name_list in os.walk(path):
        for filename in file_name_list:
            # print(filename)
            if '.java' in filename:
                apath = os.path.join(maindir, filename)
                title = filename.split(".")[0]
                info_path = os.path.join(maindir, title + ".txt")
                print(title,'---',apath,'---',maindir)
                run_pmd(title,apath,info_path,maindir)

def main():
    path = load_config("testCasePath")
    print(path)
    get_testfile(path)

if __name__ == '__main__':  
    main()