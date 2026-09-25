// package: Palabra clave de Kotlin que define el paquete de configuración tipográfica.
package co.edu.apigetvolley.ui.theme

// import: Importaciones de clases de Material3 y tipografía de Jetpack Compose.
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// val Typography = Typography(...): Instanciación e inyección del conjunto de estilos de fuente estandarizados según Material Design 3.
val Typography = Typography(
    // bodyLarge: Configuración tipográfica utilizada para párrafos o textos de cuerpo grande.
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Familia tipográfica por defecto del sistema Android (Roboto).
        fontWeight = FontWeight.Normal,  // Grosor de la fuente normal/regular.
        fontSize = 16.sp,                // Tamaño de letra definido en píxeles escalables (Scale-independent Pixels).
        lineHeight = 24.sp,              // Interlineado o altura de línea entre texto.
        letterSpacing = 0.5.sp           // Espaciado horizontal entre caracteres.
    )
)
