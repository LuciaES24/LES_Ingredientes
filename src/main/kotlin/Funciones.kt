import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * Guarda el documento xml
 * @param nombre_fichero nombre del archivo xml
 * @param doc documento con el contenido del xml
 */
fun guardar_xml(nombre_fichero:String, doc:Document){
    val file = File(nombre_fichero)
    val transformer = TransformerFactory.newInstance().newTransformer()
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
    val result = StreamResult(file)
    val source = DOMSource(doc)
    transformer.transform(source,result)
}

/**
 * Obtiene todos los ingredientes en forma de objeto
 * @param doc xml del que extraemos la información
 * @return lista con los ingredientes encontrados
 */
fun objetosIngrediente(doc:Document) : MutableList<Ingrediente> {
    val elementosIngrediente = doc.getElementsByTagName("ingrediente")
    val totalIngredientes = mutableListOf<Ingrediente>()
    //Recorremos todos los nodos ingrediente
    for (i in 0..elementosIngrediente.length-1){
        //Obtenemos el número del ingrediente
        val numerosIngrediente = doc.getElementsByTagName("numero").item(i)
        val numero = numerosIngrediente.textContent
        //Obtenemos el nombre del ingrediente
        val nombresIngrediente = doc.getElementsByTagName("nombre").item(i)
        val nombre = nombresIngrediente.textContent
        //Obtenemos las recetas del ingrediente
        val recetasIngrediente = doc.getElementsByTagName("recetas").item(i).childNodes
        val totalRecetas = Recetas()
        //Obtenemos todos los nodos receta y las guardamos en el objeto Receta()
        for (x in 0..recetasIngrediente.length-1){
            val receta = recetasIngrediente.item(x).textContent
            totalRecetas.anadirReceta(receta)
        }
        //Guardamos el ingrediente con todos sus datos en la lista
        totalIngredientes.add(Ingrediente(numero.toInt(),nombre,totalRecetas))
    }
    return totalIngredientes
}

/**
 * Añade un nuevo ingrediente al xml
 * @param doc xml en el que vamos a guardar el nuevo ingrediente
 * @param ingrediente nuevo ingrediente para añadir
 * @return documento con los cambios realizados
 */
fun crearIngrediente(doc: Document, ingrediente: Ingrediente): Document {
    //Buscamos el elemento root al que le añadiremos el nuevo ingrediente
    val root = doc.getElementsByTagName("ingredientes").item(0)

    //Creamos una nueva etiqueta ingrediente
    val nuevoIngrediente = doc.createElement("ingrediente")
    root.appendChild(nuevoIngrediente)

    //Creamos una nueva etiqueta numero
    val numeroIngrediente = doc.createElement("numero")
    numeroIngrediente.textContent = ingrediente.numero.toString()
    nuevoIngrediente.appendChild(numeroIngrediente)

    //Creamos una nueva etiqueta nombre
    val nombreIngrediente = doc.createElement("nombre")
    nombreIngrediente.textContent = ingrediente.nombreIngrediente
    nuevoIngrediente.appendChild(nombreIngrediente)

    //Creamos una nueva etiqueta recetas
    val recetasIngrediente = doc.createElement("recetas")
    nuevoIngrediente.appendChild(recetasIngrediente)

    //Vamos creando etiquetas receta según las que tengamos en el nuevo ingrediente
    for (receta in ingrediente.recetasIngredente.recetas){
        val recetaIngrediente = doc.createElement("receta")
        recetaIngrediente.textContent = receta
        recetasIngrediente.appendChild(recetaIngrediente)
    }

    doc.normalizeDocument()

    return doc
}

/**
 * Obtiene todos los ingredientes en forma de objeto añadiendoles una nueva receta
 * @param doc xml del que extraemos la información
 * @param recetaNueva nueva receta a añadir
 * @return lista con los ingredientes encontrados
 */
fun objetosIngredienteNuevaReceta(doc:Document, recetaNueva:String) : MutableList<Ingrediente> {
    val elementosIngrediente = doc.getElementsByTagName("ingrediente")
    val totalIngredientes = mutableListOf<Ingrediente>()
    //Recorremos todos los nodos ingrediente
    for (i in 0..elementosIngrediente.length-1){
        //Obtenemos el número del ingrediente
        val numerosIngrediente = doc.getElementsByTagName("numero").item(i)
        val numero = numerosIngrediente.textContent
        //Obtenemos el nombre del ingrediente
        val nombresIngrediente = doc.getElementsByTagName("nombre").item(i)
        val nombre = nombresIngrediente.textContent
        //Obtenemos las recetas del ingrediente
        val recetasIngrediente = doc.getElementsByTagName("recetas").item(i).childNodes
        val totalRecetas = Recetas()
        //Obtenemos todos los nodos receta y las guardamos en el objeto Receta()
        for (x in 0..recetasIngrediente.length-1){
            val receta = recetasIngrediente.item(x).textContent
            totalRecetas.anadirReceta(receta)
        }
        totalRecetas.anadirReceta(recetaNueva)
        //Guardamos el ingrediente con todos sus datos en la lista
        totalIngredientes.add(Ingrediente(numero.toInt(),nombre,totalRecetas))
    }
    return totalIngredientes
}

/**
 * Crea un nuevo documento xml con la nueva receta
 * @param doc documento en el que vamos a guardar los datos
 * @param listaIngrediente lista de ingredientes que vamos a guardar
 * @return documento con los cambios realizados
 */
fun crearNuevoXML(listaIngrediente: MutableList<Ingrediente>) : Document {
    //Creamos el nuevo archivo xml
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val doc = builder.newDocument()
    //Creamos la etiqueta root ingredientes
    val root = doc.createElement("ingredientes")

    //Recorremos la lista de ingredientes
    for (ingrediente in listaIngrediente){
        //Creamos una nueva etiqueta ingrediente
        val nuevoIngrediente = doc.createElement("ingrediente")
        root.appendChild(nuevoIngrediente)

        //Creamos una nueva etiqueta numero
        val numeroIngrediente = doc.createElement("numero")
        numeroIngrediente.textContent = ingrediente.numero.toString()
        nuevoIngrediente.appendChild(numeroIngrediente)

        //Creamos una nueva etiqueta nombre
        val nombreIngrediente = doc.createElement("nombre")
        nombreIngrediente.textContent = ingrediente.nombreIngrediente
        nuevoIngrediente.appendChild(nombreIngrediente)

        //Creamos una nueva etiqueta recetas
        val recetasIngrediente = doc.createElement("recetas")
        nuevoIngrediente.appendChild(recetasIngrediente)

        //Vamos creando etiquetas receta según las que tengamos en el nuevo ingrediente
        for (receta in ingrediente.recetasIngredente.recetas){
            val recetaIngrediente = doc.createElement("receta")
            recetaIngrediente.textContent = receta
            recetasIngrediente.appendChild(recetaIngrediente)
        }
    }

    doc.normalize()

    return doc
}
