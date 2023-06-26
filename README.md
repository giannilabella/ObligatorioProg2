# Obligatorio Programación 2
Application project of ADTs in Java for university's programming 2 subject.  
Made in partnership with: @GuzmanVil

## ADTs
- List
- Queue
- Stack
- Binary search tree
- Heap
- Hash table

## Implementation Comments
Dataset files are open once and all data is loaded into the program.  
Drivers are loaded from `drivers.txt` getting their full names and generating a regular expression later used for verifying its presence in a tweet.  
For Users, the data extracted is their name, favorites count and verification status.
Favorites count is updated if happens to exist another instance of the same user with more favorites in the dataset.  
For Tweets, is extracted their content, date, the User who twitted it and the list of hashtags.
The Hashtags are considerate to be case-insensitive.  
Each one of these entities is managed by its own controller, which are also responsible for the reports.

The DriverController is responsible for the 1st report, in which each tweet is checked for the mention of the drivers, keeping track of the number of mentions individually.
And then the most mentioned are displayed.  
The UserController is responsible for the 2nd and 5th, in the first one, the tweets are iterated and the User's tweet count is incremented for every tweet and then sorted in descendant order.
For the second one it just looks for the most favorited users.  
The HashtagController is responsible for 3rd and 4th reports, in the 3rd it iterates over the tweets, keeping track of every different hashtag found in the tweet that matches the day given.
For the 4th the same is done but keeping track also of the number of uses, later ordering the hashtags used in descendant order.  
The TweetController is responsible for the 6th report, in which it just iterates over the tweets looking for the string given, counting every time it's found.  

## Usage
Files `drivers.txt`, `f1_dataset.csv` and `f1_dataset_test.csv` have to be placed at the project's root.  
Then just run the `MainMenu`

## Performance Analysis
Reports performance is analysed with the complete dataset

### Dataset Loading
#### Average loading time:
- Drivers file: 14 ms
- Complete dataset file: 6520 ms
- Reduced dataset file: 999 ms
#### Resources usage when loading complete dataset
![Resources usage complete dataset](docs/load_complete_dataset.png)

#### Resources usage when loading reduced dataset
![Resources usage reduced dataset](docs/load_reduced_dataset.png)

### First Report (Most mentioned drivers)
Month 8 and year 2021 were used.
#### Average time: 2566 ms
#### Resources usage after first execution
![resources usage first execution](docs/report_1_first_execution.png)
#### Resources usage after 10 executions
![resources usage 10 executions](docs/report_1_10_executions.png)
#### Resources usage after GC
![resources usage gc](docs/report_1_gc.png)

### Second Report (Most active users)
#### Average time for first execution: 2600 ms
#### Average time for subsequente execution: 1700 ms
#### Resources usage after first execution
![resources usage first execution](docs/report_2_first_execution.png)
#### Resources usage after 10 executions
![resources usage 10 executions](docs/report_2_10_executions.png)
#### Resources usage after GC
![resources usage gc](docs/report_2_gc.png)

### Third Report (Number of different hashtags)
Day 2021-08-01 was used.
#### Average time: 23 ms
#### Resources usage after first execution
![resources usage first execution](docs/report_3_first_execution.png)
#### Resources usage after 10 executions
![resources usage 10 executions](docs/report_3_10_executions.png)
#### Resources usage after GC
![resources usage gc](docs/report_3_gc.png)

### Fourth Report (Most used hashtag)
Day 2021-08-01 was used.
#### Average time: 33 ms
#### Resources usage after first execution
![resources usage first execution](docs/report_4_first_execution.png)
#### Resources usage after 10 executions
![resources usage 10 executions](docs/report_4_10_executions.png)
#### Resources usage after GC
![resources usage gc](docs/report_4_gc.png)

### Fifth Report (Most favorited users)
#### Average time: 1800 ms
#### Resources usage after first execution
![resources usage first execution](docs/report_5_first_execution.png)
#### Resources usage after 10 executions
![resources usage 10 executions](docs/report_5_10_executions.png)
#### Resources usage after GC
![resources usage gc](docs/report_5_gc.png)

### Sixth Report (Number of tweets with a specific word or phrase)
String F1 was used (case-insensitive mode).
#### Average time: 308 ms
#### Resources usage after first execution
![resources usage first execution](docs/report_6_first_execution.png)
#### Resources usage after 10 executions
![resources usage 10 executions](docs/report_6_10_executions.png)
#### Resources usage after GC
![resources usage gc](docs/report_6_gc.png)