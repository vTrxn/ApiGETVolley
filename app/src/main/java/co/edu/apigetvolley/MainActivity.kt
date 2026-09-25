// package: Palabra clave de Kotlin que especifica el paquete o espacio de nombres donde se ubica este archivo ejecutable.
// co.edu.apigetvolley: Ruta de paquete que identifica la ubicación lógica del archivo dentro del proyecto de Android.
package co.edu.apigetvolley

// import: Palabra clave de Kotlin para importar clases y componentes externos para utilizarlos en este archivo sin necesidad de escribir su nombre completo.
import android.os.Bundle // Componente del SDK de Android para guardar y pasar estados de la actividad.
import android.view.View // Clase base de la interfaz gráfica de usuario en Android (gestiona visibilidad, eventos, etc.).
import android.widget.ArrayAdapter // Adaptador de Android que vincula una lista de datos (ArrayList) con una vista de lista (ListView).
import android.widget.ListView // Componente de la interfaz gráfica de usuario que muestra una lista desplazable de elementos.
import android.widget.ProgressBar // Componente de UI tipo indicador de progreso (círculo de carga/spinner) durante operaciones asíncronas.
import android.widget.TextView // Componente de UI de Android para renderizar y mostrar textos estáticos o dinámicos en pantalla.
import android.widget.Toast // Componente del sistema para mostrar mensajes emergentes breves e informativos en la pantalla.
import androidx.appcompat.app.AppCompatActivity // Clase base de Android Jetpack que otorga compatibilidad con versiones anteriores de Android para la actividad.
import com.android.volley.Request // Clase de la librería Volley que representa los métodos de solicitud HTTP (GET, POST, PUT, DELETE).
import com.android.volley.RequestQueue // Cola de peticiones de la librería Volley que gestiona la ejecución ordenada y concurrente de solicitudes de red.
import com.android.volley.VolleyError // Clase de la librería Volley que encapsula las excepciones y errores ocurridos en las solicitudes de red.
import com.android.volley.toolbox.JsonArrayRequest // Solicitud especializada de Volley diseñada para obtener y parsear un arreglo JSON (JSONArray).
import com.android.volley.toolbox.Volley // Clase de utilidad principal de la librería Volley usada para crear e inicializar la cola de solicitudes (RequestQueue).
import co.edu.apigetvolley.model.Post // Importación de la clase de datos 'Post' perteneciente al modelo de la aplicación.
import org.json.JSONException // Excepción estándar de Java/Android lanzada cuando ocurre un error al procesar/parsear contenido en formato JSON.

// class: Palabra clave de Kotlin utilizada para declarar una clase (componente principal en programación orientada a objetos).
// MainActivity: Nombre identificador de la actividad principal de la aplicación Android.
// : AppCompatActivity(): Herencia de clases. Indica que MainActivity extiende de AppCompatActivity para convertirse en una pantalla de la aplicación.
class MainActivity : AppCompatActivity() {

    // companion object: Bloque especial en Kotlin que declara miembros estáticos accesibles directamente con el nombre de la clase sin instanciarla.
    companion object {
        // private: Modificador de acceso que restringe la visibilidad de la constante únicamente dentro de esta clase.
        // const: Palabra clave que define una constante en tiempo de compilación (Compile-time constant).
        // val: Palabra clave que declara una variable/propiedad inmutable (de solo lectura).
        // URL_API: Nombre de la constante que contiene la dirección URL del endpoint del servicio web REST para consultar las publicaciones.
        private const val URL_API = "https://jsonplaceholder.typicode.com/posts"

        // private const val: Modificador de acceso privado para constante inmutable de tiempo de compilación.
        // REQUEST_TAG: Etiqueta de identificación asociada a la petición HTTP para poder cancelarla o rastrearla en la cola de Volley.
        private const val REQUEST_TAG = "GET_POSTS"
    }

    // private: Modificador de acceso para limitar el alcance de la variable únicamente a la clase MainActivity.
    // lateinit: Palabra clave de Kotlin para "inicialización tardía". Le promete al compilador que la variable se inicializará antes de ser leída, evitando que deba declararse como nula (Nullable).
    // var: Palabra clave de Kotlin utilizada para declarar una variable mutable (cuyo valor puede cambiar a lo largo del tiempo).
    // lvTodos: Nombre de la variable que mantendrá la referencia al componente ListView del diseño XML.
    // : ListView: Especificación del tipo de componente UI de la variable.
    private lateinit var lvTodos: ListView

