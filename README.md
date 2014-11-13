hadoop-definitive-guide
=======================

Maven setting.xml
```xml
<server>
	<id>ssh-hadoop</id>
	<username>cloudera</username>
	<password>cloudera</password> <!-- not needed if using pageant -->      
  	<configuration>
    	<sshExecutable>ssh</sshExecutable>
    	<scpExecutable>scp</scpExecutable>
    	<scpArgs>trust true</scpArgs>        
  	</configuration>
</server>
```
