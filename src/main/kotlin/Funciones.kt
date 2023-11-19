import org.w3c.dom.Document

fun objetosIngrediente(doc:Document) : MutableList<Ingrediente> {
    var elementosIngrediente = doc.getElementsByTagName("ingrediente")
    var totalIngredientes = mutableListOf<Ingrediente>()
    for (i in 0..elementosIngrediente.length-1){
        var numerosIngrediente = doc.getElementsByTagName("numero").item(0)
        var numero = numerosIngrediente.textContent
        var nombresIngrediente = doc.getElementsByTagName("nombre").item(0)
        var nombre = nombresIngrediente.textContent
        var recetasIngrediente = doc.getElementsByTagName("recetas").item(0).childNodes
        var totalRecetas = Recetas()
        for (x in 0..recetasIngrediente.length-1 step 2){
            var receta = recetasIngrediente.item(x).textContent
            totalRecetas.anadirReceta(receta)
        }
        totalIngredientes.add(Ingrediente(numero.toInt(),nombre,totalRecetas))
    }
    return totalIngredientes
}


fun buscarIngredientes(doc: Document): MutableList<Ingrediente> {
    var elementosIngrediente = doc.getElementsByTagName("ingrediente")
    var totalIngredientes = mutableListOf<Ingrediente>()
    for (i in 0..elementosIngrediente.length-1){
        var nodosIngredienteActual = elementosIngrediente.item(i).childNodes
        for (z in 0..nodosIngredienteActual.length-1 step 2){
            var numeroIngrediente = nodosIngredienteActual.item(0).textContent
            var nombreIngrediente = nodosIngredienteActual.item(1).textContent
            var recetas = nodosIngredienteActual.item(2).childNodes
            var totalRecetas = Recetas()
            for (x in 0..recetas.length-1 step 2){
                var receta = recetas.item(x).textContent
                totalRecetas.anadirReceta(receta)
            }
            totalIngredientes.add(Ingrediente(numeroIngrediente.toInt(),nombreIngrediente,totalRecetas))
        }
    }
    return totalIngredientes
}
