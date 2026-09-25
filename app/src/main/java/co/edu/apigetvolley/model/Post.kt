// package: Palabra clave de Kotlin que especifica el paquete o espacio de nombres donde se ubica esta clase dentro del proyecto.
// co.edu.apigetvolley.model: Ruta del paquete estructurado que organiza los modelos de datos de la aplicación.
package co.edu.apigetvolley.model

// data: Palabra clave de Kotlin que declara una "Clase de Datos" (Data Class). Otorga automáticamente funciones como equals(), hashCode(), copy() y toString().
// class: Palabra clave fundamental utilizada para declarar una clase o plantilla de objeto en programación orientada a objetos.
// Post: Nombre identificador de la clase que modela la estructura de una publicación (Post) recibida desde el servicio web JSON.
// ( ... ): Constructor primario que define los atributos e inyección de propiedades requeridos para instanciar la clase.
data class Post(
    // val: Palabra clave de Kotlin que define una propiedad inmutable (de solo lectura, equivalente a 'final' en Java).
    // userId: Identificador de la propiedad que representa el ID del usuario propietario de la publicación.
    // : Int: Especificación del tipo de dato numérico entero de 32 bits (Integer).
    val userId: Int,

    // val: Palabra clave que define una variable o propiedad inmutable.
    // id: Nombre de la propiedad que almacena el identificador clave primaria de la publicación específica.
    // : Int: Tipo de dato numérico entero de 32 bits.
    val id: Int,

    // val: Palabra clave que declara una propiedad de solo lectura.
    // title: Nombre de la propiedad que almacena el título textual del post.
    // : String: Tipo de dato cadena de caracteres o texto.
    val title: String,

    // val: Palabra clave que declara una propiedad inmutable.
    // body: Nombre de la propiedad que guarda el contenido o cuerpo del mensaje de la publicación.
    // : String: Tipo de dato cadena de texto.
    val body: String,
) {
    // override: Palabra clave que le indica al compilador que se sobrescribe un método existente de la clase padre Any.
    // fun: Palabra clave de Kotlin para declarar y definir una función o método ejecutable.
    // toString(): Nombre de la función reservada para generar una representación en texto del objeto actual.
    // : String: Indica explícitamente que la función retorna un valor de tipo cadena de texto.
    override fun toString(): String {
        // return: Palabra clave que devuelve el resultado de la expresión y finaliza la ejecución de la función.
        // "ID: $id ...": Plantilla de texto (String Template) con interpolación de variables ($id, $userId, $title, $body).
        return "ID: $id | User ID: $userId\nTítulo: $title\nContenido: $body"
    }
}
