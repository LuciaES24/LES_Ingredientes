import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

fun main() {
    try {
        val archivoXML = File("ingredientes.xml")
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(archivoXML)


        //Obtenemos los ingredientes del XML
        var listaIngredietes = objetosIngrediente(doc)
        for (ingrediente in listaIngredietes){
            println(ingrediente.toString())
        }

        //Creamos un nuevo ingrediente con sus recetas
        var recetasNaranja = Recetas()
        recetasNaranja.anadirReceta("Zumo")
        var ingrediente = Ingrediente(11,"Naranja",recetasNaranja)
    }catch (e:Exception){
        println("Archivo no encontrado o no existe")
    }
}