    // private lateinit var: Declaración de variable mutable privada con inicialización tardía prometida.
    // progressBar: Variable que mantendrá la referencia gráfica al indicador de carga ProgressBar.
    // : ProgressBar: Tipo de componente de interfaz gráfica.
    private lateinit var progressBar: ProgressBar

    // private lateinit var: Declaración de variable mutable privada con inicialización diferida.
    // tvEstado: Variable para almacenar la referencia al TextView que mostrará mensajes de error o estado al usuario.
    // : TextView: Tipo de componente de texto UI.
    private lateinit var tvEstado: TextView

    // private lateinit var: Variable privada mutable con inicialización diferida para la cola de red de Volley.
    // requestQueue: Instancia de RequestQueue que administrará los hilos en segundo plano para enviar las peticiones HTTP.
    // : RequestQueue: Tipo de objeto encargado de gestionar las solicitudes de red en Volley.
    private lateinit var requestQueue: RequestQueue

    // private: Modificador de visibilidad privada.
    // val: Declaración de variable inmutable (la referencia de la lista no cambiará, aunque su contenido interno sí).
    // listaPosts: Nombre de la variable que almacena la colección de objetos de tipo Post recuperados de la API.
    // = ArrayList<Post>(): Instanciación de una lista dinámica mutable de objetos de tipo Post.
    private val listaPosts = ArrayList<Post>()

    // override: Palabra clave que indica que se sobreescribe la función 'onCreate' perteneciente a la clase base AppCompatActivity.
    // fun: Palabra clave de Kotlin para declarar y definir una función o método.
    // onCreate: Nombre de la función del ciclo de vida de la actividad de Android que se ejecuta cuando la pantalla es creada por el sistema.
    // (savedInstanceState: Bundle?): Parámetro que recibe un contenedor de datos de estado previo guardado, el signo '?' indica que puede ser nulo (Nullable).
    override fun onCreate(savedInstanceState: Bundle?) {
        // super: Palabra clave utilizada para invocar la implementación del método en la clase padre (AppCompatActivity).
        // .onCreate(savedInstanceState): Ejecuta las tareas iniciales necesarias del sistema Android para iniciar la actividad.
        super.onCreate(savedInstanceState)

        // setContentView: Función miembro de AppCompatActivity que infla y vincula la interfaz gráfica XML a esta clase Kotlin.
        // R.layout.activity_main: Referencia generada automáticamente al archivo de diseño XML ubicado en res/layout/activity_main.xml.
        setContentView(R.layout.activity_main)

        // inicializarVistas(): Llamada a la función privada encargada de asociar las variables Kotlin con los componentes del XML.
        inicializarVistas()

        // requestQueue = Volley.newRequestQueue(...): Crea e inicializa la cola de peticiones de red utilizando el contexto global de la aplicación.
        // applicationContext: Objeto del sistema que proporciona el contexto del ciclo de vida de la aplicación Android.
        requestQueue = Volley.newRequestQueue(applicationContext)

        // consumirApi(): Llamada a la función interna que configura y envía la petición HTTP GET a la API REST.
        consumirApi()
    }

    // private: Modificador de visibilidad para que esta función solo pueda ejecutarse dentro de MainActivity.
    // fun: Palabra clave para declarar una función en Kotlin.
    // inicializarVistas(): Nombre descriptivo de la función que busca e inicializa los elementos gráficos del XML mediante sus IDs.
    private fun inicializarVistas() {
        // findViewById: Función del SDK de Android para buscar un componente gráfico en la jerarquía del diseño cargado según su identificador único (ID).
        // R.id.lvTodos: Identificador entero único generado automáticamente en la clase 'R' para la vista ListView del XML.
        lvTodos = findViewById(R.id.lvTodos)

        // findViewById(R.id.progressBar): Asigna la referencia de la barra de progreso en XML a la variable progressBar.
        progressBar = findViewById(R.id.progressBar)

        // findViewById(R.id.tvEstado): Asigna la referencia del TextView en XML a la variable tvEstado.
        tvEstado = findViewById(R.id.tvEstado)
    }

