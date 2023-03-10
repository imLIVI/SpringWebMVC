# SpringWebMVC
## Migration
### Description
Create a new project based on Spring MVC and Embed Tomcat and transfer the functionality implemented in <a href="https://github.com/imLIVI/Servlets-Containers.git">
  previous homework</a> assignments.
  
## The data is not deleted*
### Description
The worst thing that can be done with user data is to permanently delete it. Users always call later with a request to restore them and claim that they definitely did 
not delete anything themselves. Therefore, most often user data is not deleted, but marked for deletion, i.e. some field is added, for example, ```removed```.

For simplicity, we will assume that ```/api/posts``` is an API for clients where they will not be able to restore deleted posts and even view deleted posts. There will
be a separate API for this later.

It turns out that removeById only exposes this field. The work of the other methods is also changing dramatically:

* ```all``` returns all posts except those with the removed flag set;
* ```getById``` returns the post only if it does not have the removed flag set, otherwise it throws a ```NotFoundException*```;
* ```save``` updates an existing post only if it does not have the removed flag set, otherwise it throws a ```NotFoundException*```.

Note.* There is no perfect solution here, different people can tell you that it is possible or it is impossible, etc. We will only say that any categoricality is always
bad, and you should understand that different options are possible. It all depends on what decision the API designer or the team will make.

The only question remains with the status codes. Logically, NotFoundException should lead to the 404 status code. Study the documentation on <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html">
  @ResponseStatus</a> and think about how to use it to set the 404 status code when the Exception we specified occurs.

Hint.
You need to use it in the format ```@ResponseStatus(code = HttpStatus.NOT_FOUND)```, while importing both ```ResponseStatus``` and ```HttpStatus```.

<a href="https://github.com/netology-code/jspr-homeworks/tree/master/06_mvc">(RUS version of description)</a>

