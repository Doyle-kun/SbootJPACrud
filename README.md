Hi, technically application works properly. It only won't refresh after pressing Delete button for deleting employee for database. As it was in task it performs CRUD operations. It also prints simple report on new HTML page. Error handling is still SpringBoot default, as it will take time to write own. Also because of that no unit tests and generally TDD. Writing good tests would take about 16-24h (optimistic version) so it was rather impossible. ;) As I had to cut down time I just implemented most important part (Employee CRUD operations).

Techs used: 
Springboot (simplicity - just run jar it has embedded Tomcat server), 
Spring MVC,
JPA (fully cascade relations), 
Thymeleaf for simple frontend (mixed with HTML), 
H2 in-memory database - to save time (no need to setup DB, works everywhere, embedded), 
JAVA EE, 
Maven - because it's usefull tool that keeps dependencies and would be useful for testing with jUnit if there were any tests.
