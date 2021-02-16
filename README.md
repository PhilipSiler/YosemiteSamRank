# YosemiteSamRank
Pulls Yosemite Sam words from a text file and compares them to a list of English words in order of frequency, then prints the output to screen and to a local .txt file via PrintStream.

First, the program opens a series of Files with Scanners, one for the list of Sam quotes, one for the list of stopwords, one for word frequency. The program also creates a file (SamRank.txt) which will ultimately be where the ranked word list is printed to, along with the console.

Second, the program runs a Scanner through the stopword text file and creates a LinkedList object of all of the stopwords. The stopwords.txt file was already formatted and did not need to be modified or made parseable.

Third, the program runs a Scanner through the Sam quote text file (each line is a single quotation), takes each line, makes it lower case, strips extraneous punctuation and splits it into an array around regular expression " ". If an individual element of the array created by splitting the quote is not found in the stopwords list and is not already found in the quotes list, then it is added to the quotes.

Fourth, the program runs a Scanner through the ranked word list, and (while the quote list is not empty) checks if the quote word list contains the word currently scanned on the ranked word list. If so, the program creates a rankedPairs object and adds the word and its attendant ranking (there is an int counter that keeps track of ranking during the execution of the while loop, the ranked word list IS ordered by frequency but does NOT have the frequency as a value) to the rankList.

Fifth, the program reverses the rankList so that the most interesting and rarest words will be printed first.

Sixth, the program uses a while loop with removeFirst (removeLast could also have been used, and step 5 could have been skipped but I prefer this way) to create String objects which read something like "trustin is the 328921 most common word in the English language." The Strings are printed to the console, as well as the waiting SamRank.txt file.

Finally, the program closes the Scanners and the PrintStream object.
