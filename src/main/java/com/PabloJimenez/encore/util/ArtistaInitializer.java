package com.PabloJimenez.encore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PabloJimenez.encore.config.InitializationConfig;
import com.PabloJimenez.encore.entity.*;
import com.PabloJimenez.encore.service.*;

@Component
public class ArtistaInitializer {
    
    @Autowired
    private ArtistaService artistaService;
    
    @Autowired
    private InitializationConfig config;
    
    private final Faker faker = new Faker(new Locale("es"));

    static ArrayList<String> artistas = new ArrayList<>(Arrays.asList(
            "Guxo", "L0rna", "Yung Beef", "Kaydy Cain", "C. Tangana",
            "Bad Gyal", "Recycled J", "Dellafuente", "Bejo", "Cookin Soul",
            "La Blackie", "Natos y Waor", "Canserbero", "Porta", "Akapellah",
            "Gloosito", "Nach", "Violadores del Verso", "Tarchi",
            "Rels B", "Morad", "Quevedo", "Bizarrap", "Trueno",
            "Neo Pistea", "Lit Killah", "Maria Becerra",
            "Rosalía", "Aitana", "Melendi", "Alejandro Sanz", "Pablo Alborán", "Fito y Fitipaldis",
            "Extremoduro", "Drake", "Kendrick Lamar", "Travis Scott", "J. Cole", "Tyler the Creator",
            "Frank Ocean", "Childish Gambino", "Billie Eilish", "The Weeknd", "Post Malone",
            "Kanye West", "Lil Uzi Vert", "Playboi Carti", "21 Savage", "Future",
            "Doja Cat", "SZA", "Arctic Monkeys", "Tame Impala", "Radiohead", "Mac DeMarco"));