    // private fun: Declaración de método privado.
    // mostrarCargando: Función que conmuta la visibilidad entre el componente de carga (ProgressBar) y la lista (ListView).
    // (cargando: Boolean): Parámetro de entrada de tipo booleano (true para indicar que está cargando datos, false para ocultarlo).
    private fun mostrarCargando(cargando: Boolean) {
        // progressBar.visibility = ...: Asigna el estado de visibilidad del ProgressBar.
        // if (cargando) View.VISIBLE else View.GONE: Expresión condicional inline de Kotlin. Si cargando es verdadero muestra el spinner (VISIBLE), si no, lo remueve de la pantalla (GONE).
        progressBar.visibility = if (cargando) View.VISIBLE else View.GONE

        // lvTodos.visibility = ...: Oculta la lista ListView mientras se realiza la descarga y la muestra cuando el proceso finaliza.
        lvTodos.visibility = if (cargando) View.GONE else View.VISIBLE
    }

    // private fun: Declaración de método privado auxiliar.
    // mostrarError: Función que gestiona la visualización de mensajes de fallo en la pantalla mediante un TextView y un Toast.
    // (mensaje: String): Parámetro que recibe la cadena de texto con la descripción detallada del error.
    private fun mostrarError(mensaje: String) {
        // Oculta el spinner de carga al ocurrir un error.
        mostrarCargando(false)

        // tvEstado.text = mensaje: Asigna el texto del error al componente TextView.
        tvEstado.text = mensaje

        // tvEstado.visibility = View.VISIBLE: Hace visible el TextView de error en la pantalla.
        tvEstado.visibility = View.VISIBLE

        // Toast.makeText(...).show(): Muestra una notificación emergente corta en la pantalla con el texto del error.
        // this: Pasa la instancia actual de la actividad como Contexto para crear el Toast.
        // Toast.LENGTH_LONG: Constante que define que el mensaje flotante permanecerá visible por un período de tiempo prolongado.
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    // private fun: Declaración de método privado para procesar errores HTTP o de conexión.
    // procesarError: Analiza el objeto de error retornado por Volley para generar un mensaje comprensible.
    // (error: VolleyError): Parámetro de tipo VolleyError que almacena los detalles de la falla producida en la red.
    private fun procesarError(error: VolleyError) {
        // var: Declaración de una variable mutable cuyo contenido se puede reasignar.
        // mensaje = error.message: Extrae el mensaje de error nativo adjunto en el objeto VolleyError.
        var mensaje = error.message

        // if (mensaje.isNullOrBlank()): Estructura de control que evalúa si la cadena de texto es nula, vacía o contiene solo espacios en blanco.
        if (mensaje.isNullOrBlank()) {
            // Reasigna un mensaje explícito cuando la respuesta del servidor o de red no trae detalles.
            mensaje = "Verifique la conexión a internet."
        }

        // Invocación a la función mostrarError pasando la cadena formateada resultante.
        mostrarError("Error en la solicitud: $mensaje")
    }

    // private fun: Función privada que orquesta el consumo de la API REST mediante una petición asíncrona de Volley.
    // consumirApi(): Función principal de integración con la red.
    private fun consumirApi() {
        // Activa el indicador gráfico de carga en la interfaz de usuario.
        mostrarCargando(true)

        // val request = JsonArrayRequest(...): Instanciación de una solicitud HTTP que espera un arreglo JSON ([...]) como respuesta.
        val request = JsonArrayRequest(
            // Request.Method.GET: Especifica el método HTTP de la solicitud (GET para consultar/obtener datos del servidor).
            Request.Method.GET,

            // URL_API: Pasa la dirección URL endpoint que será consultada por el cliente Volley.
            URL_API,

            // null: Parámetro que representa el cuerpo (body) de la solicitud JSON. Al ser una petición GET no envía un cuerpo de datos.
            null,

            // Listener de respuesta exitosa (Callback Lambda ejecutado cuando el servidor responde con código 200 OK):
            // { response -> ... }: Recibe el objeto JSONArray en la variable 'response'.
            { response ->
                // Limpia los elementos existentes previamente en el ArrayList de publicaciones para evitar duplicados.
                listaPosts.clear()

                // try: Bloque de manejo de excepciones para capturar posibles errores durante el parseo del contenido JSON.
                try {
                    // for (i in 0 until response.length()): Bucle que recorre desde el índice 0 hasta el tamaño total del arreglo JSON recibido.
                    // in: Palabra clave para iteración sobre rangos.
                    // until: Función de rango que crea un intervalo excluyendo el límite superior (de 0 a N-1).
                    for (i in 0 until response.length()) {
                        // response.getJSONObject(i): Extrae el objeto JSON individual situado en la posición de índice 'i'.
                        val item = response.getJSONObject(i)

                        // item.getInt("userId"): Lee y extrae el atributo 'userId' de tipo entero de la clave del objeto JSON.
                        val userId = item.getInt("userId")

                        // item.getInt("id"): Lee el atributo clave 'id' de tipo entero dentro del objeto JSON.
                        val id = item.getInt("id")

                        // item.getString("title"): Lee y extrae el texto del atributo 'title' dentro del objeto JSON.
                        val title = item.getString("title")

                        // item.getString("body"): Lee y extrae el texto descriptivo del atributo 'body' dentro del objeto JSON.
                        val body = item.getString("body")

                        // val post = Post(...): Instancia un nuevo objeto del modelo 'Post' con los datos procesados.
                        val post = Post(userId, id, title, body)

                        // listaPosts.add(post): Añade el objeto Post creado a la lista mutable interna de la actividad.
                        listaPosts.add(post)
                    }

                    // val adapter = ArrayAdapter(...): Instancia un adaptador estándar de Android que transforma la lista de objetos Kotlin en vistas individuales para el ListView.
                    // this: Pasa la actividad actual como contexto.
                    // android.R.layout.simple_list_item_1: Utiliza un diseño de ítem simple incorporado en el SDK de Android.
                    // listaPosts: Inyecta la fuente de datos que contiene los objetos Post a renderizar.
                    val adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        listaPosts,
                    )

                    // lvTodos.adapter = adapter: Vincula el adaptador configurado al componente ListView para renderizar los elementos en la pantalla.
                    lvTodos.adapter = adapter

                    // Desactiva la visibilidad del componente de carga al haber completado el procesamiento con éxito.
                    mostrarCargando(false)

                    // Oculta el mensaje de estado/error si estaba presente previamente.
                    tvEstado.visibility = View.GONE

                    // Toast.makeText(...).show(): Muestra una alerta informativa indicando la cantidad de elementos descargados con éxito.
                    Toast.makeText(
                        this,
                        "Se recibieron ${listaPosts.size} registros",
                        Toast.LENGTH_LONG,
                    ).show()

                } catch (e: JSONException) {
                    // catch: Bloque que se ejecuta si ocurrió una excepción de tipo JSONException al procesar los campos del JSON.
                    // e.printStackTrace(): Imprime la traza de la pila de error en el logcat para depuración de desarrollo.
                    e.printStackTrace()

                    // Muestra el mensaje de error visual al usuario indicando el fallo en el parseo del formato JSON.
                    mostrarError("No fue posible procesar la respuesta.")
                }
            },

            // Listener de respuesta con error (Callback Lambda que se dispara si ocurre un error HTTP 4xx/5xx o de conexión):
            // { error -> ... }: Recibe el error retornado en la variable 'error'.
            { error ->
                // Invocación a la función de manejo de errores pasándole el objeto VolleyError.
                procesarError(error)
            },
        )

        // request.tag = REQUEST_TAG: Asigna una etiqueta identicadora a la solicitud para poder rastrearla o cancelarla posteriormente.
        request.tag = REQUEST_TAG

        // requestQueue.add(request): Agrega la solicitud de red a la cola de procesamiento de Volley para que se ejecute en un hilo secundario asíncrono.
        requestQueue.add(request)
    }

    // override fun onStop(): Sobrescribe la función del ciclo de vida de Android que se ejecuta cuando la actividad deja de ser visible para el usuario.
    override fun onStop() {
        // super.onStop(): Invoca la implementación de la clase base AppCompatActivity para mantener el ciclo de vida del SO.
        super.onStop()

        // if (::requestQueue.isInitialized): Operador de reflexión de Kotlin (::) que verifica si la variable 'lateinit' fue inicializada previamente para evitar una excepción UninitializedPropertyAccessException.
        if (::requestQueue.isInitialized) {
            // requestQueue.cancelAll(REQUEST_TAG): Cancela todas las solicitudes de red pendientes en la cola marcadas con el identificador 'REQUEST_TAG', evitando fugas de memoria o llamadas a vistas destruidas.
            requestQueue.cancelAll(REQUEST_TAG)
        }
    }
}
