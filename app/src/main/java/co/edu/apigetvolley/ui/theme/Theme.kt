// package: Palabra clave de Kotlin que ubica este archivo en el paquete del tema visual.
package co.edu.apigetvolley.ui.theme

// import: Palabras clave de inclusión de dependencias del framework Android y Jetpack Compose.
import android.app.Activity // Representa el contexto de actividad del SO Android.
import android.os.Build // Proporciona información sobre la versión del sistema operativo Android en ejecución.
import androidx.compose.foundation.isSystemInDarkTheme // Función de Compose que detecta si el modo oscuro está activo en el sistema.
import androidx.compose.material3.MaterialTheme // Componente contenedor principal de Material Design 3 en Compose.
import androidx.compose.material3.darkColorScheme // Función que genera la paleta de colores para el tema oscuro.
import androidx.compose.material3.dynamicDarkColorScheme // Función para obtener colores dinámicos oscuros del sistema (Android 12+ / Material You).
import androidx.compose.material3.dynamicLightColorScheme // Función para obtener colores dinámicos claros del sistema (Android 12+ / Material You).
import androidx.compose.material3.lightColorScheme // Función que genera la paleta de colores para el tema claro.
import androidx.compose.runtime.Composable // Anotación fundamental de Jetpack Compose que marca funciones que producen UI.
import androidx.compose.ui.platform.LocalContext // Propiedad que otorga acceso al contexto de Android actual en la jerarquía Compose.

// private: Modificador de acceso privado para la constante del esquema oscuro.
// val DarkColorScheme = darkColorScheme(...): Define la asignación de colores primario, secundario y terciario para el modo noche.
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// private val LightColorScheme = lightColorScheme(...): Define la asignación de colores para el modo día.
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

// @Composable: Anotación que indica que la función construye un elemento UI declarativo mediante Jetpack Compose.
// fun ApiGETVolleyTheme(...): Función composable contenedora que aplica el tema personalizado Material 3 a toda la aplicación.
@Composable
fun ApiGETVolleyTheme(
    // darkTheme: Parámetro booleano con valor por defecto que evalúa si el sistema Android tiene activo el modo oscuro.
    darkTheme: Boolean = isSystemInDarkTheme(),
    // dynamicColor: Parámetro booleano para habilitar/deshabilitar los colores dinámicos de Material You (Android 12+).
    dynamicColor: Boolean = true,
    // content: Parámetro lambda de contenido UI composable que será envuelto por este tema.
    content: @Composable () -> Unit
) {
    // val colorScheme = when { ... }: Estructura condicional que selecciona la paleta de colores adecuada según la versión de Android y preferencia de tema.
    val colorScheme = when {
        // Verifica si los colores dinámicos están activos y la versión del sistema es igual o superior a Android 12 (API 31 / S).
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        // Si darkTheme es verdadero, aplica el esquema de color oscuro estático.
        darkTheme -> DarkColorScheme
        // En cualquier otro caso, aplica el esquema de color claro.
        else -> LightColorScheme
    }

    // MaterialTheme: Inyecta el esquema de colores, tipografía y contenido en el árbol de composición de Compose.
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
