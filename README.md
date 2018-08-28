# springfox swagger demo project 


this repo demonstrates the issue described at https://github.com/springfox/springfox/issues/2573

We have the requirement to put all swagger documentation beneath a fix path. This first sounds close to an answer already given in How does one configure swagger-ui for non-springboot applications?. But the answer given there is to add some redirects to make it all work, but this is not going to work when you really only want to give explicit permission for one specific path e.g. /documentation/**. we don't care about the stuff after the path prefix, but it must all be beneath it - no redirects outside this path.


The current code allows me to redefine the RequestMapping for ApiResourceController. The startup works fine, and the UI stuff can also be loaded - but unfortunate these requests fail and I have no idea how to get them to work:

* http://localhost:8080/documentation/v2/api-docs?group=myapi
* http://localhost:8080/documentation/csrf
* http://localhost:8080/documentation/

Also setting springfox.documentation.swagger.v2.path=/documentation/v2/api-docs does not help, it makes it even worse, as the it now tries to access http://localhost:8080/documentation/documentation/v2/api-docs?group=myapi


