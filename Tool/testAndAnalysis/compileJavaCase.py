import os
import subprocess
from loadConfig import load_config

report_file = "spotbugs_uncompiled_report.txt"

spotbugsEnv = load_config("spotbugsEnv")
spotbugscaseClasspath = load_config("spotbugscaseClasspath")

javac_cmd = "javac -classpath "+spotbugsEnv+"/build/distributions/spotbugs-4.7.4-SNAPSHOT/lib/spotbugs-annotations.jar:"+spotbugsEnv+"/spotbugsTestCases/src/fakeAnnotations:"+spotbugsEnv+"/build/distributions/spotbugs-4.7.4-SNAPSHOT/lib/jsr305-3.0.2.jar:"+spotbugsEnv+"/spotbugs-lib/guava-30.1-jre.jar:"+spotbugscaseClasspath+" -d {output_dir} {java_file}"

def compile_java_files(path):
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith(".java"):
                java_file_path = os.path.join(root, file)
                output_dir = os.path.dirname(java_file_path)
                javac_command = javac_cmd.format(output_dir=output_dir, java_file=java_file_path)

                try:
                    # compile java file
                    subprocess.run(javac_command, shell=True, check=True)
                    print(f"Compiled: {java_file_path}")
                except subprocess.CalledProcessError:
                    # if compile failed, write the file path to report file
                    with open(report_file, "a") as f:
                        f.write(java_file_path + "\n")
                    print(f"Failed to compile: {java_file_path}")

def clear_report_file():
    with open(report_file, "w") as f:
        f.write("")

clear_report_file()
base_path = ""
compile_java_files(base_path)