    ArrayList<String> imagenes = new ArrayList<>(Arrays.asList(
            // Guxo
            "https://bookingagentinfo.com/wp-content/uploads/2024/10/ab6761610000e5ebc1c0eac288e6ab1dbf8c120d.jpg",
            // L0rna
            "https://imagenes.20minutos.es/files/image_1920_1080/uploads/imagenes/2025/06/12/l0rna-en-el-videoclip-de-3-di-as-con-el-rimel.png",
            // Yung Beef
            "https://labiode.com/wp-content/uploads/2022/01/Yung-Beef-trapero-768x960.jpg",
            // Kaydy Cain
            "https://akamai.sscdn.co/uploadfile/letras/fotos/5/9/0/d/590db392dbdfe59208955ddbcd5334bd.jpg",
            // C. Tangana
            "https://www.freim.tv/wp-content/uploads/2022/02/244306.jpg",
            // Bad Gyal
            "https://phantom-marca.unidadeditorial.es/ef6947587dbfe170b12aeb8291c8332c/resize/1200/f/jpg/assets/multimedia/imagenes/2023/06/08/16862266762736.jpg",
            // Recycled J
            "https://1.bp.blogspot.com/-YT4Tx7Xe5dE/YRkvjotp-TI/AAAAAAAEwYk/3PJwuAvfA2YikRLhmApB5iC8cqF1QxOlACNcBGAsYHQ/s640/RECYCLED%2Bfoto%2B1%2Bspotify.jpg",
            // Dellafuente
            "https://i.pinimg.com/originals/e4/a2/e0/e4a2e0b765c6231251f3ccf19ea261d0.jpg",
            // Bejo
            "https://cdn.diariodeavisos.com/wp-content/uploads/2023/05/Bejo.jpg",
            // Cookin Soul
            "https://musictech.com/wp-content/uploads/2022/04/Cookin-Soul-MusicTech-Credit-Isolde-Woudstra@1050x1400.jpg",
            // La Blackie
            "https://applications-media.feverup.com/image/upload/f_auto,w_720,h_720/fever2/plan/photo/8fddf5e2-af5e-11f0-826e-0a6c8ec62ddc.png",
            // Natos y Waor
            "https://th.bing.com/th/id/R.39160c88f7705ed973305d2a5caa17ec?rik=qz7Ee2A3CHV3vw&pid=ImgRaw&r=0",
            // Canserbero
            "https://i.pinimg.com/originals/b4/6b/88/b46b88626734dacad1802d23323cae70.jpg?nii=t",
            // Porta
            "https://labiode.com/wp-content/uploads/2021/10/Porta-02.jpg",
            // Akapellah
            "https://batalladegallos.org/wp-content/uploads/2022/08/Akapellah.png",
            // Gloosito
            "https://www.mataderomadrid.org/sites/default/files/styles/height_500/public/media/image/2024/04/gloosito.jpg.webp?itok=mUzIwxC_",
            // Nach
            "https://nach.es/wp-content/uploads/2023/08/nach-rap-en-espanol.jpg",
            // Violadores del Verso
            "https://tse3.mm.bing.net/th/id/OIP.f22ZB2Dn9zLSotxGvP67nQHaHa?cb=thfc1falcon&rs=1&pid=ImgDetMain&o=7&rm=3",
            // Tarchi
            "https://t2.genius.com/unsafe/600x600/https://images.genius.com/a492e6dfbeb69794a45eba6dee8ae678.480x480x1.png",
            // Rels B
            "https://th.bing.com/th/id/R.4e47a8bc17adf84ee3b0de102b63cee0?rik=N9GEjg%2fhrCJyKg&pid=ImgRaw&r=0",
            // Morad
            "https://img.redbull.com/images/c_crop,w_3615,h_1808,x_0,y_826,f_auto,q_auto/c_scale,w_1200/redbullcom/2023/12/12/x0zbc814oczjh200it2m/morad-red-bull-soundclash-portrait",
            // Quevedo
            "https://rtvc.es/archivos/2023/01/Quevedo-1024x1024.jpg",
            // Bizarrap
            "https://media.pitchfork.com/photos/62053f3f7db00b3b97548d5e/2:1/w_2560%2Cc_limit/CROP%2520Bizarrap%25208%2520sept%25202021%2520-%2520prensa22980%2520(1).jpg",
            // Trueno
            "https://es.rollingstone.com/wp-content/uploads/2024/05/TRUENO-APERTURA-DISCO-RS-1024x616.jpg",
            // Neo Pistea
            "https://www.tiempox.com/resizer/v2/neo-pistea-A5RY724QCBAJ7C44Q5R5ZLS3RY.jpg?auth=90eb0f305917de017cd439357bb5a2849bec0686ce85f484b3a3785ca191b2e8&focal=483%2C344&width=800&height=533&quality=70",
            // Lit Killah
            "https://www.billboard.com/wp-content/uploads/2022/11/lit-killah-2022-b-billboard-1548.jpg?w=1024",
            // Maria Becerra
            "https://www.qualityradio.com.ar/wp-content/uploads/2024/05/Maria-Becerra-scaled-1-2048x1536.jpg",
            // Rosalía
            "https://cdn2.telediario.mx/uploads/media/2023/04/18/rosalia-presentara-abril-ciudad-mexico.jpg",
            // Aitana
            "https://22minutoscon.com/wp-content/uploads/2023/03/sb-hb-aitana-001-1632818812-e1680258662838.jpg",
            // Melendi
            "https://ticketlive.com.co/wp-content/uploads/2025/01/melendi-en-colombia.jpg",
            // Alejandro Sanz
            "https://th.bing.com/th/id/R.b4a34f06b18e803b9e93ca817431a5e1?rik=%2bkmoyf0MjhabEQ&pid=ImgRaw&r=0",
            // Pablo Alborán
            "https://www.fmdos.cl/wp-content/uploads/2021/02/Pablo-Alboran.jpg",
            // Fito y Fitipaldis
            "https://image.europafm.com/clipping/cmsimages01/2025/04/24/391E4E38-92C7-42C5-857C-79389AB8FE8F/fito-fitipaldis-anuncian-gira-nuevo-album-monte-aullidos_98.jpg?crop=3000,1688,x0,y0&width=1900&height=1069&optimize=low&format=webply",
            // Extremoduro
            "https://www.diariocritico.com/fotos/1/338009_extremoduro-rock.jpg",
            // Drake
            "https://ca.billboard.com/media-library/drake.webp?id=56501521&width=1200&height=800&quality=90&coordinates=3%2C0%2C4%2C0",
            // Kendrick Lamar
            "https://www.investopedia.com/thmb/QWfQNFAKm7YY7D17s30PR_WjhRU=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-2196837244-cd59229199614d8da69e839d12e30909.jpg",
            // Travis Scott
            "https://orah.co/wp-content/uploads/2024/10/64c2216d3bcf4.jpg",
            // J. Cole
            "https://th.bing.com/th/id/R.e948ff31875c79c7d858f7a0bc781dd3?rik=zh6cckVeaRCprg&pid=ImgRaw&r=0",
            // Tyler the Creator
            "https://tse2.mm.bing.net/th/id/OIP.5aYjtzdzIa1NJZzIxg5pbAHaJb?cb=thfc1falcon&rs=1&pid=ImgDetMain&o=7&rm=3",
            // Frank Ocean
            "https://www.rollingstone.com/wp-content/uploads/2025/04/frank-ocean-billboard-coachella.jpg?w=1581&h=1054&crop=1",
            // Childish Gambino
            "https://www.sentireascoltare.com/wp-content/uploads/2012/11/donald-glover-facebook-photo-650x650.jpg",
            // Billie Eilish
            "https://www.nme.com/wp-content/uploads/2024/04/Billie-Eilish-2024.jpg",
            // The Weeknd
            "https://wallpapercave.com/wp/wp11700231.jpg",
            // Post Malone
            "https://v13.net/wp-content/uploads/post_malone.webp",
            // Kanye West
            "https://media.pitchfork.com/photos/606ce5aca518b22125a53a3c/2:1/w_2560%2Cc_limit/Kanye%252520West.jpg",
            // Lil Uzi Vert
            "https://www.nme.com/wp-content/uploads/2023/12/lil-uzi-vert@2000x1270.jpg",
            // Playboi Carti
            "https://www.billboard.com/wp-content/uploads/2024/09/Playboi-Carti-brazil-2024-billboard-1548.jpg?w=942&h=623&crop=1",
            // 21 Savage
            "https://wallpapers.com/images/featured/21-savage-pictures-519f7aj2iqjqp0t9.jpg",
            // Future
            "https://www.vibe.com/wp-content/uploads/2022/10/GettyImages-1426380393-e1664901340725.jpg?w=910&h=511&crop=1&resize=1440%2C810",
            // Doja Cat
            "https://www.rollingstone.com/wp-content/uploads/2023/05/doja-cat-2022-RS-1800.jpg?w=1581&h=1054&crop=1",
            // SZA
            "https://www.rollingstone.com/wp-content/uploads/2023/08/SZA.jpg?w=1581&h=1054&crop=1",
            // Arctic Monkeys
            "https://th.bing.com/th/id/R.a1f3aae783c6e74044bee3047ad7ebbe?rik=O4iXZkBf%2bT%2fX0A&pid=ImgRaw&r=0",
            // Tame Impala
            "https://www.rollingstone.com/wp-content/uploads/2019/03/Tame-Impala-Matt-Sav-SYNTK.jpg?crop=900:600&width=440",
            // Radiohead
            "https://cdn.britannica.com/98/162198-050-6452139D/Radiohead-business-models-British-performers-innovator-Internet-2012.jpg",
            // Mac DeMarco
            "https://www.rollingstone.com/wp-content/uploads/2025/06/Mac-DeMarco-Album-Announce.jpg?w=1581&h=1054&crop=1"));


