There is no GUI.

Once you cloned the repo make sure to run a maven install on it to install the dependencies.

Also you need to have a chrome version installed and download the fitting ChromeDriver.
You can find the ChromeDrivers in this link:

https://googlechromelabs.github.io/chrome-for-testing/

Your currently installed Chrome Version you can find by pasting this URL into Chrome:

chrome://settings/help

This is because I used Selenium to scrape the data from Flyffipedia.
Once you have downloaded and extracted the ChromeDriver all you need to do is drop the .exe in the resources folder.

When it's set up you just gotta set the "level" variable in the "MainClass" to your desired player level, say your current mains level, and then execute it.


Note: This is purely on a one monster and on paper basis and does not factor in any player specific effects such as strong or weak elements.
A monster portrayed as the highest possible efficiency might not necessarily be the best fit for you.
Monster density and monster count for example are still important factors that should be considered.
