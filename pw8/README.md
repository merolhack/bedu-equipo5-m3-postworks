# Buenas prácticas

  Durante el desarrollo de los diferentes PostWorks fuimos realizando las actividades e intentamos seguir las siguientes practicas: 
  
  1.-Uso adecuado de los nombres para variables, métodos, clases y funciones.
  
  2.-DRY, Don't Repeat your Selt. 
  
  3.-KISS Keep It Easy Stupid.
  
  4.-Uso adecuado del controlador de versiones.
  
  5.-Clean your self, Limpiate tu mismo, es decir si creas una variable o rutina, eres responsable
   de los objetos que creas y debes destruirlos al terminar la ejecución.
  
  6.-Realizar pruebas unitarias antes de subir el código al repositorio. 
  
 
 Sin embargo en la practica nos encontramos con algunos detalles como lo son los siguientes:
  
  ### 1.-Spanglish en comentarios y en nombres de funciones, los cuales pasaron al repositorio.
  
  ![spanglish01](https://user-images.githubusercontent.com/80803850/236375370-eb425fb3-176f-4eb9-bae0-049fb6fc1c0a.png)
 
  ### 2.-No siempre se liberaban la memoria al terminar cada método.
  
  ![Objetos no Borrados](https://user-images.githubusercontent.com/80803850/236375755-22e1a9a2-821b-4257-86c8-7cdf6a7c51d8.png)
  
  Aunque la maquina virtual de Java tiene un buen garbage collector, es mejor si nosotros le damos una
  ayuda, y aparte nos permite ser más consiente de los objetos que creamos, en el código de la imagen anterior, instanciamos los objetos pero al final nunca los asignamos a NULL para liberar la memoria que usamos.
  
  ### 3.-Uso adecuado del Controlador de Versiones
  
  Una buena practica que si llevamos cabalmente, ya que el repositorio se configuro así desde el
  principio fue el uso adecuado del repositorio GIT, esto en ocasiones impidió subir código con 
  algún error en las clases, en las pruebas o en los archivos de configuración POM.
  
  
  
  
   Esto se logro al configurar el repositorio con la rama main protegida, es decir,  solicitar almenos
   dos revisiones de cada pullrequest, de esta forma cada cambio realizado por un miembro del equipo
en su respectiva rama debía ser revisada por los demás integrantes antes de integrarla a la rama main.

   ![Configuracion GIT](https://user-images.githubusercontent.com/80803850/236376166-f9c24a7a-e1f7-4538-9eb8-789eb20f83b1.png)
   
   
   Como ejemplo la siguiente imagen, no se subieron los cambios a main porque faltaba el archivo POM.
   
   ![FaltaArchivo POM](https://user-images.githubusercontent.com/80803850/236377962-d9badf53-57c2-427d-99ca-41ae4233253b.png)
   
   En la siguiente imagen, podemos ver la solicitud de revisión de otro commit pendiente.
   
   
   ![PullRequestValidation](https://user-images.githubusercontent.com/80803850/236379117-37509bf9-6206-4752-b98a-39cf1bbb4da9.png)

   
   
   Por ultimo, también se procuro tener readme descriptivo del proyecto.