    @Transactional
    public void inicializarArtistas() {
        for (int i = 0; i < config.getNumeroDeArtistas(); i++) {
            Artista artista = crearArtistaAleatorio();
            guardarArtista(artista);
        }
    }

    public Artista crearArtistaAleatorio() {
        ArrayList<String> nombreImagen = generarNombre();
        String nombre = nombreImagen.get(0);
        String imagen = nombreImagen.get(1);
        Artista artista = new Artista(nombre, imagen, generarNumeroOyentes(), generarCiudad(), generarDescripcion());
        return artista;
    }

    private ArrayList<String> generarNombre() {
        ArrayList<String> artistaImagen = new ArrayList<>();

        int numeroArtista = faker.number().numberBetween(0, artistas
                .size());
        artistaImagen.add(artistas.get(numeroArtista));
        artistaImagen.add(imagenes.get(numeroArtista));
        artistas.remove(artistas.get(numeroArtista));
        imagenes.remove(imagenes.get(numeroArtista));
        return artistaImagen;
    }

    private String generarDescripcion() {
        String[] descripciones = {
                "Pionero indiscutible del trap español, ha sabido fusionar las raíces más profundas del flamenco con la crudeza del rap urbano, creando un sonido completamente propio que ha trascendido fronteras. Su evolución artística lo ha llevado desde los barrios hasta los escenarios internacionales, demostrando que la tradición y la modernidad pueden convivir en perfecta armonía.",

                "Referente absoluta del reggaeton en España, ha conquistado la escena urbana con un estilo directo, agresivo y sin concesiones. Su voz inconfundible y su actitud irreverente la han convertido en un símbolo de empoderamiento femenino dentro de un género históricamente masculino. Cada uno de sus lanzamientos genera expectación a nivel nacional e internacional.",

                "Figura clave en la consolidación del trap andaluz, ha logrado elevar las raíces culturales del sur de España al lenguaje urbano contemporáneo. Su música es un puente entre generaciones, entre el flamenco que heredó de su tierra y el trap que lo conecta con la juventud global. Una voz auténtica en un panorama saturado de artificios.",

                "Artista verdaderamente versátil, su propuesta musical no conoce fronteras entre géneros. Fusiona el rap con el R&B, la electrónica y la música de autor con una naturalidad pasmosa, construyendo un universo sonoro propio que resulta imposible de etiquetar. Su capacidad para reinventarse en cada proyecto lo mantiene siempre en la vanguardia de la escena.",

                "Con más de una década sobre los escenarios, es considerado una de las voces más sólidas e influyentes del hip hop en español. Su trayectoria es un testimonio de constancia, autenticidad y evolución, desde sus primeras mixtapes underground hasta llenar los recintos más grandes del país. Un referente que ha abierto el camino para toda una generación de raperos.",

                "Productor y artista multifacético, ha revolucionado el sonido urbano latinoamericano desde los estudios y desde el escenario. Su oído privilegiado para las tendencias le ha permitido anticiparse siempre a su tiempo, colaborando con los grandes nombres de la escena global mientras mantiene intacta su identidad artística. Una mente creativa que redefine constantemente lo que significa hacer música urbana.",

                "Exponente del rap más consciente y comprometido en lengua española, sus letras son mucho más que rimas: son reflexiones profundas sobre la sociedad, la identidad y la resistencia cultural. Con un flow inconfundible y una capacidad lírica extraordinaria, ha conseguido llevar el mensaje del hip hop más intelectual a audiencias masivas sin perder ni un ápice de honestidad.",

                "Artista de culto en la escena underground española, su nombre es sinónimo de autenticidad y valentía artística. Sus letras, crudas y sin filtros, retratan una realidad que muchos prefieren ignorar, y su sonido se niega a concesiones comerciales. Lleva años siendo la banda sonora de quienes buscan algo más profundo y honesto en la música.",

                "Su propuesta artística es una de las más originales de la escena española contemporánea: flamenco, trap y R&B conviven en sus canciones con una naturalidad que solo se consigue cuando el talento y la autenticidad van de la mano. Cada proyecto es una exploración de su identidad cultural y personal, un viaje sonoro que sorprende y emociona a partes iguales.",

                "Pionera y referente indiscutible del rap femenino en español, ha tenido que abrirse camino en una escena históricamente dominada por hombres con talento, determinación y un flow que pocos pueden igualar. Su música habla de empoderamiento, de lucha y de identidad con una sinceridad que ha conectado con millones de personas en todo el mundo hispanohablante.",

                "Con décadas de trayectoria a sus espaldas, su voz es una de las más reconocibles e influyentes del rock alternativo en español. Ha sabido mantenerse siempre relevante sin traicionar su esencia, evolucionando con los tiempos sin perder la autenticidad que lo convirtió en referente. Sus conciertos son experiencias que marcan a quienes tienen la suerte de vivirlos.",

                "Artista pop de alcance masivo, ha construido una carrera meteórica gracias a una combinación explosiva de talento vocal, presencia escénica y una conexión especial con su público. Sus directos son espectáculos de primera magnitud, llenos de energía y emoción, que demuestran por qué se ha convertido en uno de los nombres más grandes de la música en español.",

                "Banda de culto del indie español, han construido a lo largo de los años un universo sonoro propio de una profundidad y coherencia artística poco comunes. Sus letras, de una calidad poética excepcional, y su sonido atmosférico y envolvente los han convertido en referentes para toda una generación que busca algo más que entretenimiento en la música.",

                "Icono indiscutible del rock en español, su música ha trascendido generaciones y fronteras geográficas para convertirse en parte del patrimonio cultural hispanohablante. Cada álbum es un acontecimiento, cada concierto una celebración colectiva. Su influencia sobre los músicos que vinieron después es incalculable y su legado seguirá resonando durante décadas.",

                "Referente del pop español más contemporáneo, su capacidad para fusionar estilos y tendencias con naturalidad y buen gusto lo ha situado en la cima de la escena musical nacional. No sigue modas, las anticipa. Cada lanzamiento demuestra una madurez artística y una intuición musical que pocos artistas de su generación pueden igualar.",

                "Grupo indie con un sonido característico y unas letras que conectan de manera visceral con la juventud, han sabido construir una comunidad de seguidores fieles que va mucho más allá del consumo musical superficial. Sus canciones son himnos generacionales que hablan de dudas, de amor y de la búsqueda de identidad con una honestidad desarmante.",

                "Artista consagrado del pop rock español, acumula a sus espaldas un catálogo de éxitos que abarca décadas y estilos, demostrando una versatilidad y una capacidad creativa fuera de lo común. Ha llenado estadios, ha ganado todos los premios posibles y sigue siendo, después de tanto tiempo, uno de los artistas más queridos y respetados de la escena nacional.",

                "Banda pionera del rock alternativo en España, su influencia sobre las generaciones de músicos que vinieron después es difícil de exagerar. Con un sonido que combinaba la energía más cruda del rock con una sensibilidad melódica poco común, abrieron puertas y rompieron barreras que permitieron a toda una escena florecer. Un legado que el tiempo solo hace más grande.",

                "Voz del rock más visceral y comprometido en español, su música nunca ha pedido permiso ni ha buscado la comodidad del éxito fácil. Letra a letra, concierto a concierto, ha construido una carrera basada en la integridad artística y el compromiso con unos valores que no negocia. Un artista que recuerda para qué sirve realmente el rock and roll.",

                "Artista que mezcla el folk, el rock y la poesía en cada actuación con una maestría que lo sitúa en una categoría propia. Sus canciones son pequeñas obras literarias musicadas, capaces de emocionar y de hacer pensar en igual medida. En directo, su conexión con el público alcanza una dimensión casi mística que quienes la han vivido no olvidan fácilmente.",

                "Uno de los raperos más influyentes de su generación a nivel mundial, ha redefinido lo que el hip hop puede ser lírica y sonoramente. Sus álbumes son escuchados y estudiados como obras de arte que trascienden el género, y su impacto cultural va mucho más allá de la música. Una figura generacional cuya sombra se proyecta sobre prácticamente todo lo que se hace en el rap contemporáneo.",

                "Artista global que redefine constantemente los límites entre el pop, el R&B y la electrónica con una audacia creativa que desafía toda categorización. Cada proyecto es una declaración de intenciones, una exploración de nuevos territorios sonoros que arrastra consigo a millones de oyentes. Una de las mentes más inquietas y originales de la música popular contemporánea.",

                "Productor y MC que ha marcado una era entera en el hip hop contemporáneo, tanto desde detrás de la mesa de mezclas como desde el micrófono. Su capacidad para construir paisajes sonoros complejos y emotivos, combinada con una habilidad lírica fuera de lo común, lo ha convertido en una figura de referencia absoluta para cualquiera que quiera entender hacia dónde va la música urbana.",

                "Fenómeno mundial del pop alternativo, ha conquistado al público global con una propuesta artística tan personal e intransferible que resulta imposible de imitar. Sus letras, profundamente autobiográficas, y su imagen, cuidada hasta el último detalle, han creado un universo propio en el que millones de personas encuentran reflejo y consuelo. Un icono de su tiempo.",

                "Artista polifacético que mezcla el hip hop con el soul, el jazz y la psicodelia en propuestas que desafían cualquier intento de clasificación. Su curiosidad intelectual y su voracidad creativa lo han llevado a explorar territorios sonoros que pocos se atreven a transitar, construyendo una discografía que es, al mismo tiempo, profundamente personal y universalmente resonante.",

                "Referente absoluto del R&B moderno, sus álbumes son considerados obras maestras por crítica y público por igual. Su voz, de una riqueza y una expresividad extraordinarias, es capaz de transmitir emociones que las palabras solas nunca podrían alcanzar. Cada lanzamiento es un acontecimiento cultural que redefine los estándares del género y eleva el listón para todos los demás.",

                "Voz inconfundible del indie rock anglosajón, sus conciertos son experiencias legendarias que quienes los han vivido describen como transformadoras. Con una carrera construida sobre la autenticidad y la negativa a ceder ante las presiones comerciales, ha conseguido mantener intacta su credibilidad artística mientras alcanzaba un reconocimiento masivo. Un equilibrio difícilísimo que pocos logran.",

                "Banda que definió el sonido de toda una era del rock alternativo, sus discos son la banda sonora de una generación entera que encontró en su música el lenguaje para expresar lo que de otra manera hubiera sido inexpresable. Su influencia sigue siendo palpable en prácticamente todo lo que se hace en el rock independiente contemporáneo, décadas después de su irrupción.",

                "Artista de culto cuya influencia se extiende silenciosamente por todos los géneros de la música moderna, sin que muchos de quienes la sienten sean conscientes de su origen. Ha construido una carrera al margen de las lógicas comerciales, guiado únicamente por una visión artística tan coherente como radical, y el tiempo le ha dado la razón de una manera que pocos hubieran podido predecir.",

                "Pionero del rap introspectivo y confesional, sus letras son estudiadas y citadas como poesía contemporánea por quienes ven en la música algo más que entretenimiento. Su disposición a explorar los rincones más oscuros y vulnerables de la experiencia humana, con una honestidad que pocas veces se ve en la música popular, lo ha convertido en una voz imprescindible de su tiempo."
        };
        return descripciones[faker.number().numberBetween(0, descripciones.length)];
    }

