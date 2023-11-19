import java.io.File
import java.io.FileNotFoundException
import javax.xml.parsers.DocumentBuilderFactory

fun main() {
    try {
        val archivoXML = File("ingredientes.xml")
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(archivoXML)


        //Ejercicio 3
        //Obtenemos los ingredientes del XML
        var listaIngredietes = objetosIngrediente(doc)
        if (listaIngredietes.isEmpty()){
            throw Exception()
        }
        for (ingrediente in listaIngredietes){
            println(ingrediente.toString())
        }

        println("==================================")

        //Ejercicio 4
        //Creamos un nuevo ingrediente con sus recetas
        var recetasNaranja = Recetas()
        recetasNaranja.anadirReceta("Zumo")
        recetasNaranja.anadirReceta("Pollo a la naranja")
        var ingrediente = Ingrediente(11,"Naranja",recetasNaranja)

        var xmlNuevoIngrediente = crearIngrediente(doc,ingrediente)
        guardar_xml("ingredientes.xml", xmlNuevoIngrediente)

        //Volvemos a imprimir todos los ingredientes
        var listaIngredietesNueva = objetosIngrediente(doc)
        if (listaIngredietesNueva.isEmpty()){
            throw Exception()
        }
        for (ingrediente2 in listaIngredietesNueva){
            println(ingrediente2.toString())
        }



    }catch (e:FileNotFoundException){
        println("Archivo no encontrado o no existe")
    }catch (ex:Exception){
        println("No se han encontrado ingredientes")
    }
}