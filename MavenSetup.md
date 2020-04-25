# Setting Up a Java Maven Project in VSCode 

Restating [these](https://stackoverflow.com/questions/40831703/visual-studio-code-java-classpath-is-incomplete-only-syntax-errors-will-be-re) instructions from StackOverflow for clarity and for purposes of having a hard copy. 

## Required 
* Java extension in VSCode 

## Setup 
1. Check if Maven is installed 

   * Enter into terminal: 
        ```
        mvn --version
        ```
   * If Maven is not installed, the output will suggest installation command 
2. Invoke the Maven quickstart archetype to generate new project in the directory of your choice: 
    * Enter into terminal: 
    ```
    mvn archetype:generate 
    ```
    * Hit `enter` to choose `maven-archetype-quickstart` 
    * Choose a version from the list. The suggested version is displayed. 
    * Choose a value for property groupId: 
    ``` 
    com.user.app
    ```
    * Define a value for artifactId (this creates the folder in the home/current directory): 
    ```
    my-app
    ```
    * Define value for version: `1.0` 
    * Define value for property package: `com.user.app` or whatever you want your first package to be called 
    * Confirm options 
3. Start VSCode with the new project 
    * If you created the directory in home, enter into terminal: 
    ```
        code ./my-app 
    ```

    * else type in 
    
    ```
    cd directory-of-project
    code my-app 
    ```
4. Configure a launch.json file    
    * Click on the Debugger on the left-hand menu 
    * On the left, click on the blue link that says `To customize Run and Debug create a launch.json file.`
    * Choose `Java` for the language in the drop-down menu


5. Configure a Task.json    
    * Go to the top menu bar and select `Terminal > Configure Tasks...`
    * Select `Create tasks.json file from template`
    * Select `maven` from the drop-down list and the json will be automatically generated 