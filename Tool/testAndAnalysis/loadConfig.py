import json

def load_config(key):
    config_path = './config.json'
    with open(config_path, 'r') as config_file:
        config = json.load(config_file)
        
    if key in config:
        return config[key]
    else:
        print("Error: key %s not found in config.json" % key)



