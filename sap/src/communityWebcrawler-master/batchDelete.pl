#!/usr/local/bin/perl -w

print ("Starting the batch delete program now \n");

$file = "communityCrawlerStorage";
$file2 = "all databases";
$file3 = "thread.xml";
$file4 = "build";

if(-e $file)
{
	print("\nDo you want to delete ",  $file , "? \n");
	
	chomp ($input=<>);
	
	if($input eq "y" || $input eq "yes") 
	{
		system("rm -r communityCrawlerStorage");
		print($file ," has been deleted \n");
	}
	else
	{
		print($file ," has not been deleted \n");
	}
}


if(-e "HTMLandJSON.db")
{
        print("\nDo you want to delete ",  $file2 , "? \n");

        chomp ($input=<>);

        if($input eq "y" || $input eq "yes")
        {
                system("rm -r *.db");
                print($file2 ," has been deleted \n");
        }
        else
        {
                print($file2 ," has not been deleted \n");
        }
}

if(-e $file3)
{
        print("\nDo you want to delete ",  $file3 , "? \n");

        chomp ($input=<>);

        if($input eq "y" || $input eq "yes")
        {
                system("rm -r thread.xml");
                print($file3 ," has been deleted \n");
        }
        else
        {
                print($file3 ," has not been deleted \n");
        }
}

if(-e $file4)
{
        print("\nDo you want to delete ",  $file4 , "? \n");

        chomp ($input=<>);

        if($input eq "y" || $input eq "yes")
        {
                system("rm -r build");
                print($file4 ," has been deleted \n");
        }
        else
        {
                print($file4 ," has not been deleted \n");
        }
}
