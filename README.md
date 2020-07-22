# ZipLookup

This program uses a file of ZIP code information found at the opendatasoft.com website (see link below).
The program allows a user to find ZIP codes within a certain distance of another ZIP code. User entry is via the command line.

Specific file download (CSV): 
https://public.opendatasoft.com/explore/dataset/us-zip-code-latitude-and-longitude/download/?format=csv&timezone=America/Los_Angeles&lang=en&use_labels_for_header=true&csv_separator=%3B

Clone the files then simply run the class file:
$ java ZipLookup

or complile yourself. Make sure the CSV fiel stays in the same folder.

User input is on the command line. It assumes proper entry. Formats for entry are a 5-digit zipcode and miles:
````
zipcode$ java ZipLookup
Welcome to the ZIP code database.
Enter a 5-digit ZIP code and a proximity,
and this program will calculate where
that ZIP code is located, along with
a list of other ZIP codes with
the given proximity.

What ZIP code are you interested in? 82003
And what proxomity (in miles)? 30
    82009  Cheyenne WY, 12.15 miles
    80612  Carr CO, 25.51 miles
    82005  Fe Warren AFB WY, 11.85 miles
    82054  Carpenter WY, 19.79 miles
    82003  Cheyenne WY, 0.00 miles
    82050  Albin WY, 28.39 miles
zipcode$
````
