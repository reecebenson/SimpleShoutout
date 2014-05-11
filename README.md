SimpleShoutout V1.0
==============
######**What is SimpleShoutout?**  
*SimpleShoutout is an easier way of allowing your users to broadcast their YouTube channel on the server which can be set up with BuyCraft. To set it up with BuyCraft it takes 4 easy steps.*
* * *
######**How to Setup SimpleShoutout**
- Start off with making the BuyCraft Package
- Create 2 variables: "{yt_ext}" and "{yt_pre}"
- "{yt_ext}" should be setup as an Alphanumeric Variable, from 0 to 50 characters with colour codes disabled.
- "{yt_pre}" should be setup as a Dropdown Variable, with 2 values. "user" and "channel" at the price of 0.00
- Edit your Package again and add the 2 variables to it. Now for the Commands, add the following: "channelshout {name} http://youtube.com/{yt_pre}/{yt_ext}"  

* * *
######**[Version 1.0]**
- Stopped users/ops from being able to type /channelshout in-game [console command only]
- Command parses the YouTube URL to check for /user/ or /channel/ - returns null if these extensions don't exist
- Made it so once the shoutout has been processed the chat is temporarily muted for 5 seconds to avoid the shoutout disappearing