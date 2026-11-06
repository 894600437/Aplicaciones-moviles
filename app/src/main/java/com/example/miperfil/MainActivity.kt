
package com.example.miperfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

private val Morado = Color(0xFF6726B5)
private val MoradoClaro = Color(0xFFF0E8FA)
private val TextoOscuro = Color(0xFF151B35)
private val TextoSecundario = Color(0xFF586174)
private val Fondo = Color(0xFFFAF9FD)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().apply {
            userAgentValue = "MiPerfilAndroid/1.0 (com.example.miperfil)"
        }

        setContent {
            MaterialTheme {
                MiPerfilApp()
            }
        }
    }
}

@Composable
fun MiPerfilApp() {

    var mostrarMenu by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
    ) {

        // ADAPTACIÓN AL TAMAÑO DEL CELULAR
        val pantallaPequena = maxWidth < 360.dp

        val margen = if (pantallaPequena) 16.dp else 24.dp
        val tamanoFoto = if (pantallaPequena) 120.dp else 140.dp
        val tamanoNombre = if (pantallaPequena) 22.sp else 25.sp
        val alturaMapa = if (pantallaPequena) 200.dp else 230.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {

            // ENCABEZADO CON UNA SOLA IMAGEN DE FONDO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(295.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.fondo),
                    contentDescription = "Fondo de perfil",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp)
                        .align(Alignment.TopCenter),
                    contentScale = ContentScale.Crop
                )

                // IDENTIFICACIÓN DE LA UNIVERSIDAD
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 18.dp, top = 24.dp),
                    shape = RoundedCornerShape(30.dp),
                    color = Color.White.copy(alpha = 0.94f)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "UPP",
                            color = Morado,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "Universidad Politécnica",
                                color = TextoOscuro,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "de Pachuca",
                                color = TextoOscuro,
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                // ETIQUETA INSTITUCIONAL
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp, bottom = 112.dp),
                    shape = RoundedCornerShape(30.dp),
                    color = MoradoClaro
                ) {
                    Text(
                        text = "UPP Software",
                        color = Morado,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        )
                    )
                }

                // FOTO CIRCULAR SUPERPUESTA
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 25.dp, y = 155.dp)
                        .size(tamanoFoto)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(5.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.perfil),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            // NOMBRE Y CORREO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = margen,
                        end = margen,
                        top = 4.dp
                    )
            ) {

                Text(
                    text = "Maria Aritzel Vera Tena",
                    fontSize = tamanoNombre,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextoOscuro,
                    lineHeight = 31.sp
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "arybebe413@gmail.com",
                    fontSize = 14.sp,
                    color = Morado
                )
            }

            Spacer(Modifier.height(24.dp))

            // ABOUT
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = margen)
            ) {

                Text(
                    text = "About",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextoOscuro
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Soy estudiante de TIID en UPP y me interesa " +
                            "la tecnología, la programación y el desarrollo " +
                            "de aplicaciones. Me gusta aprender nuevas " +
                            "herramientas y crear proyectos.",
                    fontSize = 15.sp,
                    color = TextoSecundario,
                    lineHeight = 23.sp
                )
            }

            Spacer(Modifier.height(28.dp))

            // TÍTULO DE LENGUAJES
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = margen),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "</>",
                    color = Morado,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(Modifier.width(10.dp))

                Text(
                    text = "LENGUAJES DE PROGRAMACIÓN",
                    color = TextoOscuro,
                    fontSize = if (pantallaPequena) 13.sp else 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "Desliza →",
                    color = Color(0xFF8992A8),
                    fontSize = 11.sp
                )
            }

            Spacer(Modifier.height(15.dp))

            // LENGUAJES SOLO CON TEXTO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                val lenguajes = listOf(
                    "HTML",
                    "CSS",
                    "Java",
                    "Kotlin",
                    "C++",
                    "PHP",
                    "JavaScript",
                    "SQL",
                    "Python",
                    "Laravel"
                )

                lenguajes.forEach { lenguaje ->

                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = MoradoClaro
                    ) {
                        Text(
                            text = lenguaje,
                            color = TextoOscuro,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                horizontal = 18.dp,
                                vertical = 12.dp
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(30.dp))

            // UBICACIÓN
            Text(
                text = "Mi ubicación",
                modifier = Modifier.padding(horizontal = margen),
                color = TextoOscuro,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            // MAPA EN TARJETA
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(alturaMapa),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE9E9E9)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                MapaOpenStreetMap(
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.height(30.dp))
        }

        // MENÚ INFERIOR
        if (mostrarMenu) {

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(
                        start = 14.dp,
                        end = 14.dp,
                        bottom = 75.dp
                    ),
                shape = RoundedCornerShape(30.dp),
                color = Color.White,
                shadowElevation = 10.dp
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Inicio", color = Morado, fontWeight = FontWeight.Bold)
                    Text("Perfil", color = Morado, fontWeight = FontWeight.Bold)
                    Text("About", color = Morado, fontWeight = FontWeight.Bold)
                    Text("Lenguajes", color = Morado, fontWeight = FontWeight.Bold)
                    Text("Mapa", color = Morado, fontWeight = FontWeight.Bold)
                }
            }
        }

        // BOTÓN FLOTANTE
        FloatingActionButton(
            onClick = {
                mostrarMenu = !mostrarMenu
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(62.dp),
            containerColor = Morado,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Text(
                text = if (mostrarMenu) "×" else "+",
                fontSize = 32.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

// MAPA DE LA UNIVERSIDAD POLITÉCNICA DE PACHUCA
@Composable
fun MapaOpenStreetMap(
    modifier: Modifier = Modifier
) {

    if (LocalInspectionMode.current) {

        Box(
            modifier = modifier.background(Color(0xFFEAEAEA)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Universidad Politécnica de Pachuca",
                    color = TextoOscuro,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Zempoala, Hidalgo",
                    color = TextoSecundario,
                    fontSize = 11.sp
                )

                Text(
                    text = "Vista previa del mapa",
                    color = TextoSecundario,
                    fontSize = 11.sp
                )
            }
        }

    } else {

        AndroidView(
            modifier = modifier,

            factory = { context ->

                MapView(context).apply {

                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)

                    // COORDENADAS DEL CAMPUS UPP
                    val ubicacionUPP = GeoPoint(
                        19.98007,
                        -98.6853
                    )

                    controller.setZoom(17.0)
                    controller.setCenter(ubicacionUPP)

                    // MARCADOR DE LA UNIVERSIDAD
                    val marcador = Marker(this).apply {

                        position = ubicacionUPP

                        title = "Universidad Politécnica de Pachuca"

                        snippet = "Ex-Hacienda de Santa Bárbara, Zempoala, Hidalgo"

                        setAnchor(
                            Marker.ANCHOR_CENTER,
                            Marker.ANCHOR_BOTTOM
                        )
                    }

                    overlays.add(marcador)
                }
            },

            update = { mapa ->
                mapa.invalidate()
            }
        )
    }
}

// VISTA PREVIA
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun VistaPrevia() {
    MiPerfilApp()
}
