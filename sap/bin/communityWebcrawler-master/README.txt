Sanjiv Kawa
Red Hat

These are the steps to running the program:

Community Crawler:

To compile all source files, dependences and run the community crawler: type "ant CrawlerController"

PullRSS:

PullRSS can only be run after the community crawler has been run as it is dependant on a time stamp located in the HTMLandJSON.db

Once this has been done, to compile all source files, dependences and run the community crawler: type "ant PullRSS"

Compile without run:

To clean, compile all source files and dependencies: simply type ant