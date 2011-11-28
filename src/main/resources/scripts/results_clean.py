import csv
import sys
from optparse import OptionParser

usage = "usage: %prog [-i] input_file"
parser = OptionParser(usage=usage)
parser.add_option("-i", "--input", dest="input_file", help="read input from FILE", metavar="FILE")
(options, args) = parser.parse_args()

if options.input_file is None:
   parser.error("Input file is not specified")

ifile  = open(sys.argv[2], "rb")
reader = csv.reader(ifile)
#ofile  = open(sys.argv[4], "wb")
#writer = csv.writer(ofile, delimiter=',')

rownum = 0
headers = []
for row in reader:
   answers = []
   if rownum == 0:
	  header = row
   else:
	  colnum = 0
	  for col in row:
		 #print '%s: %s' % (header[colnum], col)
		 if	( 
			   header[colnum] == "Answer.Level0" or 
			   header[colnum] == "Answer.Level1" or 
			   header[colnum] == "Answer.Level2" or 
			   header[colnum] == "Answer.Description" or 
			   header[colnum] == "Answer.imageLoad" or 
			   header[colnum] == "Answer.quesLoad" or 
			   header[colnum] == "Answer.title" 
			   
			):
			answers.append(`col`)
		 colnum += 1
   result_row = ",".join(answers) 
   print result_row + "\n" 
   #writer.writerow(result_row + "\n")
   rownum += 1
ifile.close()
