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
        val listaIngredietes = objetosIngrediente(doc)
        if (listaIngredietes.isEmpty()){
            throw Exception()
        }
        for (ingrediente in listaIngredietes){
            println(ingrediente.toString())
        }

        println("==================================")



        //Ejercicio 5
        //Añadimos una nueva receta a los objetos obtenidos del xml
        val listaIngredientesNuevaReceta = objetosIngredienteNuevaReceta(doc,"Tengo hambre")
        if (listaIngredientesNuevaReceta.isEmpty()){
            throw Exception()
        }
        for (ingrediente3 in listaIngredientesNuevaReceta){
            println(ingrediente3.toString())
        }

        //Ejercicio 6
        //Añadimos los ingredientes con la nueva receta a un nuevo xml
        val xmlNuevo = crearNuevoXML(listaIngredientesNuevaReceta)
        guardar_xml("ingredientes_2.xml", xmlNuevo)

    }catch (e:FileNotFoundException){
        println("Archivo no encontrado o no existe")
    }catch (ex:Exception){
        println("No se han encontrado ingredientes")
    }
}