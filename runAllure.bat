set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%\libAllure\aspectjweaver-1.9.8.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAssertJ\*;;%ProjectPath%libAllure\*;%ProjectPath%libLog4Jver2\*;%ProjectPath%libExtent5\*;%ProjectPath%libReportNG\*;%ProjectPath%libSelenium\*;" org.testng.TestNG "%ProjectPath%resources\RunNopCommerceWeb.xml"
allure serve .\allure-json\
pause