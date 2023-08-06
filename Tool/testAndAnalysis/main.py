import argparse
import importlib

def main():
    parser = argparse.ArgumentParser(description='Run Python scripts')
    parser.add_argument('-f', '--file', type=str, required=True, help='Name of the file to run')
    args = parser.parse_args()

    script_name = args.file.replace('.py', '')
    try:
        script = importlib.import_module(script_name)
        script.main()  
    except ModuleNotFoundError:
        print(f"Error: {script_name}.py not found or main() function not defined.")

if __name__ == '__main__':
    main()
