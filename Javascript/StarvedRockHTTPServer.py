#!/usr/bin/env python
import sys
import json
import ast
from pprint import pprint
from urlparse import parse_qs
from optparse import OptionParser
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer

FORMATS = ('html','json','raw', 'text')
format = FORMATS[0]
keys = ['notifications', 'locations']
ipaddress = '192.168.2.17'

class Handler(BaseHTTPRequestHandler):
	#This function handles foreign HTTP requests (other than POST, GET, etc)
	#Allows GET, POST, and OPTIONS headers
	#Function redirects to the process_request function
	def do_OPTIONS(self):
		self.send_response(200, "ok")       
		self.send_header('Access-Control-Allow-Origin', '*')                
		self.send_header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS')
		self.send_header("Access-Control-Allow-Headers", "X-Requested-With")  
		self.process_request()

    #This function handles GET HTTP requests
    #In this function:
    #	A file is opened from the current directory
    #	The file is stored as a series of lines
    #	This collection of lines is converted to a JSON variable
    #	The variable is delivered to the client who sent the request
	def do_GET(self):
		#Responds to the client with an 'HTTP Okay - 200'
		self.send_response(200)
		#Send the appropriate headers
		#	NOTE: It is crucial that Access-Control-Allow-Origin is set to '*'
		self.send_header('Access-Control-Allow-Origin', '*')
		self.send_header('Content-type','text/json')
		self.end_headers()
		full_json_dict = {}
		#Stores all keys in a single dictionary
		#Sends the dictionary as "data" to the client
		for key in keys:
			full_json_dict[key] = import_json(key)
		str_json_dict = '{"data":['
		for key in keys:
			str_json_dict += str(full_json_dict[key])
		str_json_dict += "]}"
		str_json_dict = str_json_dict.replace("}{", "},{")
		self.wfile.write(str_json_dict)
		self.wfile.close()

	#This function handles POST HTTP requests
	#In this function:
	#	A JSON variable is sent to us (the server) from the client
	#	The variable is converted to a string
	#	The appropriate header (notification, location, etc) is added to the string
	#	The appropriate file is opened for writing
	#	The file is erased
	#	The aformentioned string is written to the file
	def do_POST(self):
		#Set Headers and Response
		#Read the Content Length
		#Read the message from the HTTP request
		self.send_response(200, "ok")
		self.send_header('Access-Control-Allow-Origin', '*')
		content_len = int(self.headers.getheader('Content-Length', 0))
		main_post_body = self.rfile.read(content_len)

		post_body = main_post_body

		#Parse the message to correct errors
		#Converts the message to a dictionary
		dict_post_body = parse_qs(post_body[:])

		#Get the key of the dictionary
		#This identifies which file to use and how to package the data
		key = "NULL"
		if len(dict_post_body) > 1:
			print "Error: Too many values received"
			return
		else:
			for dkey in dict_post_body:
				print dkey
				key = dkey

		#Removing the first two and last two characters from the data
		str_post_body = str(dict_post_body[key])[2:-2]
		#Convert post_body to a literal list
		JSON_post_body = ast.literal_eval(str_post_body)

		output_string = ""
		for json_dict in JSON_post_body:
			output_string += str(json.dumps(json_dict))
		output_string = output_string.replace('}{', '},{')
		export_json(key, output_string)

	#This function processes HTTP requests if they are not formatted properly
	def process_request(self):
		return True

def import_json(key):
	try:
		#Safely open the file for reading based on the given key
		with open(key + ".json", "r") as f:
			print "opening file"
			lines = f.readlines()
			jsonstr = ''
			for line in lines:
				jsonstr += line
			f.close()

			jsonstr = jsonstr.replace("}{", "},{")
			jsonstr = json.dumps(json.loads(jsonstr))
			return jsonstr
	except:
		print key + " file is empty or doesn't exist."

def export_json(key, data):
	final_str = '{"' + key + '":[' + data + ']}'
	try:
		with open(key + ".json", "w") as f:
			f.write(final_str)
			f.close()
	except:
		print "Error: Couldn't open file"

def run(port=8000):
	print('http server is starting...')
	server_address = (ipaddress, port)
	httpd = HTTPServer(server_address, Handler)
	print('http server is running...listening on port %s' %port)
	httpd.serve_forever()
	#import json for all files at start

if __name__ == '__main__':
	op = OptionParser(__doc__)

	op.add_option("-p", default=8000, type="int", dest="port", 
	              help="port #")
	op.add_option("-f", default='json', dest="format", 
	              help="format available %s" %str(FORMATS))
	op.add_option("--no_filter", default=True, action='store_false', 
	              dest="filter", help="don't filter")

	opts, args = op.parse_args(sys.argv)

	format = opts.format
	#Doesn't make sense #import_success = import_json_to_dict('datastore.json')
	run(opts.port)