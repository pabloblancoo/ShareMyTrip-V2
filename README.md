# ShareMyTrip 

## Descripcion

Proyecto para la asignatura Sistemas Distribuido e Internet.

La idea del proyecto era hacer un programa similar a 'BlaBlaCar', en el que podias apuntarte a un viaje, y el creador del viaje podíra aceptar o denegar tu plaza en el viaje.

Existen varios proyectos, cada uno con una funcionalidad diferente.

## sdi3-12-EJB
Contiene toda la logica de negocia. y los EJBs correspondientes.

Existe una clase "Factories", que da acceso a dos factorias, una para la logica, y otra para la persistencia.

Se utilizan EJBs para cada modelo de la BD, que estan implementados en la capa business, y a los cuales se accede a través de la interfaz "ServiceFactory", que da acceso a todos las interfaces de los Service.

Cada modelo de la BD tiene un "Service", que proporciona un servicio web.

## sdi3-12-WEB

Proyecto que contiene la interfaz web.

Utiliza Java Beans para el intercambio de datos entre la logica, y la presentacion.

Contiene una interfaz "ServiceRest" que proporciano acceso a traves de servisios REST a determinadas partes del proyecto. La implementacion de dichos metodos se hace en la clase "ServiceRestImpl" que accede a la logica proporcionada por "sdi3-12-EJB".

Proporciona tambien un Listener llamado "ShareMyTripListener", para que el servidor reciba mensajes de los clientes. Estos mensajes se almacenan en una cola, se procesan, y son enviados a los clientes que haga falta a traves de un 'topic', 

## sdi3-12
Proyecto general EAR que contiene a 'sdi3-12-EJB' y 'sdi3-12-WEB'

## sdi3-12-Cli-EJB

Es un cliente que accede a traves de los EJBs a la logica de la aplicacion.

## sdi3-12-Cli-MSG

Es un cliente web que permite conectarse a un grupo ( personas que van en el mismo viaje) y enviar mensajes para que lo reciban todos los participantes. Estos mensajes se envian a la cola de mensajes del servidor, los procesa, y los devuelve a los distintos usuarios. 
Cada cliente se conecta a un 'topic' que es una cola 'publish-suscribe' ,es decir, recibe los mensajes del grupo en el que se ha conectado.
Implementa un Listener llamado "ClienteListener" que se encarga de recibir los mensajes, y filtrarlos para mostrarselo al usuario solo si pertenece a ese grupo.

## sdi3-12-Cli-REST

Es un cliente que accede a una determinada parte proyecto a traves de servicios REST.

## sdi3-12-Cli-SOAP

Es un cliente que accede a una determinada parte proyecto a traves de servicios .

## sdi3-12-test
Proyecto que contienes los test web con la tecnologia Selenium

## Desarrolladores

* <a href ="https://github.com/pabloblancoo">Pablo Blanco Pacho</a>
* <a href ="https://github.com/SantiMA10">Santiago Martin Agra</a>

## Links de interes
* [ShareMyTrip v1](https://github.com/SantiMA10/ShareMyTrip-v1)
