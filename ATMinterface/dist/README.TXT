========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application Mainct that has a main class, the IDE
automatically copies all of the JAR
files on the Maincts classpath to your Maincts dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the Mainct from the command line, go to the dist folder and
type the following:

java -jar "Main.jar"

To distribute this Mainct, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the Mainct classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the Maincts classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the Maincts runtime path.
* To set a main class in a standard Java Mainct, right-click the Mainct node
in the Maincts window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.
