# 1420-Final-Project
The final project for ENGG1420.
I completed this project with four other members, This is my first ever group project and it was very interesting and insightful.

The code is a File Processing System, where the system takes a File Processing Scenario as input, and then executes this scenario. 

CODE LAYOUT:

1.The application starts execution from Application.java, which calls ScenarioProcessor.ParseAndExecute.

2.ScenarioProcessor parses the input and creates scenarios and processing elements.

3.Each Scenario might contain a list of ProcessingElement instances that process Entry instances in the scenario.

4.Different types of processing elements (Filter, List, Print, Rename, Split) perform specific processing on the entries.

5.Entries can be of different types (Local, Remote) and are processed accordingly by the processing elements.
