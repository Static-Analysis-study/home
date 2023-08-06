import os

from loadConfig import load_config
from compileJavaCase import compile_java_files

def run_spotbugs(path):
    print("[INFO] Running spotbugs..." + path)
    # 检查路径下是否存在以 .class 结尾的文件
    if not contains_class_files(path):
        print("[INFO] No .class files found in: {}".format(path))
        return

    spotbugs_cmd = "spotbugs -textui -xml:withMessages -output {}/result.xml {}".format(path, path)
    os.system(spotbugs_cmd)


def contains_class_files(path):
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith('.class'):
                return True
    return False


def main():
    path = load_config("testCasePath")
    compile_java_files(path)
    for maindir, subdir, file_name_list in os.walk(path):
        # if len(file_name_list)==2:
        #     continue
        for filename in file_name_list:
            # print(filename)
            if '.java' in filename:
                apath = os.path.join(maindir, filename)
                title = filename.split(".")[0]
                info_path = os.path.join(maindir, title + ".txt")
                print(title,'---',apath,'---',maindir)
                run_spotbugs(maindir)


if __name__ == '__main__':
    main()