    private String generarCiudad() {
        String[] ciudades = {
                "Granada", "Madrid", "Barcelona", "Sevilla", "Valencia",
                "Bilbao", "Málaga", "Zaragoza", "Murcia", "Alicante",
                "Córdoba", "Valladolid", "Vigo", "Gijón", "Vitoria",
                "San Sebastián", "Pamplona", "Salamanca", "Burgos", "Cádiz",
                "Londres", "París", "Berlín", "Ámsterdam", "Roma",
                "Lisboa", "Bruselas", "Viena", "Praga", "Budapest",
                "Estocolmo", "Oslo", "Copenhague", "Dublín", "Varsovia",
                "Nueva York", "Los Ángeles", "Miami", "Chicago", "Houston",
                "Ciudad de México", "Buenos Aires", "Bogotá", "Lima", "Santiago",
                "São Paulo", "Río de Janeiro", "Medellín", "Monterrey", "Caracas"
        };
        return ciudades[faker.number().numberBetween(0, ciudades.length)];
    }

    private int generarNumeroOyentes() {
        return (int) (Math.random() * config.getNumeroMaximoOyentes());
    }

    private void guardarArtista(Artista artista) {
        try {
            artistaService.save(artista);
        } catch (Exception e) {
            System.err.println("Error al artista con nombre: " +
                    artista.getNombre() + ": " + e.getMessage());
        }
    }

    public static int getNumeroArtistas() {
        return artistas.size();
    }

}
